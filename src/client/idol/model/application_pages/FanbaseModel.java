package client.idol.model.application_pages;

import shared.res.DataPB;
import shared.res.Idol;
import shared.res.Session;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
        Calendar cal = Calendar.getInstance();
        return sdf.format(cal.getTime());
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
