package client.idol.controller.application_pages;

import client.idol.model.application_pages.FanbaseModel;
import client.idol.view.application_pages.FanbaseView;
import client.idol.view.application_pages.VirtualMeetupView;
import shared.res.Resources;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The FanbaseController processes the user requests. Based on the user request, the FanbaseController
 * defines methods and invokes methods in the View and Model to accomplish the requested action.
 */
public class FanbaseController {
    /**
     * The view.
     */
    private FanbaseView view;
    /**
     * The model.
     */
    private FanbaseModel model;

    /**
     * Constructs a FanbaseController with a specified view and model.
     * @param view The view.
     * @param model The model.
     */
    public FanbaseController(FanbaseView view, FanbaseModel model) {
        this.view = view;
        this.model = model;

        // action listeners
        view.setJoinListener(new JoinListener(view));
        view.setReturnListener(new ReturnListener());


        // mouse listeners
        view.getBtnJoin().addMouseListener(new Resources.CursorChanger(view.getBtnJoin()));
        view.getBtnReturn().addMouseListener(new Resources.CursorChanger(view.getBtnReturn()));

        //Set default date
        view.getTablePanel().setDate(model.getDateToday());
        String[][] sessions = model.getSessionsOnDate(model.getDateToday());
        view.getTablePanel().populateTable(sessions);

        // focus listeners
        view.getTxtSearchbar().addFocusListener(new Resources.TextFieldFocus(view.getTxtSearchbar(), "Search date (MM/DD/YYYY)"));
        view.getTxtSearchbar().addActionListener(new SearchListener());
        view.getTablePanel().getTblFanbase().getSelectionModel().addListSelectionListener(new TableSelectionListener());
    }

    /**
     * Processes the joining of the session.
     */
    class JoinListener implements ActionListener {
        private FanbaseView view;
        public JoinListener(FanbaseView view) {
            this.view = view;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getTablePanel().getTblFanbase().getSelectedRow();
            if (selectedRow != -1) {
                // this retrieves data from the selected row
                String time = (String) view.getTablePanel().getTblFanbase().getValueAt(selectedRow, 0);
                String fan = (String) view.getTablePanel().getTblFanbase().getValueAt(selectedRow, 1);
                String type = (String) view.getTablePanel().getTblFanbase().getValueAt(selectedRow, 2);
                String duration = (String) view.getTablePanel().getTblFanbase().getValueAt(selectedRow, 3);
                System.out.println(duration);
                openVirtualMeetupView(time, fan, type , duration);

            } else {
                JOptionPane.showMessageDialog(view, "Please select a session to join.");
            }
        }
        private void openVirtualMeetupView(String time, String fan, String type ,String duration) {
            VirtualMeetupView virtualMeetupView = new VirtualMeetupView();

            int durationInSeconds = convertDurationToSeconds(duration);
            startTimer(durationInSeconds,virtualMeetupView.getLblTimer());
            virtualMeetupView.getLblTimer().setText(duration);
            virtualMeetupView.getLblFanName().setText(fan);

            JFrame virtualMeetupFrame = new JFrame("Virtual Meetup");
            virtualMeetupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            virtualMeetupFrame.getContentPane().add(virtualMeetupView);
            virtualMeetupFrame.pack();
            virtualMeetupFrame.setLocationRelativeTo(null);
            virtualMeetupFrame.setVisible(true);
        }
        private int convertDurationToSeconds(String duration) {
            String[] hms = duration.split(":");
            int hours = Integer.parseInt(hms[0]);
            int minutes = Integer.parseInt(hms[1]);
            int seconds = Integer.parseInt(hms[2]);
            return hours * 3600 + minutes * 60 + seconds;
        }
        private void startTimer(int durationInSeconds, JLabel label) {
            final int[] remainingTime = {durationInSeconds};
            Timer timer = new Timer(1000, e -> {
                if (remainingTime[0] > 0) {
                    remainingTime[0]--;
                    label.setText(formatDuration(remainingTime[0]));
                } else {
                    ((Timer) e.getSource()).stop();
                    label.setText("Session ended");
                }
            });
            timer.setInitialDelay(0);
            timer.start();
        }
        private String formatDuration(int totalSeconds) {
            int hours = totalSeconds / 3600;
            int minutes = (totalSeconds % 3600) / 60;
            int seconds = totalSeconds % 60;
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }
    }

    /**
     * Processes the returning to the current date view.
     */
    class ReturnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }
    class TableSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = view.getTablePanel().getTblFanbase().getSelectedRow();
                if (selectedRow != -1) {
                    String time = (String) view.getTablePanel().getTblFanbase().getValueAt(selectedRow, 0);
                    String fan = (String) view.getTablePanel().getTblFanbase().getValueAt(selectedRow, 1);
                    String type = (String) view.getTablePanel().getTblFanbase().getValueAt(selectedRow, 2);
                    System.out.println("Selected Row: "+ selectedRow +"Time=" + time + ", Fan=" + fan + ", Type=" + type);
                }
            }
        }
    }
    class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchedDate = view.getTxtSearchbar().getText();
            String[][] sessions = model.getSessionsOnDate(searchedDate);
            view.getTablePanel().populateTable(sessions);
        }
    }
}