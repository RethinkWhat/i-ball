package client.idol.model.application_pages;

import shared.res.DataPB;
import shared.res.Idol;
import shared.res.Session;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The FanbaseModel provides methods for displaying current bookings of the user.
 */
public class FanbaseModel {

    private Idol idol;

    public FanbaseModel(Idol idol) {
        this.idol = idol;
    }

    public Idol getIdol() {
        return idol;
    }

    public void setIdol(Idol idol) {
        this.idol = idol;
    }


    public String getDateToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = new GregorianCalendar(new Locale("English", "PH"));
        return sdf.format(cal.getTime());
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

    public String[][] getSessionsOnDate(String date) {
        ArrayList<Session> sessions = DataPB.getIdolSessions(getIdol().getIdolID(), date);
        String[][] toReturn = new String[sessions.size()][4];

        for (int x =0 ; x < sessions.size(); x ++) {
            sessions.get(x);
            toReturn[x] = new String[]{
                    String.valueOf(sessions.get(x).getStartTime()),
                    sessions.get(x).getFanName(),
                    sessions.get(x).getSessionType(),
                    String.valueOf(sessions.get(x).getDuration())
            };
        }
        return toReturn;
    }



}
