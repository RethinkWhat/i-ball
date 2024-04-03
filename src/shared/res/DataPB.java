package shared.res;

import java.sql.*;

/**
 * This class will be used to access and manipulate the database
 *
 * IMPORTANT:
 * Before coding, the developer should add the mysql-connector-j-8.3.0.jar file in the project structure.
 * (If the jar file is already in the External Libraries, no need to do this step)
 *
 * The jar file can be found in the res folder.
 */
public class DataPB {

    private static Connection con;

    private DataPB(){
    };

    /**
     *  Sets the connection to the database
     *
     *  READ THE LINE COMMENTS BEFORE RUNNING THE PROGRAM
     */
    public static void setCon() {
       try {
            // Developer should add the Schema name right after the 3306/
            String url = "jdbc:mysql://localhost:3306/deans5";

            // User and Password should be changed dynamically by the developer
            String user = "root";
            String password = "root";

            con = DriverManager.getConnection(url, user, password);
       }catch (Exception e){
           System.out.println("Database connection failed");
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

    /**
     * Method to handle validating user login authentication
     * @return userExists
     */
    public static Object validateUser(String username, String password) throws SQLException{
        //TODO : Access schema and check whether user exists

        /*
        String query = "SELECT * FROM ? WHERE username=? and password=?";
        PreparedStatement stmt = con.prepareStatement(query);

        stmt.setString(1, "User");
        stmt.setString(2, username);
        stmt.setString(3, password);
        ResultSet account = stmt.executeQuery(query);
        if (account.next()) {
            int userID = account.getInt("userID");
            String email = account.getString("username");
            String gCashNumber = account.getString("GCashNumber");
            String ppAddress = account.getString("ppAddress");
            return new User(userID, username, email, password, gCashNumber, ppAddress);
        } else {
            stmt.setString(1, "Idol");
            stmt.executeQuery(query);
            if (account.next()) {
                int idolID = account.getInt("idolID");
                //return new Idol(idolID,)
            }
        }

         */


        User temp = new User(1,"Rithik", "2233293@slu.edu.ph", "rithik", "09177900153", "1");
        return temp;
    }

    /**
     * Returns a ResultSet containing all the names of idols present in the system
     * @throws SQLException
     */
    public static ResultSet getAllIdols() throws SQLException {
        String query = "SELECT * FROM idol";
        Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
        ResultSet idolSet = stmt.executeQuery(query);

        return idolSet;
    }




    //===============================//

    /**
     * TO BE DELETED
     *
     * For testing purposes only
     * @param args
     */
    public static void main(String[] args) throws SQLException {
        setCon();


    }
}
