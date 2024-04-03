package client.idol.controller.application_pages;

import client.idol.model.application_pages.FanbaseModel;
import client.idol.view.application_pages.FanbaseView;
import shared.res.Resources;

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
        view.setJoinListener(new JoinListener());
        view.setReturnListener(new ReturnListener());

        // mouse listeners
        view.getBtnJoin().addMouseListener(new Resources.CursorChanger(view.getBtnJoin()));
        view.getBtnReturn().addMouseListener(new Resources.CursorChanger(view.getBtnReturn()));

        // focus listeners
        view.getTxtSearchbar().addFocusListener(new Resources.TextFieldFocus(view.getTxtSearchbar(), "Search date (MM/DD/YY)"));
    }

    /**
     * Processes the joining of the session.
     */
    class JoinListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

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
}
