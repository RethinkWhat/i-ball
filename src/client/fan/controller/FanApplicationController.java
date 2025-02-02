package client.fan.controller;

import client.fan.controller.application_pages.IdolsController;
import client.fan.controller.application_pages.MyIdolsController;
import client.fan.model.FanApplicationModel;
import client.fan.model.application_pages.IdolsModel;
import client.fan.model.application_pages.MyIdolsModel;
import client.fan.view.FanApplicationView;
import client.fan.view.application_pages.BookingView;
import client.fan.view.application_pages.VirtualMeetupView;
import client.idol.controller.application_pages.FanbaseController;
import client.idol.controller.application_pages.VirtualMeetupController;
import client.idol.model.application_pages.FanbaseModel;
import client.idol.model.application_pages.VirtualMeetupModel;
import shared.res.LogoutDialog;
import shared.res.Resources;
import shared.res.Stylesheet;

import javax.swing.*;
import java.awt.*;
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

        // Update user label and profile picture
        view.getLblUser().setText(model.getUser().getName());
        ImageIcon pfp = new ImageIcon(model.getUser().getPpAddress());
        Image pfpImage = pfp.getImage();
        Image resized = pfpImage.getScaledInstance(113, 64, Image.SCALE_SMOOTH);
        view.getLblUserPfp().setIcon(new ImageIcon(resized));

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
            Stylesheet style = new Stylesheet();
            LogoutDialog dialog = new LogoutDialog("Logout", new ImageIcon("res/drawables/logout-red-solid.png"),
                    "LOGOUT CONFIRMATION", "Are you sure you want to logout?", "LOGOUT", style.red, style.white, style.black, style.red);
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

    public BookingView addBookingView() {
        BookingView bookingView = new BookingView();
        view.getPnlCards().add(bookingView, "booking");
        return bookingView;
    }
}
