package client.fan.controller;

import client.fan.controller.application_pages.IdolsController;
import client.fan.controller.application_pages.MyIdolsController;
import client.fan.model.FanApplicationModel;
import client.fan.model.application_pages.IdolsModel;
import client.fan.model.application_pages.MyIdolsModel;
import client.fan.view.FanApplicationView;
import shared.res.Resources;

import java.sql.SQLException;

/**
 * The FanApplicationController provides the logic to navigate between different pages.
 */
public class FanApplicationController {
    /**
     * The view.
     */
    private FanApplicationView view;
    /**
     * The model.
     */
    private FanApplicationModel model;

    /**
     * Constructs a FanApplicationController with a specified view and model.
     * @param view The specified view.
     * @param model The specified model.
     */
    public FanApplicationController(FanApplicationView view, FanApplicationModel model) throws SQLException {
        this.view = view;
        this.model = model;

        new IdolsController(view.getIdolsView(), new IdolsModel(), this);
        MyIdolsController myIdolsController = new MyIdolsController(view.getMyIdolsView(), new MyIdolsModel(model.getUser()), this);
        // new VirtualMeetupController(view.getVirtualMeetupView(), new VirtualMeetupModel());

        // action listeners
        view.getBtnNavHome().addActionListener(e -> {
            view.getCardLayout().show(view.getPnlCards(), "home");
        });
        view.getBtnNavMyIdols().addActionListener(e -> {
            // Show all user's booking sessions when "My Idols" button is clicked
            try {
                myIdolsController.showAllFanSessions();
            } catch (SQLException ex) {
                ex.printStackTrace(); // Handle SQLException appropriately
            }
            view.getCardLayout().show(view.getPnlCards(), "idols");
        });
        view.getBtnNavLogout().addActionListener(e -> {
            view.dispose();
            System.exit(0);
        });

        // mouse listeners
        view.getBtnNavHome().addMouseListener(new Resources.CursorChanger(view.getBtnNavHome()));
        view.getBtnNavMyIdols().addMouseListener(new Resources.CursorChanger(view.getBtnNavMyIdols()));
        view.getBtnNavLogout().addMouseListener(new Resources.CursorChanger(view.getBtnNavLogout()));

        // focus listeners
    }

    public FanApplicationView getView() {
        return view;
    }

    public FanApplicationModel getModel() {
        return model;
    }
}
