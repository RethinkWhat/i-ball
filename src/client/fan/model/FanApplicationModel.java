package client.fan.model;

import shared.res.DataPB;
import shared.res.Idol;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The FanApplicationView provides methods to invoke SQL connection, SQL statements to provide
 * fan user information.
 */
public class FanApplicationModel {

    public List<Idol> getAllIdols(ResultSet idols) throws SQLException {
        List<Idol> idolList = new ArrayList<>();

        //WILL CONTINUE AFTER USERNAME IS ADDED IN THE SCHEMA
        while (idols.next()){
            //idolList.add(new Idol());
        }

        return idolList;
    }

    /**
     * FOR TESTING PURPOSES ONLY
     * @param args
     */
    public static void main(String[] args) throws SQLException {
        FanApplicationModel model = new FanApplicationModel();
        DataPB.setCon();

        System.out.println(model.getAllIdols(DataPB.getAllIdols()));
    }
}
