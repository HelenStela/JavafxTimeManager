package address.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Olga on 09.06.2016.
 */
public class SqliteConnection {

    public static Connection connector(){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:EmployeeDb.sqlite");
            return connection;
        }
        catch (Exception e){

        }
        return null;
    }
}
