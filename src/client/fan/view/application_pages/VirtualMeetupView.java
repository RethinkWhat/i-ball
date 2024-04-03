package client.fan.view.application_pages;

import shared.res.Stylesheet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * The VirtualMeetupView is the intermediary between the fan and idol. It contains:
 * 1. The countdown timer of their session.
 * 2. Pertinent details of the idol.
 * 3. Pertinent details of the session.
 */
public class VirtualMeetupView extends JPanel {
    /**
     * The name of the idol.
     */
    private JLabel lblIdolName;
    /**
     * The profile picture of the idol.
     */
    private JLabel lblIdolPfp;
    /**
     * The profile picture of the fan.
     */
    private JLabel lblFanPfp;
    /**
     * The timer of the call.
     */
    private JLabel lblTimer;
    /**
     * The button to end call.
     */
    private JButton btnEndCall;
    /**
     * The stylesheet.
     */
    private Stylesheet style = new Stylesheet();

    /**
     * Constructs a panel of VirtualMeetupView
     */
    public VirtualMeetupView() {
        this.setLayout(new BorderLayout());
        this.setBackground(style.lightGray);
        this.setBorder(style.padding);

        add(new CallPanel(), BorderLayout.CENTER);
        add(new ButtonsPanel(), BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(1100, 755));
    }

    /**
     * Panel to hold the call participants' details.
     */
    class CallPanel extends JPanel {
        public CallPanel() {
            this.setLayout(new BorderLayout());

            JPanel container = style.createPnlRounded(1000, 700, style.darkGray, style.white);
            container.setLayout(new GridBagLayout());
            container.setBorder(style.padding);
            add(container, BorderLayout.CENTER);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridwidth = 3;

            gbc.ipadx = 800;
            gbc.ipady = 150;
            gbc.gridx = 0;
            gbc.gridy = 0;
            lblTimer = style.createLblP("00:00",style.white);
            lblTimer.setIcon(style.iconTimer);
            container.add(lblTimer, gbc);

            gbc.gridx = 1;
            JLabel lblSound = style.createLblIconOnly(style.iconSound, 40,40);
            lblSound.setHorizontalAlignment(SwingConstants.RIGHT);
            container.add(lblSound, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            lblIdolPfp = style.createLblIconOnly(style.iconPfpPlaceholder, 120,120);
            lblIdolPfp.setHorizontalAlignment(SwingConstants.CENTER);
            container.add(lblIdolPfp, gbc);

            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.WEST;
            lblIdolName = style.createLblH3("Info", style.white);
            lblIdolName.setHorizontalAlignment(SwingConstants.LEFT);
            container.add(lblIdolName, gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.EAST;
            lblFanPfp = style.createLblIconOnly(style.iconPfpPlaceholder, 60,60);
            lblFanPfp.setHorizontalAlignment(SwingConstants.RIGHT);
            container.add(lblFanPfp, gbc);
        }
    }

    /**
     * Panel to hold the buttons.
     */
    class ButtonsPanel extends JPanel {
        public ButtonsPanel() {
            this.setLayout(new BorderLayout());
            this.setBorder(new EmptyBorder(10,0,10,0));

            JPanel container = style.createPnlRounded(1000,100,style.white, style.white);
            container.setBorder(style.padding);
            container.setLayout(new FlowLayout());
            add(container,BorderLayout.SOUTH);

            btnEndCall = style.createBtnIconOnly(style.iconEndCall, 50,50);
            btnEndCall.setVerticalAlignment(SwingConstants.CENTER);
            btnEndCall.setHorizontalAlignment(SwingConstants.CENTER);
            container.add(btnEndCall);
        }
    }

    /**
     * Retrieves the current JLabel of lblIdolName.
     * @return The current lblIdolName.
     */
    public JLabel getLblIdolName() {
        return lblIdolName;
    }

    /**
     * Retrieves the current JLabel of lblIdolPfp.
     * @return The current lblIdolPfp.
     */
    public JLabel getLblIdolPfp() {
        return lblIdolPfp;
    }

    /**
     * Retrieves the current JLabel of lblFanPfp.
     * @return The current lblFanPfp.
     */
    public JLabel getLblFanPfp() {
        return lblFanPfp;
    }

    /**
     * Retrieves the current JLabel of lblTimer.
     * @return The current lblTimer.
     */
    public JLabel getLblTimer() {
        return lblTimer;
    }

    /**
     * Retrieves the current JButton of btnEndCall.
     * @return The current btnEndCall.
     */
    public JButton getBtnEndCall() {
        return btnEndCall;
    }
}
