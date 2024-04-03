package client.fan.controller;

import client.fan.model.FanApplicationModel;
import client.fan.view.FanApplicationView;
import shared.Resources;

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
    public FanApplicationController(FanApplicationView view, FanApplicationModel model) {
        this.view = view;
        this.model = model;

        // action listeners
        view.getBtnNavHome().addActionListener(e -> {
            view.getCardLayout().show(view.getPnlCards(), "home");
        });
        view.getBtnNavMyIdols().addActionListener(e -> {
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
}
