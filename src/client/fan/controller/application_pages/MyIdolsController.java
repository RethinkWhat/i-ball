package client.fan.controller.application_pages;

import client.fan.controller.FanApplicationController;
import client.fan.model.application_pages.MyIdolsModel;
import client.fan.view.application_pages.MyIdolsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class MyIdolsController {
    private MyIdolsView view;

    private MyIdolsModel model;

    private FanApplicationController mainController;

    public MyIdolsController(MyIdolsView view, MyIdolsModel model, FanApplicationController mainController){
        this.view = view;
        this.model = model;
        this.mainController = mainController;

        // action listeners
        view.getBtnSearch().addActionListener(new SearchListener());

        // mouse listeners

        // set default date

        // focus listeners
    }

    class SearchListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchKey = view.getTxtSearchbar().getText();
            try {
                List<List<String>> sessionList = model.searchFanSessions(searchKey);
                for (List<String> currSession : sessionList){
                    view.getTblFanbaseModel().addRow(currSession.toArray());
                    System.out.println(currSession.toString());
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
}
