package webmvc.services;

import lombok.SneakyThrows;
import org.postgresql.util.PSQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import webmvc.models.Resource;
import webmvc.models.User;

import javax.sql.DataSource;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class MyEntityManager {

    private Logger logger = Logger.getLogger(MyEntityManager.class.getName());

    private DriverManagerDataSource dataSource;

    public MyEntityManager() {
        this.dataSource = new DriverManagerDataSource("jdbc:postgresql://localhost:5432/zdstyle",
                "postgres",
                "dREAM1cACAO");
        dataSource.setDriverClassName("org.postgresql.Driver");
    }

    @SneakyThrows
    public <T> T get(long id, Class<? extends T> c) {
        PreparedStatement preparedStatement = dataSource.
                getConnection().
                prepareStatement("SELECT * FROM resource WHERE id=?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return this.rowmap(resultSet, c);
    }

    private <T> T rowmap(ResultSet resultSet, Class<? extends T> c) throws NoSuchColumnException, NoSuchMethodException {
        Constructor constructor = null;
        try {
            constructor = c.getConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            logger.warning(String.format("%s class has not no args constructor", c));
            return null;
        }

        T instance = null;
        try {
            instance = (T) constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }


        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            String methodName = "get".concat(field.getType().getSimpleName());
            if (!field.isAccessible()) {
                field.setAccessible(true);

                try {

                    Method getMethod = resultSet.getClass().getDeclaredMethod(methodName, String.class);
                    if (!getMethod.isAccessible()) {
                        logger.info(String.format("%s method ", getMethod));
                        getMethod.setAccessible(true);
                        Object arg = getMethod.invoke(resultSet, field.getName());
                        field.set(instance, arg);
                        getMethod.setAccessible(false);
                    }

                } catch (NoSuchMethodException e) {
                    logger.warning(String.format("method not found %s", methodName));
                    throw e;
                } catch (InvocationTargetException e) {
                    throw new NoSuchColumnException(e.getMessage());

                } catch (IllegalAccessException e) {
//                    cannot happen
                    field.setAccessible(false);

                }
            }
        }
        return instance;
    }


    @SneakyThrows
    public <T> T save(T obj) {
        PreparedStatement preparedStatement = null;

        //language=SQL
        String insert = "INSERT INTO resource VALUES(?,?)";

        try {
            preparedStatement = dataSource.
                    getConnection().
                    prepareStatement(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Field[] fields = obj.getClass().getDeclaredFields();
        List<Object> args = new ArrayList<>();

        for (Field field : fields) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
                try {
                    args.add(field.get(obj));
                } catch (IllegalAccessException e) {
                    logger.warning(String.format("wtf %s", field.getName()));
                }
                field.setAccessible(false);
            }
        }

        for (int i = 0; i < args.size(); i++) {

            Object objectClass = args.get(i);
            Class argClass = args.get(i).getClass();
            try {
                Field typeField = objectClass.getClass().getField("TYPE");
                if (typeField != null) {
                    argClass = (Class) typeField.get(objectClass);
                    System.out.println(argClass);
                }
            } catch (NoSuchFieldException e) {
                logger.info("its not primitive");
            }


            assert preparedStatement != null;
            Method setMethod = preparedStatement.getClass()
                    .getDeclaredMethod(
                            "set".concat(args.get(i).getClass().getSimpleName()),
                            Integer.TYPE, argClass);
            if (!setMethod.isAccessible()) {
                logger.info(String.format("%s method ", setMethod));
                setMethod.setAccessible(true);
                setMethod.invoke(preparedStatement, i + 1, args.get(i));
                setMethod.setAccessible(false);
            }
        }
        preparedStatement.executeUpdate();

        return null;
    }

    public static void main(String[] args) {
        MyEntityManager myEntityManager = new MyEntityManager();
        System.out.println(myEntityManager.get(5, User.class));
//
//        Resource resource = new Resource();
//        resource.setId(1000023L);
//        resource.setLink("fl;bd'lfbjdlkbmdlkbndklbn");
//        myEntityManager.save(resource);
    }

}

class NoSuchColumnException extends Exception{

    public NoSuchColumnException(String e){
        super(e);
    }
}
