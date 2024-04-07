package client.fan.controller.application_pages;

import client.fan.controller.FanApplicationController;
import client.fan.model.application_pages.MyIdolsModel;
import client.fan.view.application_pages.MyIdolsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
    public void showAllFanSessions() throws SQLException {
        // Clear existing table rows
        view.getTblFanbaseModel().setRowCount(0);

        // Get all booking sessions of the user and populate the table
        List<List<String>> sessionList = model.getAllFanSessions();
        for (List<String> currSession : sessionList) {
            view.getTblFanbaseModel().addRow(currSession.toArray());
        }
    }

    class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchKey = view.getTxtSearchbar().getText();
            try {
                // Clear existing table rows
                view.getTblFanbaseModel().setRowCount(0);

                // Search for booking sessions with the same date from the search input
                List<List<String>> sessionList = model.searchFanSessions(searchKey);
                for (List<String> currSession : sessionList) {
                    view.getTblFanbaseModel().addRow(currSession.toArray());
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
