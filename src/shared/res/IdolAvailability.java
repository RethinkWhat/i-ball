package shared.res;

import java.sql.Date;
import java.sql.Time;

public class IdolAvailability {

    private int availabilityID;
    private int idolID;
    private Time startTime;
    private Time endTime;
    private Date date;

    /** Default constructor
     * @param availabilityID
     * @param idolID
     * @param startTime
     * @param endTime
     * @param date
     * */

    public IdolAvailability(int availabilityID, int idolID, Time startTime, Time endTime, Date date){
        this.availabilityID = availabilityID;
        this.idolID = idolID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }

    /**
     * Getter for AvailabilityID
     * @return availabilityID
     * */

    public int getAvailabilityID() {
        return availabilityID;
    }

    /**
     * Setter for AvailabilityID
     **/
    public void setAvailabilityID(int availabilityID) {
        this.availabilityID = availabilityID;
    }

    /**
     * Getter for IdolID
     * @return idolID
     * */

    public int getIdolID() {
        return idolID;
    }

    /**
     * Setter for IdolID
     **/

    public void setIdolID(int idolID) {
        this.idolID = idolID;
    }

    /**
     * Get the start time
     * @return startTime
     * */

    public Time getStartTime() {
        return startTime;
    }
    /**
     * Setter for the start time
     * */

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    /**
     * Get the end time
     * @return endTime
     * */

    public Time getEndTime(){
        return endTime;
    }

    /**
     * Setter for the end time
     **/

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    /**
     * Get the date of availability
     * @return date
     * */

    public Date getDate() {
        return date;
    }

    /**
     * Set the date of availability
     * */

    public void setDate(Date date) {
        this.date = date;
    }
}
