package client.fan.controller.application_pages;

import client.fan.controller.FanApplicationController;
import client.fan.model.application_pages.BookingModel;
import client.fan.view.application_pages.BookingView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
     * Constructs a BookingController with a specified view and specified model.
     * @param view The specified view.
     * @param model The specified model.
     */
    public BookingController(BookingView view, BookingModel model, FanApplicationController mainController) {
        this.view = view;
        this.model = model;
        this.mainController = mainController;

        // constants / variables
        populateDetails();

        // action listeners
        view.getBtnBack().addActionListener(e -> {
            mainController.getView().getCardLayout().show(mainController.getView().getPnlCards(), "home");
        });

        view.setFbListener(new SocialsListener(model.getIdolDetails().get(3), 'f'));
        view.setIgListener(new SocialsListener(model.getIdolDetails().get(4), 'i'));
        view.setXListener(new SocialsListener(model.getIdolDetails().get(5), 'x'));

        // mouse listeners

        // focus listeners
    }

    /**
     * Processes the clicking of idol social media links.
     */
    class SocialsListener implements ActionListener {
        /**
         * The URL of the social media profile.
         */
        private String socialsLink;
        /**
         * The type of social media.
         */
        private char socMed;
        /**
         * The URL.
         */
        private String url;

        /**
         * Constructs a SocialsListener with a specified socialsLink.
         * @param socialsLink The specified socialsLink.
         */
        public SocialsListener(String socialsLink, char socMed) {
            url = "";
            this.socialsLink = socialsLink;
            this.socMed = socMed;

            switch (socMed) {
                case 'i' -> url = "https://www.instagram.com/" + socialsLink;
                case 'x' -> url = "https://www.x.com/" + socialsLink;
                case 'f' -> url = "https://www." + socialsLink;
            }
        }

        /**
         * The action requested.
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Desktop.isDesktopSupported()){
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

     private void populateDetails() {
         ImageIcon icon = new ImageIcon(model.getIdolDetails().getFirst());
         Image image = icon.getImage().getScaledInstance(533,300, Image.SCALE_SMOOTH);
         view.getLblIdolPfp().setIcon(new ImageIcon(image));

         view.getLblIdolName().setText(model.getIdolDetails().get(1));
         view.getLblIdolType().setText(model.getIdolDetails().get(2));
         view.getLblIdolQuote().setText(model.getIdolDetails().get(6));
         view.getTxaIdolBio().setText(model.getIdolDetails().get(7));
     }
}
