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
    }

    public List<Idol> getAllIdols(ResultSet idols) throws SQLException {
        List<Idol> idolList = new ArrayList<>();

        //WILL CONTINUE AFTER USERNAME IS ADDED IN THE SCHEMA
        while (idols.next()){
            //idolList.add(new Idol());
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
    public static void main(String[] args) throws SQLException {

    }
}
