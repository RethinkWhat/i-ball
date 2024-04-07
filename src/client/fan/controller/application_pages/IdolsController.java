package client.fan.controller.application_pages;

import client.fan.controller.FanApplicationController;
import client.fan.model.application_pages.BookingModel;
import client.fan.model.application_pages.IdolsModel;
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

        // Populate all idols initially
        populateIdolsPanel(model.getAllIdols());

        // Add action listeners for filter buttons
        view.getBtnFilterAll().addActionListener(new FilterButtonListener("ALL"));
        view.getBtnFilterGamers().addActionListener(new FilterButtonListener("Gamer"));
        view.getBtnFilterInfls().addActionListener(new FilterButtonListener("Influencer"));
        view.getBtnFilterCelebs().addActionListener(new FilterButtonListener("Celebrity"));

        // Add action listener for search button
        view.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = view.getTxtSearchbar().getText();

                // Call the method to perform idol search
                try {
                    List<Idol> searchResults = model.idolSearch(searchTerm);
                    populateIdolsPanel(searchResults);
                } catch (SQLException ex) {
                    ex.printStackTrace(); // Handle SQLException appropriately
                }
            }
        });
    }

    /**
     * Populate idols panel with the given list of idols.
     *
     * @param idols The list of idols to populate the panel with
     */
    private void populateIdolsPanel(List<Idol> idols) {
        // Clear existing idol panels
        view.getPnlIdolsContainer().removeAll();
        view.getIdolPanels().clear();

        // Add panels for idols
        for (Idol idol : idols) {
            view.addIdolPanel(idol.getProfilePictureAddress(), idol.getIdolName(), idol.getIdolType(), Double.toString(idol.getVideoCallRate()), Double.toString(idol.getVoiceCallRate()));

            // Attach ChosenIdolListener to the newly added panel
            view.getIdolPanels().get(view.getIdolPanels().size() - 1).setPfpListener(new ChosenIdolListener(mainController, idol));
        }

        // Refresh the view
        view.revalidate();
        view.repaint();
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

    /**
     * FilterButtonListener for handling filter button actions.
     */
    class FilterButtonListener implements ActionListener {
        /**
         * The category to filter.
         */
        private String category;

        /**
         * Constructs a filter button listener with specified category.
         *
         * @param category The category to filter.
         */
        public FilterButtonListener(String category) {
            this.category = category;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (category.equals("ALL")) {
                    // Show all idols
                    populateIdolsPanel(model.getAllIdols());
                } else {
                    // Filter idols by category
                    List<Idol> filteredIdols = model.filterIdolsByType(category);
                    populateIdolsPanel(filteredIdols);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Handle SQLException appropriately
            }
        }
    }
}









