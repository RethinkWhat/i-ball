package client.fan.view;

import client.fan.view.application_pages.AccountSettingsView;
import client.fan.view.application_pages.BookingView;
import client.fan.view.application_pages.VirtualMeetupView;

import javax.swing.*;

/**
 * The main application for fan users that contain the different subpages.
 */
public class FanApplicationView extends JFrame {
    /**
     * Panel of booking view.
     */
    private BookingView bookingView;
    /**
     * Panel of virtual meetup view.
     */
    private VirtualMeetupView virtualMeetupView;
    /**
     * Panel of account settings view.
     */
    private AccountSettingsView accountSettingsView;
}
