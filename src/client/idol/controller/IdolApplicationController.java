package client.idol.controller;

import client.idol.model.IdolApplicationModel;
import client.idol.view.IdolApplicationView;
import shared.res.DataPB;
import shared.res.Idol;
import shared.res.Resources;
import shared.res.Session;

import java.util.ArrayList;

/**
 * The IdolApplicationController provides the logic to navigate between different pages.
 */
public class IdolApplicationController {
    /**
     * The view.
     */
    private IdolApplicationView view;
    /**
     * The model.
     */
    private IdolApplicationModel model;


    /**
     * Constructs an IdolApplicationController with a specified view and model.
     * @param idolView The specified view.
     * @param model The specified model.
     */
    public IdolApplicationController(IdolApplicationView idolView, IdolApplicationModel model) {
        this.view = idolView;
        this.model = model;

        // constants / variables
        view.getLblUser().setText(model.getIdol().getIdolName());


        // action listeners
        view.getBtnNavHome().addActionListener(e -> {
            view.getCardLayout().show(view.getPnlCards(), "home");
        });

        view.getBtnNavLogout().addActionListener(e -> {
            view.dispose();
            System.exit(0);
        });

        // mouse listeners
        view.getBtnNavHome().addMouseListener(new Resources.CursorChanger(view.getBtnNavHome()));
        view.getBtnNavLogout().addMouseListener(new Resources.CursorChanger(view.getBtnNavLogout()));

        // mouse listeners
        view.getBtnNavHome().addMouseListener(new Resources.CursorChanger(view.getBtnNavHome()));
        view.getBtnNavAccount().addMouseListener(new Resources.CursorChanger(view.getBtnNavAccount()));
        view.getBtnNavCalendar().addMouseListener(new Resources.CursorChanger(view.getBtnNavCalendar()));
        view.getBtnNavLogout().addMouseListener(new Resources.CursorChanger(view.getBtnNavLogout()));

        // focus listeners
    }
}
