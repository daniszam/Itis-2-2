import com.sun.xml.internal.rngom.digested.DRefPattern;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DB {

    private static Random r = new Random();

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/zdstyle?user=postgres&password=dREAM1cACAO";
        Connection conn = DriverManager.getConnection(url);
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO resource_tag(resource_id, tag_id) VALUES(?, ?);"
        );
        int min = 1, max = 400;
        List<Integer> anArray = new ArrayList<Integer>();
        for (int i = min; i < max; i++) {
            anArray.add(i);
        }

        for (int i=1; i< 1000000; i++ ){
            int tagsCount = 50 + r.nextInt(100);
            Collections.shuffle(anArray);
            for( int j = 0; j<tagsCount; j++){
                preparedStatement.setInt(1,i);
                preparedStatement.setInt(2, anArray.get(j));
                preparedStatement.execute();
            }
        }
    }
}
