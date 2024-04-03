package shared.res;

import java.sql.Date;
import java.sql.Time;

/**
 * Reference class for a session
 */
public class Session {

    // Variable to hold the primary key of session
    private int sessionID;

    // Foreign key to idol object
    private int idolID;

    // Foreign key to user object
    private int userID;

    // Holds the date of booking
    private Date date;

    // Holds the time the session starts
    private Time startTime;

    // Holds the total duration of the session
    private int duration;

    // Holds the type of session scheduled
    private String sessionType;

    /**
     * Default constructor
     * @param sessionID
     * @param idolID
     * @param userID
     * @param date
     * @param startTime
     * @param duration
     * @param sessionType
     */
    public Session(int sessionID, int idolID, int userID, Date date, Time startTime, int duration, String sessionType) {
        this.sessionID = sessionID;
        this.idolID = idolID;
        this.userID = userID;
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.sessionType = sessionType;
    }

    /**
     * get SessionID
     * @return sessionID
     */
    public int getSessionID() {
        return sessionID;
    }

    /**
     * Setter for sessionID
     * @param sessionID
     */
    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    /**
     * IdolID getter
     * @return idolID
     */
    public int getIdolID() {
        return idolID;
    }

    /**
     * Setter for IdolID
     * @param idolID
     */
    public void setIdolID(int idolID) {
        this.idolID = idolID;
    }

    /**
     * Getter for userID
     * @return userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Setter for userID
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Getter for date of session
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setter for the date
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Getter for the start time
     * @return
     */
    public Time getStartTime() {
        return startTime;
    }

    /**
     * Setter for the start time
     * @param startTime
     */
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter for the duration
     * @return
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Setter for the duration
     * @param duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Getter of the session type
     * @return
     */
    public String getSessionType() {
        return sessionType;
    }

    /**
     * Setter for the sessionType
     * @param sessionType
     */
    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }
}
