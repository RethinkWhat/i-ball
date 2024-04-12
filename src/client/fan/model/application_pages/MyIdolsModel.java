package client.fan.model.application_pages;

import shared.res.DataPB;
import shared.res.Fan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyIdolsModel {

    private Fan fan;

    public MyIdolsModel(Fan fan){
        this.fan = fan;
    }
    public Fan getUser() {
        return fan;
    }

    public void setUser(Fan fan) {
        this.fan = fan;
    }

    public List<List<String>> searchFanSessions(String date) throws SQLException {
        ResultSet fanSessionsSet = DataPB.searchFanSessions(this.fan.getFanID(),date);
        List<List<String>> fanSessions = new ArrayList<>();

        while (fanSessionsSet.next()){
            List<String> currSession = new ArrayList<>();

            currSession.add(String.valueOf(fanSessionsSet.getTime("startTime")));
            currSession.add(fanSessionsSet.getString("idolName"));
            currSession.add(fanSessionsSet.getString("sessionType"));
            currSession.add(String.valueOf(fanSessionsSet.getTime("duration")));

            fanSessions.add(currSession);
        }


        return fanSessions;
    }

    public boolean compareBookingToTimeNow(String date, String startTime, String duration) {
        Calendar cal = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        if (!sdf.format(cal.getTime()).equals(date)) {
            return false;
        }
        String[] startList = startTime.split(":");
        String[] durationToAdd = duration.split(":");

        Calendar timeOfBooking = Calendar.getInstance();

        Calendar endOfBooking = Calendar.getInstance();


        timeOfBooking.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), Integer.parseInt(startList[0]), Integer.parseInt(startList[1]), Integer.parseInt(startList[2]));

        endOfBooking.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),
                Integer.parseInt(startList[0]) + Integer.parseInt(durationToAdd[0]),
                Integer.parseInt(startList[1]) + Integer.parseInt(durationToAdd[1]),
                Integer.parseInt(startList[2]) + Integer.parseInt(durationToAdd[2]));

        return (cal.compareTo(timeOfBooking) >= 0) && (cal.compareTo(endOfBooking) < 0);
    }

    public List<List<String>> getAllFanSessions() throws SQLException {
        ResultSet fanSessionsSet = DataPB.getAllSessions(this.fan.getFanID());
        List<List<String>> fanSessions = new ArrayList<>();

        while (fanSessionsSet.next()) {
            List<String> currSession = new ArrayList<>();

            currSession.add(String.valueOf(fanSessionsSet.getTime("startTime")));
            currSession.add(fanSessionsSet.getString("idolName"));
            currSession.add(fanSessionsSet.getString("sessionType"));
            currSession.add(String.valueOf(fanSessionsSet.getTime("duration")));

            fanSessions.add(currSession);
        }

        return fanSessions;
    }

    /**
     * Returns the String representation of the date today
     * @return
     */
    public String getDateToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        return sdf.format(cal.getTime());
    }

    public String convertDate(String search) {
            LocalDate date = LocalDate.parse(search, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }


    /**
     * FOR TESTING PURPOSES ONLY
     * @param args
     */
    public static void main(String[] args) throws SQLException {
        MyIdolsModel model = new MyIdolsModel(DataPB.getUser(1));

        System.out.println(model.searchFanSessions("2024-04-07"));
    }
}
