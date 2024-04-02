package client.idol;

import client.idol.controller.IdolApplicationController;
import client.idol.model.IdolApplicationModel;
import client.idol.view.IdolApplicationView;

/**
 * The main entry point of the idol application
 */
public class Main {
    /**
     * Main entry point of the program.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new IdolApplicationController(new IdolApplicationView(), new IdolApplicationModel());
    }
}
