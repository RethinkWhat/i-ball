package shared.res;

public class Report {
    private int reportID;
    private int sessionID;
    private int idolID;
    private int fanID;
    private String abuseType;
    private String description;

    /** Default constructor
     * @param reportID
     * @param sessionID
     * @param idolID
     * @param fanID
     * @param abuseType
     * @param description
     * */

    public Report(int reportID, int sessionID, int idolID, int fanID, String abuseType, String description){
        this.reportID = reportID;
        this.sessionID = sessionID;
        this.idolID = idolID;
        this.fanID = fanID;
        this.abuseType = abuseType;
        this.description = description;
    }

    /**
     * Getter for ReportID
     * @return reportID
     * */

    public int getReportID() {
        return reportID;
    }

    /**
     * Setter for ReportID
     * */

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    /**
     * Getter for SessionID
     * @return sessionID
     * */

    public int getSessionID(){
        return sessionID;
    }

    /**
     * Setter for SessionID
     * */

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    /**
     * Getter for IdolID
     * @return idolID
     * */

    public int getIdolID(){
        return idolID;
    }

    /**
     * Setter for IdolID
     * */

    public void setIdolID(int idolID) {
        this.idolID = idolID;
    }

    /**
     * Getter for UserID
     * @return userID
     * */

    public int getFanID() {
        return fanID;
    }

    /**
     * Setter for UserID
     * */

    public void setUserID(int userID) {
        this.fanID = fanID;
    }

    /**
     * Get the abuse type
     */

    public String getAbuseType() {
        return abuseType;
    }

    /**
     * Set the abuse type
     */

    public void setAbuseType(String abuseType) {
        this.abuseType = abuseType;
    }

    /**
     * Get the description of report
     * */

    public String getDescription() {
        return description;
    }

    /**
     * Set the description of report
     */

    public void setDescription(String description) {
        this.description = description;
    }
}
