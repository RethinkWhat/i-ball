package client.fan.controller;

import client.fan.model.LoginModel;
import client.fan.model.RegisterModel;
import client.fan.view.LoginView;
import client.fan.view.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The LoginController processes the user requests. Based on the user request, the LoginController
 * defines methods and invokes methods in the View and Model to accomplish the requested action.
 */
public class LoginController {
    /**
     * The view.
     */
    private LoginView view;
    /**
     * The model.
     */
    private LoginModel model;

    /**
     * Constructs a LoginController
     */
    public LoginController(LoginView view, LoginModel model) {
        this.view = view;
        this.model = model;

        // action listeners
        view.setLoginListener(new LoginListener());
        view.setSignupListener(e -> {
            view.dispose();
            new RegisterController(new RegisterView(), new RegisterModel());
        });

        // mouse listeners

        // focus listeners
    }

    /**
     * Processes login.
     */
    class LoginListener implements ActionListener {
        /**
         * Validates user input and opens the main fan application.
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            // todo: implementation
            new FanApplicationController();
            view.dispose();
        }
    }
}
