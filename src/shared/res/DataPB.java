package shared.res;

import client.fan.view.application_pages.IdolsView;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
            String password = "";

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
    public static Object validateUser(String username, String password) throws SQLException {
        //TODO : Access schema and check whether user exists
        DataPB.setCon();

        String query = "SELECT userID, username, GCashNumber, profilePictureAddress FROM User WHERE username=? and password=?";

        PreparedStatement stmt = con.prepareStatement(query);

        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet account = stmt.executeQuery();

        if (account.next()) {
            int userID = account.getInt("userID");
            String email = account.getString("username");
            String gCashNumber = account.getString("GCashNumber");
            String ppAddress = account.getString("profilePictureAddress");
            return new User(userID, username, email, password, gCashNumber, ppAddress);
        } else {
            query = "SELECT idolID, idolName, GCashNumber, idolType, idolStatus, voiceCallRate, " +
                    "videoCallRate, fbAccount, xAccount, igAccount, bio, quote, profilePictureAddress  FROM Idol WHERE username=? and password=?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            account = stmt.executeQuery();
            if (account.next()) {
                boolean boolStatus = false;
                int idolID = account.getInt("idolID");
                String idolName = account.getString("idolName");
                String gCashNumber = account.getString("gCashNumber");
                String type = account.getString("idolType");
                String status = account.getString("idolStatus");
                if (status.equalsIgnoreCase("Verified"))
                    boolStatus = true;
                double voiceRate = account.getDouble("voiceCallRate");
                double videoRate = account.getDouble("videoCallRate");
                String fb = account.getString("fbAccount");
                String x = account.getString("xAccount");
                String ig = account.getString("igAccount");
                String bio = account.getString("bio");
                String quote = account.getString("quote");
                String pp = account.getString("profilePictureAddress");
                return new Idol(idolID, username, idolName, gCashNumber, type, boolStatus, voiceRate, videoRate, fb, x, ig, bio, quote, pp, password);
            }
            return null;
        }
    }

    private static String formatDate(String date) {
        String[] dateInfo = date.split("/");
        Calendar cal = new GregorianCalendar(
                Integer.parseInt(dateInfo[2]),
                Integer.parseInt(dateInfo[0])-1,
                Integer.parseInt(dateInfo[1]));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    public static ArrayList<Session> getIdolSessions(int idolID, String dateToFind) {
        DataPB.setCon();
        ArrayList<Session> sessionsList = new ArrayList<>();

        try {
            String query = "SELECT " +
                    "sessionID, date, startTime, duration, sessionType, amount, userID " +
                    "FROM Session WHERE idolID=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, idolID);
            ResultSet sessions = stmt.executeQuery();
            while (sessions.next()) {
                Date date = sessions.getDate(2);
                if (date.toString().equalsIgnoreCase(formatDate(dateToFind))) {
                    int sessionID = sessions.getInt(1);
                    Time startTime = sessions.getTime(3);
                    Time duration = sessions.getTime(4);
                    String sessionType = sessions.getString(5);
                    Double amount = sessions.getDouble(6);
                    int userID = sessions.getInt(7);
                    String username = getUser(userID).getUsername();
                    Session session = new Session(sessionID, idolID, date, startTime, duration, sessionType, amount, username);
                    sessionsList.add(session);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessionsList;
    }

    public static User getUser(int userID) {
        DataPB.setCon();
        User userObj = null;
        try {
            String query = "SELECT username, email, password, GCashNumber, profilePictureAddress " +
                    "FROM User WHERE userID=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1,userID);
            ResultSet user = stmt.executeQuery();

            if (user.next()) {
                String username = user.getString("username");
                String email = user.getString("email");
                String password = user.getString("password");
                String gCash = user.getString("GCashNumber");
                String pp = user.getString("profilePictureAddress");
                userObj = new User(userID,username,email,password,gCash,pp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userObj;
    }

    /**
     * Returns a ResultSet containing all the names of idols present in the system
     * @throws SQLException
     */
    public static ResultSet getAllIdols() throws SQLException {
        DataPB.setCon();

        String query = "SELECT * FROM idol";
        Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
        ResultSet idolSet = stmt.executeQuery(query);

        return idolSet;
    }

    public static ResultSet idolSearch(String searchTerm) throws SQLException {
        DataPB.setCon();

        String query = "SELECT * FROM idol WHERE idolName LIKE ?";
        // Add wildcard '%' to the search term
        String searchPattern = searchTerm + "%";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, searchPattern);

        ResultSet searchInput = stmt.executeQuery();

        return searchInput;
        }





    //===============================//

    /**
     * TO BE DELETED
     *
     * For testing purposes only
     * @param args
     */
    public static void main(String[] args) throws SQLException {


    }
}
