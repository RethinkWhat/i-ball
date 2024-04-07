package client.fan.model.application_pages;

import shared.res.DataPB;
import shared.res.Idol;
import shared.res.Session;

import javax.swing.table.DefaultTableModel;
import java.sql.DataTruncation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

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
     * The available dates to book.
     */
    private List<String> availableDatesToBook;
    /**
     * The available time slots to book.
     */
    private List<String> availableSlotsToBook;

    /**
     * Constructs a BookingModel with a specified idol.
     *
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

        try {
            getAvailSchedule(idol);
            availableDatesToBook = getAvailableDates();
            // availableSlotsToBook = getAvailTimes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the available schedule of the idol.
     *
     * @param idol The specified idol.
     * @throws SQLException If error or exception occurs.
     */
    public void getAvailSchedule(Idol idol) throws SQLException {
        DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
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

    /**
     * Retrieves the available times on a specified date.
     * The existing sessions of the user on that specified is considered.
     * @param date The specified date.
     * @return String list of available times.
     * @throws SQLException If error or exception occurs.
     */
    public List<String> getAvailTimes(String date) throws SQLException {
        List<String> availableDates = getAvailableDates();
        List<List<String>> sessions = getIdolSessions(idol);
        List<String> availableSlots = new ArrayList<>();

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       //DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMMM dd, yyyy", Locale.ENGLISH);
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        LocalDate givenDate = LocalDate.parse(date, dateFormat);

        for (List<String> schedule : getIdolSchedule()) {
            DayOfWeek dayOfWeek = DayOfWeek.valueOf(schedule.get(0).toUpperCase());
            DayOfWeek specifiedDateDay = givenDate.getDayOfWeek();

            if (specifiedDateDay.equals(dayOfWeek)) {
                LocalTime endTime = LocalTime.parse(schedule.get(1), timeFormat);

                for (String dates : availableDates) {
                    LocalDate currDate = LocalDate.parse(dates);
                    if (dayOfWeek.equals(currDate.getDayOfWeek())) {
                        for (List<String> session : sessions) {
                            LocalTime startTime = LocalTime.parse(session.get(1));
                            LocalTime duration = LocalTime.parse(session.get(2));
                            LocalTime totalTime = startTime.plusHours(duration.getHour()).plusMinutes(duration.getMinute()).plusSeconds(duration.getSecond());

                            if (startTime.isAfter(endTime)) {
                                availableSlots.add(endTime.format(timeFormat));
                                availableSlots.add(startTime.format(timeFormat));
                            }
                            endTime = totalTime;
                        }
                        if (endTime.isBefore(LocalTime.parse(schedule.get(2)))) { // update to idol schedule availability
                            availableSlots.add(endTime.format(timeFormat));
                        }
                    }
                }
            }
        }
        return availableSlots;
    }

    /**
     * Retrieves the available dates that the user can book.
     * The available dates are only the days that the idol has specified (e.g., dates that fall on Monday).
     * @return String list of available dates.
     * @throws SQLException If error or exception occurs.
     */
    public List<String> getAvailableDates() throws SQLException {
        YearMonth currentMonth = YearMonth.now();
        List<String> availableDates = new ArrayList<>();
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (List<String> schedule : getIdolSchedule()) {

            for (int i = 1; i < currentMonth.lengthOfMonth(); i++) {
                DayOfWeek dayOfWeek = DayOfWeek.valueOf(schedule.getFirst().toUpperCase());

                LocalDate date = currentMonth.atDay(i);
                LocalDate now = LocalDate.now();

                if (date.getDayOfWeek().equals(dayOfWeek) && (date.isAfter(now) || date.equals(now))) {
                    availableDates.add(date.format(formatter));
                }
            }

        }

        return availableDates;
    }

    public void addBooking(Session session) throws SQLException {

    }

    /**
     * Retrieves all the sessions of the idol.
     *
     * @param idol The specified idol.
     * @throws SQLException If error or exception occurs.
     */
    public List<List<String>> getIdolSessions(Idol idol) throws SQLException {
        List<List<String>> idolSessions = new ArrayList<>();
        ResultSet idolSessionsSet = DataPB.getAllIdolSession(idol);

        while (idolSessionsSet.next()) {
            List<String> session = new ArrayList<>();
            session.add(idolSessionsSet.getString("date"));
            session.add(idolSessionsSet.getString("startTime"));
            session.add(idolSessionsSet.getString("duration"));
            idolSessions.add(session);
        }
        return idolSessions;
    }

    /**
     * Retrieves the current List of idolDetails.
     *
     * @return The current idolDetails.
     */
    public List<String> getIdolDetails() {
        return idolDetails;
    }

    /**
     * Retrieves the current list of lists of idolSchedule.
     *
     * @return The current idolSchedule.
     */
    public List<List<String>> getIdolSchedule() {
        return idolSchedule;
    }

    /**
     * Retrieves the current idol.
     *
     * @return The current idol.
     */
    public Idol getIdol() {
        return idol;
    }

    public List<String> getAvailableDatesToBook() {
        return availableDatesToBook;
    }

    public List<String> getAvailableSlotsToBook() {
        return availableSlotsToBook;
    }
}
