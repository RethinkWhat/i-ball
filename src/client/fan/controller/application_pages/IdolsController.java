package client.fan.controller.application_pages;

import client.fan.model.application_pages.IdolsModel;
import client.fan.view.application_pages.IdolsView;
import shared.res.Idol;

import java.sql.SQLException;

public class IdolsController {

    private IdolsView view;
    private IdolsModel model;

    public IdolsController(IdolsView view, IdolsModel model) throws SQLException {
        this.view = view;
        this.model = model;


        for (Idol idol : model.getAllIdols()){
            view.addIdolPanel(idol.getProfilePictureAddress(),
                    idol.getIdolName(),
                    idol.getIdolType(),
                    Double.toString(idol.getVideoCallRate()),
                    Double.toString(idol.getVoiceCallRate()));
        }


        /*
        for (int i = 0; i < model.getAllIdols().size(); i++){

            Idol currIdol = model.getAllIdols().get(i);
            view.getPnlIdolsContainer().add(new IdolsView.IdolDetailsPanel(
                   currIdol.getProfilePictureAddress(),
                   currIdol.getIdolName(),
                   currIdol.getIdolType(),
                    Double.toString(currIdol.getVideoCallRate()),
                    Double.toString(currIdol.getVoiceCallRate())
            ));
        }
         */
    }

}
