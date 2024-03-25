package shared;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This class will be used to access and manipulate the database
 */
public class DataPB {

    private static Connection con;

    private DataPB(){};


    public static void setCon(Connection con) {
       try {
            //Add the Schema name right after the 3306/<schema-name>
            String url = "jdbc:mysql://localhost:3306/";

            // User and Password should be changed dynamically by the developer
            String user = "root";
            String password = "root";

            con = DriverManager.getConnection(url, user, password);
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    /**
     * TO BE DELETED
     *
     * For testing purposes only
     * @param args
     */
    public static void main(String[] args) {

    }
}
