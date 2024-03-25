package shared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class will be used to access and manipulate the database
 */
public class DataPB {

    private static Connection con;

    private DataPB(){};

    /**
     *  Sets the connection to the database
     *
     *  READ THE LINE COMMENTS BEFORE RUNNING THE PROGRAM
     * @param con
     */
    public static void setCon(Connection con) {
       try {
            // Developer should add the Schema name right after the 3306/
            String url = "jdbc:mysql://localhost:3306/<schema-name>";

            // User and Password should be changed dynamically by the developer
            String user = "root";
            String password = "root";

            con = DriverManager.getConnection(url, user, password);
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    public static void closeCon(){
        try {
            if (con != null){
                con.close();
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

    //INSERT DATA ACCESS METHODS HERE//





    //===============================//

    /**
     * TO BE DELETED
     *
     * For testing purposes only
     * @param args
     */
    public static void main(String[] args) {
        setCon(con);
    }
}
