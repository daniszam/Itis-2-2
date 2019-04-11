package webmvc.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class LocaleRepo {

    //language=SQL
    private final String MESSAGE_BY_CODE = "SELECT text FROM $tablename WHERE code=?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public LocaleRepo(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public String getText(String db, String s){
        String query =MESSAGE_BY_CODE.replace("$tableName",db);

        String result = this.jdbcTemplate.queryForObject(
                query,
                new Object[]{db, s},
                String.class);
        return result;
    }
}
