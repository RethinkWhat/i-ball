package client.idol.model.application_pages;

import shared.res.Idol;

/**
 * The AccountSettingsModel provides methods for viewing and editing personal details, security details,
 * bio, available days and respective available times, and handling of account deletion.
 */
public class AccountSettingsModel {

    Idol idol;

    public AccountSettingsModel(Idol idol) {
        this.idol = idol;
    }
}
