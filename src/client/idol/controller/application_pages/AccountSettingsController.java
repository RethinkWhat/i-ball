package client.idol.controller.application_pages;

import client.idol.model.application_pages.AccountSettingsModel;
import client.idol.view.application_pages.AccountSettingsView;

/**
 * The AccountSettingsController processes the user requests. Based on the user request, the AccounSettingsController
 * defines methods and invokes methods in the View and Model to accomplish the requested action.
 */
public class AccountSettingsController {

    AccountSettingsView view;
    AccountSettingsModel model;
    public AccountSettingsController(AccountSettingsView view, AccountSettingsModel model) {
        this.view = view;
        this.model = model;

    }
}
