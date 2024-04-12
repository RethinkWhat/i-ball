package client.idol.controller.application_pages;

import client.idol.model.application_pages.AccountSettingsModel;
import client.idol.view.application_pages.AccountSettingsView;
import shared.res.CustomizedMessageDialog;
import shared.res.Resources;
import shared.res.Stylesheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The AccountSettingsController processes the user requests. Based on the user request, the AccounSettingsController
 * defines methods and invokes methods in the View and Model to accomplish the requested action.
 */
public class AccountSettingsController {

    AccountSettingsView view;
    AccountSettingsModel model;

    String[] startTime = new String[]{"07:00:00", "8:00:00", "9:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00","16:00:00","17:00:00", "Not Available"};
    String[] endTimeTime = new String[]{ "8:00:00", "9:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00","16:00:00","17:00:00", "18:00:00","Not Available"};

    public AccountSettingsController(AccountSettingsView view, AccountSettingsModel model) {
        this.view = view;
        this.model = model;

        //Action Listeners
        view.getPnlEdit().getPnlSide().setBtnDeleteListener(new DeleteAccount());

        view.getPnlEdit().getPnlDetails().getTxtName().setText(model.getIdol().getIdolName());
        view.getPnlEdit().getPnlDetails().getTxtUsername().setText(model.getIdol().getUsername());
        view.getPnlEdit().getPnlDetails().getTxtEmail().setText(model.getIdol().getIdolType());
        view.getPnlEdit().getPnlDetails().getTxtGCash().setText(model.getIdol().getGCashNumber());
        view.getPnlEdit().getPnlAvailability().getTxtVideoRate().setText(String.valueOf(model.getIdol().getVideoCallRate()));
        view.getPnlEdit().getPnlAvailability().getTxtVoiceRate().setText(String.valueOf(model.getIdol().getVoiceCallRate()));
        view.getPnlEdit().getPnlDetails().setBtnEditNameListener(new EditInformation(view.getPnlEdit().getPnlDetails().getTxtName(),"idolName"));
        view.getPnlEdit().getPnlDetails().setBtnEditUsernameListener(new EditInformation(view.getPnlEdit().getPnlDetails().getTxtUsername(), "username"));
        view.getPnlEdit().getPnlDetails().setBtnEditEmailListener(new EditInformation(view.getPnlEdit().getPnlDetails().getTxtEmail(), "idolType"));
        view.getPnlEdit().getPnlDetails().setBtnEditGCashListener(new EditInformation(view.getPnlEdit().getPnlDetails().getTxtGCash(),"gCashNumber"));

        view.getPnlEdit().getPnlAvailability().setCmbDateListener(new DateListener());
        view.getPnlEdit().getPnlAvailability().setCmbStartTimeListener(new StartListener());

        view.getPnlEdit().getPnlAvailability().setBtnEditDayListener(new EditListener());
        view.getPnlEdit().getPnlAvailability().setBtnConfirmListener(new ConfirmListener());

        // focus listeners
        view.getPnlEdit().getPnlAvailability().getTxtVideoRate().addFocusListener(
                new Resources.TextFieldFocus(view.getPnlEdit().getPnlAvailability().getTxtVideoRate(), String.valueOf(model.getIdol().getVideoCallRate())));
        view.getPnlEdit().getPnlAvailability().getTxtVoiceRate().addFocusListener(
                new Resources.TextFieldFocus(view.getPnlEdit().getPnlAvailability().getTxtVoiceRate(), String.valueOf(model.getIdol().getVoiceCallRate())));

        populateTime();
    }

