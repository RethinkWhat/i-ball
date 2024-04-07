package client.fan.model.application_pages;

import shared.res.DataPB;
import shared.res.User;

import java.awt.geom.RectangularShape;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyIdolsModel {

    private User user;

    public MyIdolsModel(User user){
        this.user = user;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<List<String>> searchFanSessions(String date) throws SQLException {
        ResultSet fanSessionsSet = DataPB.searchFanSessions(this.user.getUserID(),date);
        List<List<String>> fanSessions = new ArrayList<>();

        while (fanSessionsSet.next()){
            List<String> currSession = new ArrayList<>();

            currSession.add(String.valueOf(fanSessionsSet.getTime("startTime")));
            currSession.add(fanSessionsSet.getString("idolName"));
            currSession.add(fanSessionsSet.getString("sessionType"));
            currSession.add(String.valueOf(fanSessionsSet.getTime("duration")));

            fanSessions.add(currSession);
        }


        return fanSessions;
    }

    /**
     * FOR TESTING PURPOSES ONLY
     * @param args
     */
    public static void main(String[] args) throws SQLException {
        MyIdolsModel model = new MyIdolsModel(DataPB.getUser(1));

        System.out.println(model.searchFanSessions("2024-04-07"));
    }
}
