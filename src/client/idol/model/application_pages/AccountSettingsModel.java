package client.idol.model.application_pages;

import shared.res.DataPB;
import shared.res.Idol;

import java.text.SimpleDateFormat;

/**
 * The AccountSettingsModel provides methods for viewing and editing personal details, security details,
 * bio, available days and respective available times, and handling of account deletion.
 */
public class AccountSettingsModel {

    Idol idol;

    public AccountSettingsModel(Idol idol) {
        this.idol = idol;
    }

    public boolean deleteAccount() {
        return DataPB.deleteAccount(idol.getIdolID());
    }

    public boolean editInfo(String toEdit, String newInfo) {

        DataPB.editInfo(idol.getIdolID(), toEdit, newInfo);
        //TODO: access datapb
        return false;
    }

    public Idol getIdol() {
        return idol;
    }

    public void setIdol(Idol idol) {
        this.idol = idol;
    }

    public String[] getAvailability(String date) {

        return DataPB.getAvailability(idol.getIdolID(),date);
    }

    public void updateStartEndTime(String day, String startTime, String endTime){
        DataPB.updateStartEndTime(idol.getIdolID(), day,startTime,endTime);
    }

    public void deleteStartEndTime(String day) {
        DataPB.deleteStartEndTime(idol.getIdolID(), day);
    }
}
