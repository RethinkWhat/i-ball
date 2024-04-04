package client.idol.model.application_pages;

import shared.res.DataPB;
import shared.res.Idol;
import shared.res.Session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * The FanbaseModel provides methods for displaying current bookings of the user.
 */
public class FanbaseModel {

    private Idol idol;

    private ArrayList<Session> sessions;
    public FanbaseModel(Idol idol) {
        this.idol = idol;
        sessions = DataPB.getIdolSessions(getIdol().getIdolID());
    }

    public Idol getIdol() {
        return idol;
    }

    public void setIdol(Idol idol) {
        this.idol = idol;
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public String getDateToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        Calendar cal = Calendar.getInstance();
        return sdf.format(cal.getTime());
    }

    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }


}
