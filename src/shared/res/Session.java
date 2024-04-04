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

    // holds the name of the fan
    /** In database this is userID, however for ease of use it will just hold the username of fan */
    private String fanName;

    // Holds the date of booking
    private Date date;

    // Holds the time the session starts
    private Time startTime;

    // Holds the total duration of the session
    private Time duration;

    // Holds the type of session scheduled
    private String sessionType;

    private double amount;

    /**
     * Default constructor
     * @param sessionID
     * @param idolID
     * @param fanName
     * @param date
     * @param startTime
     * @param duration
     * @param sessionType
     */
    public Session(int sessionID, int idolID,Date date, Time startTime, Time duration, String sessionType, double amount, String fanName) {
        this.sessionID = sessionID;
        this.idolID = idolID;
        this.fanName = fanName;
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.sessionType = sessionType;
        this.amount = amount;
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
    public String getFanName() {
        return fanName;
    }

    /**
     * Setter for fanName
     * @param fanName
     */
    public void setFanName(String fanName) {
        this.fanName = fanName;
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
    public Time getDuration() {
        return duration;
    }

    /**
     * Setter for the duration
     * @param duration
     */
    public void setDuration(Time duration) {
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

    @Override
    public String toString() {
        return "Session{" +
                "sessionID=" + sessionID +
                ", idolID=" + idolID +
                ", fanName='" + fanName + '\'' +
                ", date=" + date +
                ", startTime=" + startTime +
                ", duration=" + duration +
                ", sessionType='" + sessionType + '\'' +
                ", amount=" + amount +
                '}';
    }
}
