package client.fan.controller;

import client.fan.model.FanApplicationModel;
import client.fan.view.FanApplicationView;
import shared.res.User;

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

    private User user;
    /**
     * Constructs a FanApplicationController with a specified view and model.
     * @param view The specified view.
     * @param model The specified model.
     */
    public FanApplicationController(FanApplicationView view, FanApplicationModel model, User user) {
        this.view = view;
        this.model = model;
        this.user = user;

        // action listeners

        // mouse listeners

        // focus listeners
    }
}
