package client.fan.view.application_pages;

import shared.res.Stylesheet;

import javax.swing.*;
import java.awt.*;

/**
 * The BookingView is the interface to book an idol. It contains the:
 * 1. Idol information
 * 2. Buttons to book idols
 */
public class BookingView extends JPanel {
    /**
     * The back button.
     */
    private JButton btnBack;
    /**
     * The profile picture of the idol.
     */
    private JLabel lblIdolPfp;
    /**
     * The name of the idol.
     */
    private JLabel lblIdolName;
    /**
     * The idol type.
     */
    private JLabel lblIdolType;
    /**
     * The link to the idol's Facebook.
     */
    private JButton btnFb;
    /**
     * The link to the idol's Instagram.
     */
    private JButton btnIg;
    /**
     * The link to the idol's X.
     */
    private JButton btnX;
    /**
     * The quote of the idol.
     */
    private JLabel lblQuote;
    /**
     * The bio of the idol.
     */
    private JLabel lblBio;
    /**
     * The video call rate.
     */
    private JLabel lblVidRate;
    /**
     * The voice call rate.
     */
    private JLabel lblVoiceRate;
    /**
     * The video call radio button.
     */
    private JRadioButton radVidCall;
    /**
     * The voice call radio button.
     */
    private JRadioButton radVoiceCall;
    /**
     * The date picker.
     */
    private JComboBox<String> cmbDate;
    /**
     * The time picker.
     */
    private JComboBox<String> cmbTime;
    /**
     * The duration picker.
     */
    private JComboBox<String> cmbDuration;
    /**
     * The amount text field (cannot be edited).
     */
    private JTextField txtAmount;
    /**
     * The book button.
     */
    private JButton btnBook;
    /**
     * The stylesheet.
     */
    private Stylesheet style = new Stylesheet();

    /**
     * Constructs a panel of BookingView.
     */
    public BookingView() {
        this.setBackground(style.white);
        GridLayout gridLayout = new GridLayout(1,2);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        this.setLayout(gridLayout);

        LeftPanel pnlLeft = new LeftPanel();
        add(pnlLeft);

        RightPanel pnlRight = new RightPanel();
        pnlRight.setBorder(style.padding);
        pnlRight.setBackground(style.white);
        add(pnlRight);

        this.setPreferredSize(new Dimension(1100,755));
    }

    /**
     * Holds the idol details.
     */
    class LeftPanel extends JPanel {
        /**
         * Constructs a panel of LeftPanel.
         */
        public LeftPanel() {
            btnBack = style.createBtnIconOnly();
        }
    }

    /**
     * Holds the availability and booking components.
     */
    class RightPanel extends JPanel {
        /**
         * Constructs a panel of RightPanel.
         */
        public RightPanel() {

        }
    }
}
