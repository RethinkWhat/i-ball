package client.idol.model.application_pages;

import shared.res.Idol;

public class VirtualMeetupModel {

    private Idol idol;

    public VirtualMeetupModel(Idol idol) {
        this.idol = idol;
    }

    public Idol getIdol() {
        return idol;
    }

    public void setIdol(Idol idol) {
        this.idol = idol;
    }
}
