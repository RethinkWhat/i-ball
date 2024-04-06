package shared;

import client.fan.controller.application_pages.IdolsController;
import client.fan.model.application_pages.IdolsModel;
import client.fan.view.application_pages.IdolsView;
import shared.login_register.LoginController;
import shared.login_register.LoginModel;
import shared.login_register.LoginView;

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
