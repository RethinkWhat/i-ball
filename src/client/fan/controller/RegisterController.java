package client.fan.controller;

import client.fan.Main;
import client.fan.model.LoginModel;
import client.fan.model.RegisterModel;
import client.fan.view.LoginView;
import client.fan.view.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The RegisterController processes the user requests. Based on the user request, the RegisterController
 * defines methods and invokes methods in the View and Model to accomplish the requested action.
 */
public class RegisterController {
    /**
     * The view.
     */
    private RegisterView view;
    /**
     * The model.
     */
    private RegisterModel model;

    /**
     * Constructs a RegisterController with a specified view and model.
     * @param view The specified view.
     * @param model The specified model.
     */
    public RegisterController(RegisterView view, RegisterModel model) {
        this.view = view;
        this.model = model;

        // action listeners
        view.setSignupListener(new SignupListener());
        view.setLoginListener(e -> {
            view.dispose();
            new LoginController(new LoginView(), new LoginModel());
        });

        // mouse listeners

        // focus listeners
    }

    /**
     * Processes registration.
     */
    class SignupListener implements ActionListener {
        /**
         * Validates user input and creates a new account.
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
            // todo: confirmation message and implementation
            new Main();
        }
    }
}
