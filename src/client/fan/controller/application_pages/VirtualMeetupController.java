package client.fan.controller.application_pages;

import client.fan.model.application_pages.VirtualMeetupModel;
import client.fan.view.application_pages.VirtualMeetupView;
import shared.res.Resources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The VirtualMeetupController processes the user requests. Based on the user request, the VirtualMeetupController
 * defines methods and invokes methods in the View and Model to accomplish the requested action.
 */
public class VirtualMeetupController {
    /**
     * The view.
     */
    private VirtualMeetupView view;
    /**
     * The model.
     */
    private VirtualMeetupModel model;

    /**
     * Construct a VirtualMeetupController
     */
    public VirtualMeetupController(VirtualMeetupView view, VirtualMeetupModel model) {
        this.view = view;
        this.model = model;

        // action listeners
        view.getBtnEndCall().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setVisible(false);
            }
        });

        // mouse listeners
        view.getBtnEndCall().addMouseListener(new Resources.CursorChanger(view.getBtnEndCall()));

        // focus listeners
    }

    // inner classes
}
