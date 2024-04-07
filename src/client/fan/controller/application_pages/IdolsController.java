package client.fan.controller.application_pages;

import client.fan.controller.FanApplicationController;
import client.fan.model.application_pages.BookingModel;
import client.fan.model.application_pages.IdolsModel;
import client.fan.view.application_pages.BookingView;
import client.fan.view.application_pages.IdolsView;
import shared.res.Idol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class IdolsController {
    /**
     * The view.
     */
    private IdolsView view;
    /**
     * The model.
     */
    private IdolsModel model;
    /**
     * The main application controller.
     */
    private FanApplicationController mainController;

    public IdolsController(IdolsView view, IdolsModel model, FanApplicationController mainController) throws SQLException {
        this.view = view;
        this.model = model;
        this.mainController = mainController;

        for (Idol idol : model.getAllIdols()){
            view.addIdolPanel(idol.getProfilePictureAddress(),
                    idol.getIdolName(),
                    idol.getIdolType(),
                    Double.toString(idol.getVideoCallRate()),
                    Double.toString(idol.getVoiceCallRate()));
        }

        for (int i = 0; i < view.getIdolPanels().size(); i++) {
            view.getIdolPanels().get(i).setPfpListener(new ChosenIdolListener(mainController, model.getAllIdols().get(i)));
        }
        view.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = view.getTxtSearchbar().getText();

                // Call the method to perform idol search
                try {
                    List<Idol> searchResults = model.idolSearch(searchTerm);

                    // Clear existing idol panels
                    view.getPnlIdolsContainer().removeAll();
                    view.getIdolPanels().clear();

                    // Add panels for search results
                    for (Idol idol : searchResults) {
                        view.addIdolPanel(idol.getProfilePictureAddress(), idol.getIdolName(), idol.getIdolType(), Double.toString(idol.getVideoCallRate()), Double.toString(idol.getVoiceCallRate()));

                        // Attach ChosenIdolListener to the newly added panel
                        view.getIdolPanels().get(view.getIdolPanels().size() - 1).setPfpListener(new ChosenIdolListener(mainController, idol));
                    }

                    // Refresh the view
                    view.revalidate();
                    view.repaint();
                } catch (SQLException ex) {
                    ex.printStackTrace(); // Handle SQLException appropriately
                }
            }
        });

        };


        /*
        for (int i = 0; i < model.getAllIdols().size(); i++){

            Idol currIdol = model.getAllIdols().get(i);
            view.getPnlIdolsContainer().add(new IdolsView.IdolDetailsPanel(
                   currIdol.getProfilePictureAddress(),
                   currIdol.getIdolName(),
                   currIdol.getIdolType(),
                    Double.toString(currIdol.getVideoCallRate()),
                    Double.toString(currIdol.getVoiceCallRate())
            ));
        }
         */
    }


    /**
     * Processes opening of an idol's details for booking.
     */
    class ChosenIdolListener implements ActionListener {
        /**
         * The main controller of the application.
         */
        private final FanApplicationController mainController;
        /**
         * The chosen idol.
         */
        private final Idol idol;

        /**
         * Constructs a ChosenIdolListener with a specified controller and idol.
         *
         * @param mainController The specified controller.
         * @param idol           The specified idol.
         */
        public ChosenIdolListener(FanApplicationController mainController, Idol idol) {
            this.mainController = mainController;
            this.idol = idol;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            new BookingController(mainController.addBookingView(), new BookingModel(idol), mainController);
            mainController.getView().getCardLayout().show(mainController.getView().getPnlCards(), "booking");
        }
    }







