package client.fan.controller.application_pages;

import client.fan.model.application_pages.IdolsModel;
import client.fan.view.application_pages.IdolsView;
import shared.res.Idol;

import java.sql.SQLException;

public class IdolsController {

    IdolsView view;
    IdolsModel model;

    public IdolsController(IdolsView view, IdolsModel model) throws SQLException {
        this.view = view;
        this.model = model;

        for (Idol idol : model.getAllIdols()){
            view.getPnlIdolsContainer().add(new IdolsView.IdolDetailsPanel(idol.getProfilePictureAddress(),
                    idol.getIdolName(),
                    idol.getIdolType(),
                    Double.toString(idol.getVideoCallRate()),
                    Double.toString(idol.getVoiceCallRate())
                    ));
        }
    }

}