    class StartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int x =0 ;
            for (; x < endTimeTime.length; x++ ) {
                if (endTimeTime[x].equals(view.getPnlEdit().getPnlAvailability().getStartTimeChosen())) {
                    break;
                }
            }
            if (!(view.getPnlEdit().getPnlAvailability().getStartTimeChosen().equals("07:00:00"))) {
                x += 1;
                int max = endTimeTime.length - x;

                String[] endTime = new String[max];
                for (int j = 0; j < max; j++) {
                    endTime[j] = endTimeTime[x];
                    x++;
                }
                view.getPnlEdit().getPnlAvailability().setCmbEndTime(endTime);
            }
        }
    }

    class DeleteAccount implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Stylesheet s = new Stylesheet();
            if (model.deleteAccount()) {
                Image originalImage = s.iconSuccess.getImage();
                int width = 75;
                int height = 75;
                Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                CustomizedMessageDialog msg =
                        new CustomizedMessageDialog(
                                "Success", scaledIcon,
                                "Account Delete Success",
                                "Click confirm to exit application.",
                                "Confirm",
                                s.celadon,
                                s.celadon,
                                s.gray,
                                s.celadon,
                                true);
                System.exit(0);
            } else {

                Image originalImage = s.iconFailed.getImage();
                int width = 75;
                int height = 75;
                Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                CustomizedMessageDialog msg =
                        new CustomizedMessageDialog(
                                "Failed", scaledIcon,
                                "Account Delete Failed",
                                "You still have bookings scheduled",
                                "Confirm",
                                s.red,
                                s.red,
                                s.gray,
                                s.red,
                                false);
            }
        }
    }

    class EditInformation implements ActionListener {
        JTextField textField;
        String toEdit;
        public EditInformation(JTextField textField, String toEdit) {
            this.textField = textField;
            this.toEdit = toEdit;

        }
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();

            // enter edit
            if (button.getIcon().equals(view.getStyle().iconCheck)) {
                button.setIcon(view.getStyle().iconEdit);
                model.editInfo(toEdit, textField.getText());
                textField.setEnabled(false);
            } else {
                button.setIcon(view.getStyle().iconCheck);
                textField.setEnabled(true);
            }
        }
    }

    class DateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            populateTime();
        }
    }

    public void populateTime() {
        String[] time = model.getAvailability(view.getPnlEdit().getPnlAvailability().getDateChosen());

        if (time != null) {
            view.getPnlEdit().getPnlAvailability().setCmbStartTime(new String[]{time[0]});
            view.getPnlEdit().getPnlAvailability().setCmbEndTime(new String[]{time[1]});

        } else {
            view.getPnlEdit().getPnlAvailability().setCmbStartTime(new String[]{"Time not set"});
            view.getPnlEdit().getPnlAvailability().setCmbEndTime(new String[]{"Time not set"});
        }
    }

    class EditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
                view.getPnlEdit().getPnlAvailability().setCmbStartTime(startTime);
                view.getPnlEdit().getPnlAvailability().setCmbEndTime(endTimeTime);
        }
    }

    class ConfirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (model.getIdol().getVoiceCallRate() != Double.parseDouble(view.getPnlEdit().getPnlAvailability().getTxtVoiceRate().getText())) {
                model.editInfo("voiceCallRate", view.getPnlEdit().getPnlAvailability().getTxtVoiceRate().getText());

            }
            if (model.getIdol().getVideoCallRate() != Double.parseDouble(view.getPnlEdit().getPnlAvailability().getTxtVideoRate().getText())) {
                model.editInfo("videoCallRate", view.getPnlEdit().getPnlAvailability().getTxtVideoRate().getText());
            }

            if (view.getPnlEdit().getPnlAvailability().getStartTimeChosen().equals("Not Available") ||view.getPnlEdit().getPnlAvailability().getEndTimeChosen().equals("Not Available")) {
                model.deleteStartEndTime(view.getPnlEdit().getPnlAvailability().getDateChosen());
            } else {
                model.updateStartEndTime(
                        view.getPnlEdit().getPnlAvailability().getDateChosen(),
                        view.getPnlEdit().getPnlAvailability().getStartTimeChosen(),
                        view.getPnlEdit().getPnlAvailability().getEndTimeChosen());
            }

            populateTime();

            JOptionPane.showMessageDialog(view, "Availability Set.");
        }
    }
}



