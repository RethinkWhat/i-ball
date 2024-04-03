package client.idol.controller;

import client.idol.model.IdolApplicationModel;
import client.idol.view.IdolApplicationView;
import shared.res.Idol;
import shared.res.Resources;

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

    private Idol idol;

    /**
     * Constructs an IdolApplicationController with a specified view and model.
     * @param view The specified view.
     * @param model The specified model.
     */
    public IdolApplicationController(IdolApplicationView idolView, IdolApplicationModel model, Idol idol) {
        this.view = idolView;
        this.model = model;
        this.idol = idol;
        // constants / variables

        // action listeners


        // mouse listeners
        view.getBtnNavHome().addMouseListener(new Resources.CursorChanger(view.getBtnNavHome()));
        view.getBtnNavAccount().addMouseListener(new Resources.CursorChanger(view.getBtnNavAccount()));
        view.getBtnNavCalendar().addMouseListener(new Resources.CursorChanger(view.getBtnNavCalendar()));
        view.getBtnNavLogout().addMouseListener(new Resources.CursorChanger(view.getBtnNavLogout()));

        // focus listeners
    }
}
