package shared.res;

import java.sql.*;
import java.text.ParseException;
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
            String url = "jdbc:mysql://localhost:8889/deans5";

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
    public static Object validateUser(String username, String password) throws SQLException {
        //TODO : Access schema and check whether user exists
        DataPB.setCon();

        String query = "SELECT fanID, username, fanUser,GCashNumber, profilePictureAddress FROM Fan WHERE username=? and password=?";

        PreparedStatement stmt = con.prepareStatement(query);

        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet account = stmt.executeQuery();

        if (account.next()) {
            int fanID = account.getInt("fanID");
            String email = account.getString("username");
            String name = account.getString("fanUser");
            String gCashNumber = account.getString("GCashNumber");
            String ppAddress = account.getString("profilePictureAddress");
            return new Fan(fanID, username, name,email, password, gCashNumber, ppAddress);
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

        //dateToFind.split()
        DataPB.setCon();
        ArrayList<Session> sessionsList = new ArrayList<>();

        try {
            String query = "SELECT " +
                    "sessionID, date, startTime, duration, sessionType, amount, fanID, sessionStatus " +
                    "FROM Session WHERE idolID=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, idolID);
            ResultSet sessions = stmt.executeQuery();
            while (sessions.next()) {
                Date date = sessions.getDate(2);
                if (date.toString().equalsIgnoreCase(dateToFind)) {
                    int sessionID = sessions.getInt(1);
                    Time startTime = sessions.getTime(3);
                    Time duration = sessions.getTime(4);
                    String sessionType = sessions.getString(5);
                    Double amount = sessions.getDouble(6);
                    int fanID = sessions.getInt(7);
                    int status = sessions.getInt(8);
                    String username = getUser(fanID).getUsername();
                    Session session = new Session(sessionID, idolID, date, startTime, duration, sessionType, amount, username);
                    session.setStatus(status);
                    sessionsList.add(session);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessionsList;
    }

    /**
     * Returns all the booking of a fan given a specific date
     * @param date
     * @return
     * @throws SQLException
     */
    public static ResultSet searchFanSessions(int fanID, String date) throws SQLException{
        DataPB.setCon();

        String query =  "SELECT startTime, idolName, sessionType, duration " +
                "FROM session JOIN idol USING(idolID) " +
                "WHERE fanID = ? AND date LIKE ?";

        PreparedStatement stmt = con.prepareStatement(query);
        String searchKey = date + "%";

        stmt.setString(1, String.valueOf(fanID));
        stmt.setString(2, searchKey);

        ResultSet resultSet = stmt.executeQuery();

        return resultSet;
    }

    public static ResultSet getAllSessions(int fanID) throws SQLException {
        DataPB.setCon();

        String query = "SELECT startTime, idolName, sessionType, duration " +
                "FROM session JOIN idol USING(idolID) " +
                "WHERE fanID = ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, fanID);

        ResultSet resultSet = stmt.executeQuery();

        return resultSet;
    }


    public static Fan getUser(int fanID) {
        DataPB.setCon();
        Fan fanObj = null;
        try {
            String query = "SELECT username, email, password, GCashNumber, profilePictureAddress " +
                    "FROM Fan WHERE fanID=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1,fanID);
            ResultSet user = stmt.executeQuery();

            if (user.next()) {
                String username = user.getString("username");
                String name = user.getString("fanUser");
                String email = user.getString("email");
                String password = user.getString("password");
                String gCash = user.getString("GCashNumber");
                String pp = user.getString("profilePictureAddress");
                fanObj = new Fan(fanID,username, name, email,password,gCash,pp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fanObj;
    }

    public static String getUserPFP(String username) {
        DataPB.setCon();
        Fan fanObj = null;
        try {
            String query = "SELECT profilePictureAddress " +
                    "FROM Fan WHERE username=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,username);
            ResultSet user = stmt.executeQuery();

            if (user.next()) {
                return user.getString("profilePictureAddress");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getIdolPFP(String username) {
        DataPB.setCon();
        Fan fanObj = null;
        try {
            String query = "SELECT profilePictureAddress " +
                    "FROM idol WHERE idolName=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,username);
            ResultSet user = stmt.executeQuery();

            if (user.next()) {
                return user.getString("profilePictureAddress");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

    public static boolean deleteAccount(int id) {
        try {
            String query = "delete from idol WHERE idolID=?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1,id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean editInfo(int idolID, String toEdit, String newInfo) {
        try {
            String query = "UPDATE idol SET " + toEdit + " = ? WHERE idolid = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, newInfo);
            statement.setInt(2, idolID);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String[] getAvailability(int idolID, String date) {
        try {
            String query = "SELECT startTime, endTime, day FROM idol_availability WHERE idolID = ? AND day = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, idolID);
            statement.setString(2, date);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String st = resultSet.getString("startTime");
                String et = resultSet.getString("endTime");

                return new String[]{st, et};
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteStartEndTime(int idolID, String day) {
        try {
            String query = "DELETE FROM idol_availability WHERE idolID=? AND day=?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1,idolID);
            statement.setString(2,day);
            statement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateStartEndTime(int idolID, String date, String st, String et) {
        Long startTime = 0L;
        Long endTime = 0L;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            java.util.Date dateForStart = sdf.parse(st);
            java.util.Date dateForEnd = sdf.parse(et);
            startTime = dateForStart.getTime();
            endTime = dateForEnd.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            if (getAvailability(idolID, date) != null) {
                String query = "update idol_availability set startTime =?, endtime=? WHERE idolID=? AND day=?";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setTime(1,new Time(startTime));
                statement.setTime(2,new Time(endTime));
                statement.setInt(3,idolID);
                statement.setString(4,date);
                statement.executeUpdate();
            } else {
                String query = "INSERT INTO idol_availability (startTime, endTime, day, idolID) " +
                        "VALUES(?,?,?,?)";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setTime(1, new Time(startTime));
                statement.setTime(2, new Time(endTime));
                statement.setString(3,date);
                statement.setInt(4, idolID);
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
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

    public static ResultSet filterIdolsByType(String idolType) throws SQLException {
        DataPB.setCon();

        String query = "SELECT * FROM idol WHERE idolType = ?";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, idolType);

        ResultSet filteredIdols = stmt.executeQuery();

        return filteredIdols;
    }

    public static ResultSet getIdolSchedule(Idol idol) throws SQLException {
        DataPB.setCon();

        int idolID = idol.getIdolID();
        String query = "SELECT day, startTime, endTime FROM idol_availability WHERE idolID = " + idolID + ";";
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        return statement.executeQuery(query);
    }

    /**
     * Retrieves all the sessions of a specified idol.
     * @param idol The specified idol.
     * @return ResultSet of the idol's schedule.
     * @throws SQLException If error or exception occurs.
     */
    public static ResultSet getAllIdolSession(Idol idol) throws SQLException {
        DataPB.setCon();

        String query = "SELECT date, startTime, duration FROM session WHERE idolId=" + idol.getIdolID() + " ORDER BY date DESC;";
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        return statement.executeQuery(query);
    }

    /**
     * Inserts a new record in the session table.
     * @param session The specified session.
     * @throws SQLException If error or exception occurs.
     */
    public static void addNewSession(Session session) throws SQLException {
        DataPB.setCon();

        String insertion = "INSERT INTO session "
                + "(idolID, date, startTime, duration, sessionType, amount, fanID, sessionStatus)"
                + " values (?,?,?,?,?,?,?,?)";

        PreparedStatement statement = con.prepareStatement(insertion);
        statement.setInt(1,session.getIdolID());
        statement.setDate(2,session.getDate());
        statement.setTime(3,session.getStartTime());
        statement.setTime(4,session.getDuration());
        statement.setString(5,session.getSessionType());
        statement.setDouble(6,session.getAmount());
        statement.setInt(7,session.getIdolID());
        statement.setInt(8, session.getStatus());

        statement.executeUpdate();
    }

    public static int getSessionID(int idolID, int fanID, String date, String startTime) {
        try {
            DataPB.setCon();
            // 2024-04-15
            String query = "Select sessionID FROM session WHERE idolID=? AND userID=? AND date=? AND startTime=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1,idolID);
            stmt.setInt(2,fanID);
            stmt.setString(3,date);
            stmt.setString(4,startTime);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Call the getSessionID method above first before calling this method
     * @param sessionID
     */
    public static void setSessionToComplete(int sessionID) {
        try {
            String query = "UPDATE session set sessionStatus=? WHERE sessionID=?";

            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1,1);
            stmt.setInt(2,sessionID);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}