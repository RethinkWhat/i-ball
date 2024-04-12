package client.fan.model;

import shared.res.DataPB;
import shared.res.Fan;
import shared.res.Idol;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * The FanApplicationView provides methods to invoke SQL connection, SQL statements to provide
 * fan user information.
 */
public class FanApplicationModel {

    private Fan fan;

    public FanApplicationModel(Fan fan) {
        this.fan = fan;
        System.out.println(fan.getPpAddress());
    }

    public List<Idol> getAllIdols() throws Exception {
        ResultSet idolSet = DataPB.getAllIdols();

        List<Idol> idolList = new ArrayList<>();

        while (idolSet.next()){
            idolList.add(new Idol(
                    idolSet.getInt("idolID"),
                    idolSet.getString("username"),
                    idolSet.getString("idolName"),
                    idolSet.getString("gCashNumber"),
                    idolSet.getString("idolType"),
                    idolSet.getString("idolStatus").equalsIgnoreCase("Verified") ? true : false,
                    idolSet.getDouble("voiceCallRate"),
                    idolSet.getDouble("videoCallRate"),
                    idolSet.getString("fbAccount"),
                    idolSet.getString("xAccount"),
                    idolSet.getString("igAccount"),
                    idolSet.getString("bio"),
                    idolSet.getString("quote"),
                    idolSet.getString("profilePictureAddress"),
                    idolSet.getString("password")
            ));
        }

        return idolList;
    }

    public Fan getUser() {
        return fan;
    }

    public void setUser(Fan fan) {
        this.fan = fan;
    }

}
