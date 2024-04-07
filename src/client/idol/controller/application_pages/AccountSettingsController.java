package client.idol.controller.application_pages;

import client.idol.model.application_pages.AccountSettingsModel;
import client.idol.view.application_pages.AccountSettingsView;
import shared.res.CustomizedMessageDialog;
import shared.res.Stylesheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The AccountSettingsController processes the user requests. Based on the user request, the AccounSettingsController
 * defines methods and invokes methods in the View and Model to accomplish the requested action.
 */
public class AccountSettingsController {

    AccountSettingsView view;
    AccountSettingsModel model;

    String[] startTime = new String[]{"07:00:00", "8:00:00", "9:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00","16:00:00","17:00:00"};
    String[] endTimeTime = new String[]{"8:00:00", "9:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00","16:00:00","17:00:00", "18:00:00"};

    public AccountSettingsController(AccountSettingsView view, AccountSettingsModel model) {
        this.view = view;
        this.model = model;

        //Action Listeners
        view.getPnlEdit().getPnlSide().setBtnDeleteListener(new DeleteAccount());

        view.getPnlEdit().getPnlDetails().getTxtName().setText(model.getIdol().getIdolName());
        view.getPnlEdit().getPnlDetails().getTxtUsername().setText(model.getIdol().getUsername());
        view.getPnlEdit().getPnlDetails().getTxtEmail().setText(model.getIdol().getIdolType());
        view.getPnlEdit().getPnlDetails().getTxtGCash().setText(model.getIdol().getGCashNumber());
        view.getPnlEdit().getPnlDetails().setBtnEditNameListener(new EditInformation(view.getPnlEdit().getPnlDetails().getTxtName(),"idolName"));
        view.getPnlEdit().getPnlDetails().setBtnEditUsernameListener(new EditInformation(view.getPnlEdit().getPnlDetails().getTxtUsername(), "username"));
        view.getPnlEdit().getPnlDetails().setBtnEditEmailListener(new EditInformation(view.getPnlEdit().getPnlDetails().getTxtEmail(), "idolType"));
        view.getPnlEdit().getPnlDetails().setBtnEditGCashListener(new EditInformation(view.getPnlEdit().getPnlDetails().getTxtGCash(),"gCashNumber"));

        view.getPnlEdit().getPnlAvailability().setCmbDateListener(new DateListener());

        view.getPnlEdit().getPnlAvailability().setBtnEditDayListener(new EditListener());
        view.getPnlEdit().getPnlAvailability().setBtnConfirmListener(new ConfirmListener());

        populateTime();
    }

    class DeleteAccount implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Stylesheet s = new Stylesheet();
            if (model.deleteAccount()) {
                System.exit(0);
            } else {
                System.out.println("show error");
                CustomizedMessageDialog msg =
                        new CustomizedMessageDialog(
                                "Failed", s.iconFailed,
                                "Account Delete Failed",
                                "You still have bookings scheduled",
                                "okay",
                                s.red,
                                s.red,
                                s.gray,
                                s.red);
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
            model.updateStartEndTime(
                    view.getPnlEdit().getPnlAvailability().getDateChosen(),
                    view.getPnlEdit().getPnlAvailability().getStartTimeChosen(),
                    view.getPnlEdit().getPnlAvailability().getEndTimeChosen());
        }
    }
}



