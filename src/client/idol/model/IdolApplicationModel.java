package client.idol.model;

import client.idol.model.application_pages.FanbaseModel;
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

    FanbaseModel fanbaseModel;

    public IdolApplicationModel(Idol idol) {
        this.idol = idol;
        fanbaseModel = new FanbaseModel(getIdol());
    }

    public Idol getIdol() {
        return idol;
    }

    public void setIdol(Idol idol) {
        this.idol = idol;
    }

    public FanbaseModel getFanbaseModel() {
        return fanbaseModel;
    }

    public void setFanbaseModel(FanbaseModel fanbaseModel) {
        this.fanbaseModel = fanbaseModel;
    }
}
