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
    private JButton btnIdolFb;
    /**
     * The link to the idol's Instagram.
     */
    private JButton btnIdolIg;
    /**
     * The link to the idol's X.
     */
    private JButton btnIdolX;
    /**
     * The quote of the idol.
     */
    private JLabel lblIdolQuote;
    /**
     * The bio of the idol.
     */
    private JTextArea txaIdolBio;
    /**
     * The idol bio.
     */
    private String idolBio;
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

        add(new LeftPanel());
        add(new RightPanel());

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
            this.setBackground(style.lightGray);
            this.setBorder(style.padding);
            this.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5,20,5,20);
            gbc.fill = GridBagConstraints.BOTH;

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 300;
            gbc.ipady = 30;
            btnBack = style.createBtnIconOnly(style.iconBack, 40,40);
            btnBack.setHorizontalAlignment(SwingConstants.LEFT);
            add(btnBack, gbc);

            gbc.gridy = 1;
            gbc.weightx = 1;
            gbc.ipady = 10;
            gbc.anchor = GridBagConstraints.CENTER;
            lblIdolPfp = style.createLblIconOnly(style.iconPfpPlaceholder, 150, 150);
            lblIdolPfp.setHorizontalAlignment(SwingConstants.CENTER);
            add(lblIdolPfp, gbc);

            gbc.ipady = 5;
            gbc.gridy = 2;
            lblIdolName = style.createLblH1("Ramon Jasmin", style.black);
            lblIdolName.setHorizontalAlignment(SwingConstants.CENTER);
            add(lblIdolName, gbc);

            gbc.ipady = 30;
            gbc.gridy = 3;
            lblIdolType = style.createLblH4("Influencer", style.black);
            lblIdolType.setHorizontalAlignment(SwingConstants.CENTER);
            add(lblIdolType, gbc);

            gbc.ipady = 20;
            gbc.gridy = 4;
            JPanel pnlSocials = new JPanel(new FlowLayout(FlowLayout.CENTER));
            pnlSocials.setBackground(style.lightGray);
            pnlSocials.setPreferredSize(new Dimension(555,30));
            add(pnlSocials, gbc);

            btnIdolFb = style.createBtnIconOnly(style.iconFb, 25, 25);
            pnlSocials.add(btnIdolFb);

            btnIdolIg = style.createBtnIconOnly(style.iconIg, 25,25);
            pnlSocials.add(btnIdolIg);

            btnIdolX = style.createBtnIconOnly(style.iconX, 25, 25);
            pnlSocials.add(btnIdolX);

            gbc.ipady = 40;
            gbc.gridy = 5;
            lblIdolQuote = style.createLblH3(" \"" + "Kilabot ng Vizcaya" + "\"", style.black);
            lblIdolQuote.setHorizontalAlignment(SwingConstants.CENTER);
            add(lblIdolQuote, gbc);

            gbc.gridy = 6;
            JPanel pnlBio = new JPanel();
            pnlBio.setBorder(style.padding);
            pnlBio.setLayout(new BorderLayout());
            pnlBio.setPreferredSize(new Dimension(550, 70));
            add(pnlBio, gbc);

            idolBio = "I am a certified lover boy, data scientist, machine learning enthusiast, professional yapper, Fazzio driver, Vespa hater," +
                    "nyondomizer smasher, destroyer of tice mits, and future " +
                    "SLU Department Head of Computer Science and Computer" +
                    "Applications Department. Amen.";

            txaIdolBio = new JTextArea();
            txaIdolBio.setFont(new Font("Arial", Font.PLAIN, 14));
            txaIdolBio.setText(idolBio);
            txaIdolBio.setPreferredSize(new Dimension(500, 260));
            txaIdolBio.setWrapStyleWord(true);
            txaIdolBio.setLineWrap(true);
            txaIdolBio.setOpaque(false);
            txaIdolBio.setEditable(false);
            txaIdolBio.setFocusable(false);
            txaIdolBio.setForeground(style.black);
            pnlBio.add(txaIdolBio, BorderLayout.CENTER);

            this.setPreferredSize(new Dimension(550,755));
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
            this.setBackground(style.white);
            this.setBorder(style.padding);



            this.setPreferredSize(new Dimension(550,755));
        }
    }
}
