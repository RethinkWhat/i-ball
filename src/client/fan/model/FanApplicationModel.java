package client.fan.model;

import shared.res.DataPB;
import shared.res.Idol;
import shared.res.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The FanApplicationView provides methods to invoke SQL connection, SQL statements to provide
 * fan user information.
 */
public class FanApplicationModel {

    private User user;

    public FanApplicationModel(User user) {
        this.user = user;
        System.out.println(user.getPpAddress());
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * FOR TESTING PURPOSES ONLY
     * @param args
     */
    public static void main(String[] args) throws Exception {
        FanApplicationModel model = new FanApplicationModel(new User(1,"a","b","c","d","e"));

        List<Idol> allIdols = model.getAllIdols();

        for (Idol idol : allIdols){
            System.out.println(idol.toString());
        }
    }
}
