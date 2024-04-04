package client.idol.model;

import shared.res.DataPB;
import shared.res.Idol;
import shared.res.Session;

import java.util.ArrayList;

/**
 * The IdolApplicationView provides methods to invoke SQL connection, SQL statements to provide
 * idol user information.
 */
public class IdolApplicationModel {

    private Idol idol;
    private ArrayList<Session> sessions;

    public IdolApplicationModel(Idol idol) {
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

    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }
}
