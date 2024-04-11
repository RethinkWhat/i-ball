package client.fan.controller.application_pages;

import client.fan.controller.FanApplicationController;
import client.fan.model.application_pages.MyIdolsModel;
import client.fan.view.application_pages.MyIdolsView;
import client.idol.model.application_pages.FanbaseModel;
import client.fan.view.application_pages.VirtualMeetupView;
import shared.res.DataPB;
import shared.res.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        view.setJoinListener(new JoinListener(view,model));
        view.setReturnListener(new ReturnListener());

        // mouse listeners
        view.getBtnJoin().addMouseListener(new Resources.CursorChanger(view.getBtnJoin()));
        view.getBtnSearch().addMouseListener(new Resources.CursorChanger(view.getBtnSearch()));

        // focus listeners
        view.getTxtSearchbar().addFocusListener(new Resources.TextFieldFocus(view.getTxtSearchbar(), "Search date (YYYY-MM-DD)"));
    }

    class ReturnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getLblDate().setText(model.getDateToday());
            List<List<String>> sessions;
            try {
                sessions = model.searchFanSessions(model.getDateToday());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            view.getTblFanbaseModel().addRow(sessions.toArray());
            view.getBtnReturn().setEnabled(false);
            view.getTxtSearchbar().setText("Search date (YYYY-MM-DD)");
        }
    }


    public void showAllFanSessions() throws SQLException {
        view.getLblDate().setText(model.getDateToday());
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
            LocalDate date = LocalDate.parse(searchKey);
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                view.getLblDate().setText(date.format(dateFormat));
                // Clear existing table rows
                view.getTblFanbaseModel().setRowCount(0);

                // Search for booking sessions with the same date from the search input
                List<List<String>> sessionList = model.searchFanSessions(searchKey);
                for (List<String> currSession : sessionList) {
                    view.getTblFanbaseModel().addRow(currSession.toArray());
                }
                view.getBtnReturn().setEnabled(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    class JoinListener implements ActionListener {
        /**
         * The view.
         */
        private MyIdolsView view;
        /**
         * The model.
         */
        private MyIdolsModel model;

        public JoinListener(MyIdolsView view, MyIdolsModel model) {
            this.view = view;
            this.model = model;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getTblFanbase().getSelectedRow();
            if (selectedRow != -1) {
                // this retrieves data from the selected row
                String idol = (String) view.getTblFanbase().getValueAt(selectedRow, 1);
                String startTime = (String) view.getTblFanbase().getValueAt(selectedRow, 0);
                String duration = (String) view.getTblFanbase().getValueAt(selectedRow, 3);

                if (model.compareBookingToTimeNow(view.getLblDate().getText(),startTime,duration)) {
                    System.out.println(model.getDateToday());
                    System.out.println(view.getLblDate().getText());
                    openVirtualMeetupView(idol, duration, model);
                } else {
                    JOptionPane.showMessageDialog(view, "Please check the session time and try again.");
                }

            } else {
                JOptionPane.showMessageDialog(view, "Please select a session to join.");
            }
        }

        private void openVirtualMeetupView(String idol, String duration, MyIdolsModel model) {
            VirtualMeetupView virtualMeetupView = new VirtualMeetupView();

            int durationInSeconds = convertDurationToSeconds(duration);
            startTimer(durationInSeconds, virtualMeetupView.getLblTimer());
            virtualMeetupView.getLblTimer().setText(duration);
            virtualMeetupView.getLblIdolName().setText(idol);

            String idolPFP = DataPB.getIdolPFP(idol);
            ImageIcon idolPfp = new ImageIcon(idolPFP);
            Image idolPfpImage = idolPfp.getImage();
            Image idolResized = idolPfpImage.getScaledInstance(226, 128, Image.SCALE_SMOOTH);
            virtualMeetupView.getLblIdolPfp().setIcon(new ImageIcon(idolResized));

            ImageIcon pfp = new ImageIcon(model.getUser().getPpAddress());
            Image pfpImage = pfp.getImage();
            Image resized = pfpImage.getScaledInstance(113, 64, Image.SCALE_SMOOTH);
            virtualMeetupView.getLblFanPfp().setIcon(new ImageIcon(resized));

            JFrame virtualMeetupFrame = new JFrame("Virtual Meetup");
            virtualMeetupFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent default closing behavior
            virtualMeetupFrame.getContentPane().add(virtualMeetupView);
            virtualMeetupFrame.pack();
            virtualMeetupFrame.setLocationRelativeTo(null);
            virtualMeetupFrame.setVisible(true);

            virtualMeetupView.getBtnEndCall().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int choice = JOptionPane.showConfirmDialog(view, "Are you sure you want to end call?", "End Call", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        virtualMeetupFrame.dispose();
                    } else if (choice == JOptionPane.NO_OPTION) {
                        // Do nothing, or optionally add some behavior here
                    }
                }
            });
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
}
