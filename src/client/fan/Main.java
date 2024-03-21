package client.fan;

import client.fan.controller.LoginController;
import client.fan.model.LoginModel;
import client.fan.view.LoginView;

/**
 * Main entry point of the fan application.
 */
public class Main {
    /**
     * Main entry point of the program.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new LoginController(new LoginView(), new LoginModel());
    }
}
