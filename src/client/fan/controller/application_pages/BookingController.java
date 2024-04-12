package client.fan.controller.application_pages;

import client.fan.controller.FanApplicationController;
import client.fan.model.application_pages.BookingModel;
import client.fan.view.application_pages.BookingView;
import shared.res.CustomizedMessageDialog;
import shared.res.Resources;
import shared.res.Stylesheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The BookingController processes the user requests. Based on the user request, the BookingController
 * defines methods and invokes methods in the View and Model to accomplish the requested action.
 */
public class BookingController {
    /**
     * The view.
     */
    private BookingView view;
    /**
     * The model.
     */
    private BookingModel model;
    /**
     * The main application controller.
     */
    private FanApplicationController mainController;
    /**
     * The stylesheet.
     */
    private Stylesheet style = new Stylesheet();

    /**
     * Constructs a BookingController with a specified view and specified model.
     *
     * @param view  The specified view.
     * @param model The specified model.
     */
    public BookingController(BookingView view, BookingModel model, FanApplicationController mainController) {
        this.view = view;
        this.model = model;
        this.mainController = mainController;

        // constants / variables
        populateIdolDetails();
        populateReservationDetails();
        populateReservationComboBoxes();

        // action listeners
        view.getBtnBack().addActionListener(e -> {
            mainController.getView().getCardLayout().show(mainController.getView().getPnlCards(), "home");
        });

        view.getCmbDate().addActionListener(e -> {
            List<String> availTimes;
            try {
                view.getCmbTime().removeAllItems();
                availTimes = model.getAvailableTimes(String.valueOf(view.getCmbDate().getSelectedItem()))
                        .stream().distinct().toList();
                for (String availTime : availTimes) {
                    view.getCmbTime().addItem(availTime);
                }
            } catch (Exception ex) {
            }
        });

        view.getCmbDuration().addActionListener(e -> {
            double rate;
            int duration = (int) view.getCmbDuration().getSelectedItem();
            if (view.getRadVidCall().isSelected()) {
                rate = model.getIdol().getVideoCallRate();
            } else {
                rate = model.getIdol().getVoiceCallRate();
            }
            view.getTxtAmount().setText("Php " + (rate * ((double) duration / 5)));
        });

        view.getBtnBook().addActionListener(new BookingListener());

        view.getRadVidCall().addActionListener(e -> {
            double rate;
            int duration = (int) view.getCmbDuration().getSelectedItem();
            if (view.getRadVidCall().isSelected()) {
                rate = model.getIdol().getVideoCallRate();
            } else {
                rate = model.getIdol().getVoiceCallRate();
            }
            view.getTxtAmount().setText("Php " + (rate * ((double) duration / 5)));
        });

        view.getRadVoiceCall().addActionListener(e -> {
            double rate;
            int duration = (int) view.getCmbDuration().getSelectedItem();
            if (view.getRadVidCall().isSelected()) {
                rate = model.getIdol().getVideoCallRate();
            } else {
                rate = model.getIdol().getVoiceCallRate();
            }
            view.getTxtAmount().setText("Php " + (rate * ((double) duration / 5)));
        });

        view.setFbListener(new SocialsListener(model.getIdolDetails().get(3), 'f'));
        view.setIgListener(new SocialsListener(model.getIdolDetails().get(4), 'i'));
        view.setXListener(new SocialsListener(model.getIdolDetails().get(5), 'x'));

        // mouse listeners
        view.getBtnBook().addMouseListener(new Resources.CursorChanger(view.getBtnBook()));
        view.getBtnBack().addMouseListener(new Resources.CursorChanger(view.getBtnBack()));
        view.getBtnIdolFb().addMouseListener(new Resources.CursorChanger(view.getBtnIdolFb()));
        view.getBtnIdolIg().addMouseListener(new Resources.CursorChanger(view.getBtnIdolIg()));
        view.getBtnIdolX().addMouseListener(new Resources.CursorChanger(view.getBtnIdolX()));

        // focus listeners

        view.repaint();
        view.revalidate();
    }

    /**
     * Processes the clicking of idol social media links.
     */
    static class SocialsListener implements ActionListener {
        /**
         * The URL.
         */
        private String url;

