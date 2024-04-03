package client.idol.controller.application_pages;

import client.idol.model.application_pages.VirtualMeetupModel;
import client.idol.view.application_pages.VirtualMeetupView;
import shared.Resources;

public class VirtualMeetupController {
    /**
     * The view.
     */
    private VirtualMeetupView view;
    /**
     * The model.
     */
    private VirtualMeetupModel model;
    public VirtualMeetupController(VirtualMeetupView view, VirtualMeetupModel model) {
        this.view = view;
        this.model = model;

        // action listeners


        // mouse listeners
        view.getBtnEndCall().addMouseListener(new Resources.CursorChanger(view.getBtnEndCall()));

        // focus listeners
    }
}
