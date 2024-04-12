package client.idol.controller.application_pages;

import client.idol.model.application_pages.FanbaseModel;
import client.idol.view.application_pages.FanbaseView;
import client.idol.view.application_pages.VirtualMeetupView;
import shared.res.DataPB;
import shared.res.Resources;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.crypto.Data;
import java.awt.*;
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
        view.setJoinListener(new JoinListener(view, model));
        view.setReturnListener(new ReturnListener());


        // mouse listeners
        view.getBtnJoin().addMouseListener(new Resources.CursorChanger(view.getBtnJoin()));
        view.getBtnReturn().addMouseListener(new Resources.CursorChanger(view.getBtnReturn()));

        //Set default date
        view.getTablePanel().setDate(model.getDateToday());
        String[][] sessions = model.getSessionsOnDate(model.getDateToday());
        view.getTablePanel().populateTable(sessions);

        // focus listeners
        view.getTxtSearchbar().addFocusListener(new Resources.TextFieldFocus(view.getTxtSearchbar(), "Search date (YYYY-MM-DD)"));
        view.getTxtSearchbar().addActionListener(new SearchListener());

    }

    /**
     * Processes the joining of the session.
     */
    class JoinListener implements ActionListener {
        private FanbaseView view;

        private FanbaseModel model;

        public JoinListener(FanbaseView view, FanbaseModel model) {
            this.view = view;
            this.model = model;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getTablePanel().getTblFanbase().getSelectedRow();
            if (selectedRow != -1) {
                // this retrieves data from the selected row
                String fan = (String) view.getTablePanel().getTblFanbase().getValueAt(selectedRow, 1);
                String startTime = (String) view.getTablePanel().getTblFanbase().getValueAt(selectedRow, 0);
                String duration = (String) view.getTablePanel().getTblFanbase().getValueAt(selectedRow, 3);

                int sessionID = DataPB.getSessionID(model.getIdol().getIdolID(), DataPB.getFanID(fan), model.getDateToday(),
                        String.valueOf(view.getTablePanel().getTblFanbaseModel().getValueAt(selectedRow, 0)));
                if (DataPB.getSessionStatus(sessionID) != 0) {
                    if (model.compareBookingToTimeNow(view.getTablePanel().getDate(),startTime,duration)) {
                        System.out.println(model.getDateToday());
                        System.out.println(view.getTablePanel().getDate());
                        openVirtualMeetupView(fan, duration, model);
                    } else {
                        JOptionPane.showMessageDialog(view, "Please check the session time and try again.");
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Session has ended. Please select a different session.");
                }
            } else {
                JOptionPane.showMessageDialog(view, "Please select a session to join.");
            }
        }
        private void openVirtualMeetupView(String fan, String duration, FanbaseModel model) {
            int selectedRow = view.getTablePanel().getTblFanbase().getSelectedRow();

            VirtualMeetupView virtualMeetupView = new VirtualMeetupView();

            int durationInSeconds = convertDurationToSeconds(duration);
            startTimer(durationInSeconds, virtualMeetupView.getLblTimer());
            virtualMeetupView.getLblTimer().setText(duration);
            virtualMeetupView.getLblFanName().setText(fan);

            String userPFP = DataPB.getUserPFP(fan);
            ImageIcon userPfp = new ImageIcon(userPFP);
            Image userPfpImage = userPfp.getImage();
            Image userResized = userPfpImage.getScaledInstance(226, 128, Image.SCALE_SMOOTH);
            virtualMeetupView.getLblFanPfp().setIcon(new ImageIcon(userResized));

            ImageIcon pfp = new ImageIcon(model.getIdol().getProfilePictureAddress());
            Image pfpImage = pfp.getImage();
            Image resized = pfpImage.getScaledInstance(113, 64, Image.SCALE_SMOOTH);
            virtualMeetupView.getLblIdolPfp().setIcon(new ImageIcon(resized));

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
                        int sessionID = DataPB.getSessionID(model.getIdol().getIdolID(), DataPB.getFanID(fan), model.getDateToday(),
                                String.valueOf(view.getTablePanel().getTblFanbaseModel().getValueAt(selectedRow, 0)));
                        DataPB.setSessionToComplete(sessionID);
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

    /**
     * Processes the returning to the current date view.
     */
    class ReturnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[][] sessions = model.getSessionsOnDate(model.getDateToday());
            view.getTablePanel().populateTable(sessions);
            view.getTablePanel().setDate(model.getDateToday());
            view.getBtnReturn().setEnabled(false);
            view.getTxtSearchbar().setText("Search date (YYYY-MM-DD)");
        }
    }
    class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchedDate = view.getTxtSearchbar().getText();
            String[][] sessions = model.getSessionsOnDate(searchedDate);
            view.getTablePanel().populateTable(sessions);
            view.getTablePanel().setDate(searchedDate);
            view.getBtnReturn().setEnabled(true);
        }
    }
}