        /**
         * Constructs a SocialsListener with a specified socialsLink.
         *
         * @param socialsLink The specified socialsLink.
         */
        public SocialsListener(String socialsLink, char socMed) {
            url = "";

            switch (socMed) {
                case 'i' -> url = "https://www.instagram.com/" + socialsLink;
                case 'x' -> url = "https://www.x.com/" + socialsLink;
                case 'f' -> url = "https://www." + socialsLink;
            }
        }

        /**
         * The action requested.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(new URI(url));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            } else {
                Runtime runtime = Runtime.getRuntime();
                try {
                    runtime.exec("xdg-open " + url);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * Processes booking.
     */
    class BookingListener implements ActionListener {
        /**
         * Processes the booking of the fan user.
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            int fanID = 0;
            int idolId = 0;
            String sessionType = null;
            String date = null;
            String startTime = null;
            int duration = 0;
            double amount = 0.0;

            try {
                fanID = model.getUser().getFanID();
                idolId = model.getIdol().getIdolID();
                sessionType = null;
                date = Objects.requireNonNull(view.getCmbDate().getSelectedItem()).toString();
                startTime = Objects.requireNonNull(view.getCmbTime().getSelectedItem()).toString();
                duration = (int) view.getCmbDuration().getSelectedItem();
                amount = Double.parseDouble(view.getTxtAmount().getText().replace("Php ", ""));

                if (view.getRadVoiceCall().isSelected()) {
                    sessionType = "Voice Call";
                } else if (view.getRadVidCall().isSelected()) {
                    sessionType = "Video Call";
                }

                if (!(duration == 0) && (!view.getRadVidCall().isSelected() || !view.getRadVoiceCall().isSelected())) {
                    model.addBooking(idolId, sessionType, date, startTime, duration, amount, fanID, 0);
                    new CustomizedMessageDialog("Booking",
                            style.iconSuccess,
                            "Booking Confirmed",
                            "You have successfully booked this idol. Thank you!",
                            "Close",
                            style.purple,
                            style.purple,
                            style.black,
                            style.purple,
                            false);
                    view.getCmbDate().setSelectedIndex(0);
                    view.getCmbDuration().setSelectedIndex(0);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                view.getCmbDate().setSelectedIndex(0);
                view.getCmbDuration().setSelectedIndex(0);
                new CustomizedMessageDialog("Booking",
                        style.iconFailed,
                        "Booking Failed",
                        "Please check booking details and try again.",
                        "Close",
                        style.red,
                        style.purple,
                        style.black,
                        style.purple,
                        false);
            }
        }
    }

    /**
     * Populates the necessary JFrame components of the idol's details.
     */
    private void populateIdolDetails() {
        ImageIcon icon = new ImageIcon(model.getIdolDetails().get(0));
        Image image = icon.getImage().getScaledInstance(533, 300, Image.SCALE_SMOOTH);
        view.getLblIdolPfp().setIcon(new ImageIcon(image));

        view.getLblIdolName().setText(model.getIdolDetails().get(1));
        view.getLblIdolType().setText(model.getIdolDetails().get(2));
        view.getLblIdolQuote().setText(model.getIdolDetails().get(6));
        view.getTxaIdolBio().setText(model.getIdolDetails().get(7));
        view.getLblVidRate().setText("Php " + model.getIdolDetails().get(8) + " / 5 minutes");
        view.getLblVoiceRate().setText("Php " + model.getIdolDetails().get(9) + " / 5 minutes");
    }

    /**
     * Populates the necessary JFrame components of the idol's reservation details.
     */
    private void populateReservationDetails() {
        StringBuilder sbDaysAvail = new StringBuilder();
        StringBuilder sbTimeAvail = new StringBuilder();

        // view.getCmbDuration().removeAllItems();

        try {
            model.getAvailSchedule(model.getIdol());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < model.getIdolSchedule().size(); i++) {
            List<String> schedule = model.getIdolSchedule().get(i);
            String date = schedule.get(0);
            String time = schedule.get(1) + " - " + schedule.get(2);
            sbDaysAvail.append(date).append("\n");
            sbTimeAvail.append(time).append("\n");
        }
        view.getTxaAvailDays().setText(sbDaysAvail.toString());
        view.getTxaAvailTime().setText(sbTimeAvail.toString());
    }

    private void populateReservationComboBoxes() {
        List<String> dates = model.getAvailableDatesToBook()
                .stream().distinct().toList();
        for (String date : dates) {
            view.getCmbDate().addItem(date);
        }
    }
}
