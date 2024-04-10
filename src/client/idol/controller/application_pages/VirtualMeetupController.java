package client.idol.controller.application_pages;

import client.idol.model.application_pages.VirtualMeetupModel;
import client.idol.view.application_pages.VirtualMeetupView;
import shared.res.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        view.getBtnEndCall().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setVisible(false);
            }
        });

        // mouse listeners
        view.getBtnEndCall().addMouseListener(new Resources.CursorChanger(view.getBtnEndCall()));

        ImageIcon pfp = new ImageIcon(model.getIdol().getProfilePictureAddress());
        Image pfpImage = pfp.getImage();
        Image resized = pfpImage.getScaledInstance(113,64,Image.SCALE_SMOOTH);
        view.getLblFanPfp().setIcon(new ImageIcon(resized));
        System.out.println("reached");


        // focus listeners
    }
    public void endCall(){
        view.setVisible(false);
        }



}


