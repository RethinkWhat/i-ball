package client.fan.controller;

import client.fan.model.FanApplicationModel;
import client.fan.model.LoginModel;
import client.fan.model.RegisterModel;
import client.fan.view.FanApplicationView;
import client.fan.view.LoginView;
import client.fan.view.RegisterView;
import shared.Resources;

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
        view.getChkShowPassword().addActionListener(new Resources.ShowPasswordListener(view.getChkShowPassword(), view.getTxtPassword()));

        // mouse listeners
        view.getBtnLogin().addMouseListener(new Resources.CursorChanger(view.getBtnLogin()));
        view.getBtnSignup().addMouseListener(new Resources.CursorChanger(view.getBtnSignup()));

        // focus listeners
        view.getTxtUsername().addFocusListener(new Resources.TextFieldFocus(view.getTxtUsername(), "Username"));
        view.getTxtPassword().addFocusListener(
                new Resources.PasswordFocusWithCheckbox(view.getTxtPassword(), view.getChkShowPassword(), "Password"));

        view.repaint();
        view.revalidate();
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
            new FanApplicationController(new FanApplicationView(), new FanApplicationModel());
            view.dispose();
        }
    }
}
