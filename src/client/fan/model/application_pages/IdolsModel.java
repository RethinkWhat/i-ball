package client.fan.model.application_pages;

import shared.res.DataPB;
import shared.res.Idol;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IdolsModel {

    /**
     * This method returns a List of idols with all of their details
     * @return
     * @throws SQLException
     */
    public List<Idol> getAllIdols() throws SQLException {
        ResultSet idolSet = DataPB.getAllIdols();

        List<Idol> idolList = new ArrayList<>();

        while (idolSet.next()) {
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

    /**
     * For testing purposes only
     * @param args
     */
    public static void main(String[] args) throws Exception{
        IdolsModel model = new IdolsModel();

    }
}
