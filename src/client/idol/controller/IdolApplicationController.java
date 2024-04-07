package client.idol.controller;

import client.idol.controller.application_pages.AccountSettingsController;
import client.idol.controller.application_pages.CalendarController;
import client.idol.controller.application_pages.FanbaseController;
import client.idol.controller.application_pages.VirtualMeetupController;
import client.idol.model.IdolApplicationModel;
import client.idol.model.application_pages.AccountSettingsModel;
import client.idol.model.application_pages.CalendarModel;
import client.idol.model.application_pages.FanbaseModel;
import client.idol.model.application_pages.VirtualMeetupModel;
import client.idol.view.IdolApplicationView;
import shared.res.LogoutDialog;
import shared.res.Stylesheet;

import javax.swing.*;
import java.awt.*;

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

        ImageIcon pfp = new ImageIcon(model.getIdol().getProfilePictureAddress());
        Image pfpImage = pfp.getImage();
        Image resized = pfpImage.getScaledInstance(113,64,Image.SCALE_SMOOTH);
        view.getLblUserPfp().setIcon(new ImageIcon(resized));

        new FanbaseController(view.getFanbaseView(), new FanbaseModel(model.getIdol()));
        new AccountSettingsController(view.getAccountSettingsView(), new AccountSettingsModel(model.getIdol()));
        new CalendarController(view.getCalendarView(), new CalendarModel(model.getIdol()));
        new VirtualMeetupController(view.getVirtualMeetupView(), new VirtualMeetupModel(model.getIdol()));



        // action listeners
        view.getBtnNavHome().addActionListener(e -> {
            view.getCardLayout().show(view.getPnlCards(), "home");
        });

        view.getBtnNavAccount().addActionListener(e -> {
            view.getCardLayout().show(view.getPnlCards(),"account");
        });
        view.getBtnNavCalendar().addActionListener(e -> {
            view.getCardLayout().show(view.getPnlCards(), "calendar");
        });

        view.getBtnNavLogout().addActionListener(e -> {
            Stylesheet style = new Stylesheet();
            LogoutDialog dialog = new LogoutDialog("Logout", new ImageIcon("res/drawables/logout-red-solid.png"),
                    "LOGOUT CONFIRMATION", "Are you sure you want to logout?", "LOGOUT", style.red, style.white, style.black, style.red);
        });

    }
}
