package client.fan.model.application_pages;

import shared.res.DataPB;
import shared.res.Idol;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The BookingModel provides methods for viewing idol information and handling the booking of a session.
 */
public class BookingModel {
    /**
     * The current idol.
     */
    private Idol idol;
    /**
     * Holds the idol details.
     */
    private List<String> idolDetails;
    /**
     * The list of schedules of the idol.
     */
    private List<List<String>> idolSchedule;

    /**
     * Constructs a BookingModel with a specified idol.
     * @param idol The specified idol.
     */
    public BookingModel(Idol idol) {
        this.idol = idol;

        idolDetails = new ArrayList<>();
        idolDetails.add(idol.getProfilePictureAddress());
        idolDetails.add(idol.getIdolName());
        idolDetails.add(idol.getIdolType());
        idolDetails.add(idol.getFbAccount());
        idolDetails.add(idol.getIgAccount());
        idolDetails.add(idol.getxAccount());
        idolDetails.add(idol.getQuote());
        idolDetails.add(idol.getBio());
        idolDetails.add(Double.toString(idol.getVideoCallRate()));
        idolDetails.add(Double.toString(idol.getVoiceCallRate()));
    }

    /**
     * Retrieves the available schedule of the idol.
     * @param idol The specified idol.
     * @throws SQLException If error or exception occurs.
     */
    public void getAvailSchedule(Idol idol) throws SQLException {
        idolSchedule = new ArrayList<>();
        ResultSet idolScheduleSet = DataPB.getIdolSchedule(idol);

        while (idolScheduleSet.next()) {
            List<String> currentSchedule = new ArrayList<>();
            currentSchedule.add(idolScheduleSet.getString("day"));
            currentSchedule.add(idolScheduleSet.getString("startTime"));
            currentSchedule.add(idolScheduleSet.getString("endTime"));
            idolSchedule.add(currentSchedule);
        }
    }

    /**
     * Retrieves the current List of idolDetails.
     * @return The current idolDetails.
     */
    public List<String> getIdolDetails() {
        return idolDetails;
    }

    /**
     * Retrieves the current list of lists of idolSchedule.
     * @return The current idolSchedule.
     */
    public List<List<String>> getIdolSchedule() {
        return idolSchedule;
    }

    /**
     * Retrieves the current idol.
     * @return The current idol.
     */
    public Idol getIdol() {
        return idol;
    }
}
