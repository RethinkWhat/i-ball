package client.fan.model.application_pages;

import shared.res.DataPB;
import shared.res.Idol;
import shared.res.Session;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
     * The session of the idol.
     */
    private List<List<String>> idolSessions;
    /**
     * Available dates the user can book.
     */
    private List<String> availDates;

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
        DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        idolSchedule = new ArrayList<>();
        ResultSet idolScheduleSet = DataPB.getIdolSchedule(idol);

        while (idolScheduleSet.next()) {
            List<String> currentSchedule = new ArrayList<>();
            LocalTime startTime = LocalTime.parse(idolScheduleSet.getString("startTime"), parseFormatter);
            LocalTime endTime = LocalTime.parse(idolScheduleSet.getString("endTime"), parseFormatter);

            currentSchedule.add(idolScheduleSet.getString("day"));
            currentSchedule.add(startTime.format(timeFormatter));
            currentSchedule.add(endTime.format(timeFormatter));
            idolSchedule.add(currentSchedule);
        }
    }

    public void getAvailDatesToBook() {
        availDates = new ArrayList<>();

    }

    public void addBooking(Session session) throws SQLException {

    }

    /**
     * Retrieves all the sessions of the idol.
     * @param idol The specified idol.
     * @throws SQLException If error or exception occurs.
     */
    public void getIdolSessions(Idol idol) throws SQLException {
        idolSessions = new ArrayList<>();
        ResultSet idolSessionsSet = DataPB.getAllIdolSession(idol);

        while (idolSessionsSet.next()) {
            List<String> session = new ArrayList<>();
            session.add(idolSessionsSet.getString("date"));
            session.add(idolSessionsSet.getString("startTime"));
            session.add(idolSessionsSet.getString("endTime"));
            idolSessions.add(session);
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

    /**
     * Retrieves the current list of idolSessions.
     * @return The current idolSessions.
     */
    public List<List<String>> getIdolSessions() {
        return idolSessions;
    }
}
