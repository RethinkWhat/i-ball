package client.fan.view.application_pages;

import shared.res.Stylesheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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
    private String idolBio = "";
    /**
     * The available days of the idol.
     */
    private String availDays;
    /**
     * Text area containing availDays
     */
    private JTextArea txaAvailDays;
    /**
     * The available time of the idol.
     */
    private String availTime;
    /**
     * Text area containing availTime.
     */
    private JTextArea txaAvailTime;
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
    private JComboBox<Integer> cmbDuration;
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
            this.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5,10,5,10);
            gbc.fill = GridBagConstraints.BOTH;

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 300;
            gbc.ipady = 30;
            JLabel lblAvailability = style.createLblH2("Availability:",style.black);
            add(lblAvailability, gbc);

            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.ipady = 10;
            GridLayout gridLayout = new GridLayout(1,2);
            gridLayout.setVgap(10);
            gridLayout.setHgap(15);
            JPanel pnlAvailDetails = new JPanel(gridLayout);
            pnlAvailDetails.setBackground(style.white);
            pnlAvailDetails.setPreferredSize(new Dimension(550,100));
            add(pnlAvailDetails, gbc);

            pnlAvailDetails.add(new AvailableDaysPanel());
            pnlAvailDetails.add(new AvailableTimePanel());

            gbc.gridy = 2;
            JLabel lblRates = style.createLblH2("Rates:",style.black);
            add(lblRates, gbc);

            gbc.gridy = 3;
            JPanel pnlRateDetails = new JPanel(gridLayout);
            pnlRateDetails.setBackground(style.white);
            pnlRateDetails.setPreferredSize(new Dimension(550,50));
            add(pnlRateDetails, gbc);

            pnlRateDetails.add(new VideoRatePanel());
            pnlRateDetails.add(new VoiceRatePanel());

            gbc.gridy = 4;
            JLabel lblReservation = style.createLblH2("Reservation:",style.black);
            add(lblReservation, gbc);

            gbc.gridy = 5;
            add(new ReservePanel(), gbc);

            this.setPreferredSize(new Dimension(550,755));
        }

        /**
         * Panel containing the available days details.
         */
        class AvailableDaysPanel extends JPanel {
            /**
             * Constructs a panel of AvailableDaysPanel
             */
            public AvailableDaysPanel() {
                this.setBackground(style.white);
                this.setLayout(new BorderLayout());

                JLabel lblAvailDays = style.createLblH4("Days Available:",style.black);
                add(lblAvailDays, BorderLayout.NORTH);

                availDays = "Info";

                txaAvailDays = new JTextArea(availDays);
                txaAvailDays.setFont(new Font("Arial", Font.PLAIN, 14));
                txaAvailDays.setPreferredSize(new Dimension(500, 260));
                txaAvailDays.setWrapStyleWord(true);
                txaAvailDays.setLineWrap(true);
                txaAvailDays.setOpaque(false);
                txaAvailDays.setEditable(false);
                txaAvailDays.setFocusable(false);
                txaAvailDays.setForeground(style.black);
                add(txaAvailDays, BorderLayout.CENTER);

                this.setPreferredSize(new Dimension(550,100));
            }
        }

        /**
         * Panel containing the available time details.
         */
        class AvailableTimePanel extends JPanel {
            /**
             * Constructs a panel of AvailableTimePanel.
             */
            public AvailableTimePanel() {
                this.setBackground(style.white);
                this.setLayout(new BorderLayout());

                JLabel lblAvailTime = style.createLblH4("Time Available:",style.black);
                add(lblAvailTime, BorderLayout.NORTH);

                availTime = "Info";

                txaAvailTime = new JTextArea(availTime);
                txaAvailTime.setFont(new Font("Arial", Font.PLAIN, 14));
                txaAvailTime.setPreferredSize(new Dimension(500, 260));
                txaAvailTime.setWrapStyleWord(true);
                txaAvailTime.setLineWrap(true);
                txaAvailTime.setOpaque(false);
                txaAvailTime.setEditable(false);
                txaAvailTime.setFocusable(false);
                txaAvailTime.setForeground(style.black);
                add(txaAvailTime, BorderLayout.CENTER);
            }
        }

        /**
         * Contains the video call rate details.
         */
         class VideoRatePanel extends JPanel {
            /**
             * Constructs a panel of VideoRatePanel.
             */
            public VideoRatePanel() {
                this.setBackground(style.white);
                this.setLayout(new BorderLayout());

                JLabel lblVidRateLabel = style.createLblH4("Video Call Rate:",style.black);
                add(lblVidRateLabel, BorderLayout.NORTH);

                lblVidRate = style.createLblP("Info",style.black);
                add(lblVidRate, BorderLayout.CENTER);
            }
         }

        /**
         * Contains the voice call rate details.
         */
         class VoiceRatePanel extends JPanel {
            /**
             * Constructs a panel of VoiceRatePanel.
             */
            public VoiceRatePanel() {
                this.setBackground(style.white);
                this.setLayout(new BorderLayout());

                JLabel lblVoiceRateLabel = style.createLblH4("Voice Call Rate:",style.black);
                add(lblVoiceRateLabel, BorderLayout.NORTH);

                lblVoiceRate = style.createLblP("Info",style.black);
                add(lblVoiceRate, BorderLayout.CENTER);
            }
         }

        /**
         * Contains the I/O components to book a reservation/
         */
        class ReservePanel extends JPanel {
            /**
             * Constructs a panel of ReservePanel.
             */
            public ReservePanel() {
                this.setBackground(style.white);
                this.setLayout(new GridBagLayout());

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5,20,5,20);
                gbc.fill = GridBagConstraints.BOTH;

                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.anchor = GridBagConstraints.CENTER;
                gbc.weightx = 300;
                gbc.gridwidth = 3;
                JPanel pnlRadioButtons = new JPanel(new FlowLayout());
                pnlRadioButtons.setBackground(style.white);
                pnlRadioButtons.setPreferredSize(new Dimension(550,60));
                add(pnlRadioButtons, gbc);

                ButtonGroup buttonGroup = new ButtonGroup();

                radVidCall = style.createRad("Video Call", style.black);
                radVidCall.setSelected(true);
                buttonGroup.add(radVidCall);
                pnlRadioButtons.add(radVidCall);

                radVoiceCall = style.createRad("Voice Call", style.black);
                buttonGroup.add(radVoiceCall);
                pnlRadioButtons.add(radVoiceCall);

                gbc.gridy = 1;
                JLabel lblDate = style.createLblH4("Date",style.black);
                add(lblDate, gbc);

                gbc.gridy = 2;
                cmbDate = new JComboBox<>(new String[] {"Select Date:"});
                cmbDate.setFont(new Font("Arial", Font.PLAIN, 14));
                add(cmbDate, gbc);

                gbc.gridy = 3;
                gbc.gridx = 0;
                gbc.gridwidth = 1;
                JLabel lblTime = style.createLblH4("Time",style.black);
                add(lblTime, gbc);

                gbc.gridy = 4;
                cmbTime = new JComboBox<>(new String[] {"Select Time:"});
                cmbTime.setFont(new Font("Arial", Font.PLAIN, 14));
                add(cmbTime, gbc);

                gbc.gridy = 3;
                gbc.gridx = 1;
                gbc.gridwidth = 1;
                JLabel lblDuration = style.createLblH4("Duration (minutes)",style.black);
                add(lblDuration, gbc);

                gbc.gridy = 4;
                cmbDuration = new JComboBox<>();
                cmbDuration.setFont(new Font("Arial", Font.PLAIN, 14));
                add(cmbDuration, gbc);

                for (int i = 5; i <= 30; i += 5) {
                    cmbDuration.addItem(i);
                }

                gbc.gridx = 0;
                gbc.gridy = 5;
                gbc.gridwidth = 3;
                JLabel lblAmount = style.createLblH4("Amount",style.black);
                add(lblAmount, gbc);

                gbc.gridy = 6;
                txtAmount = style.createTxtRounded("Amount",style.lightGray,style.black, 20);
                txtAmount.setEditable(false);
                txtAmount.setFocusable(false);
                add(txtAmount, gbc);

                gbc.gridy = 7;
                JLabel lblBr = new JLabel("");
                add(lblBr, gbc);

                gbc.gridy = 8;
                btnBook = style.createBtnRounded("Book",style.purple, style.black, 20);
                add(btnBook, gbc);

                this.setPreferredSize(new Dimension(550, 200));
            }
        }
    }

    /**
     * Retrieves the current JButton of btnBack.
     * @return The current btnBack.
     */
    public JButton getBtnBack() {
        return btnBack;
    }

    /**
     * Retrieves the current JLabel of lblIdolPfp.
     * @return The current lblIdolPfp.
     */
    public JLabel getLblIdolPfp() {
        return lblIdolPfp;
    }

    /**
     * Retrieves the current JLabel of getLblIdolName.
     * @return The current lblIdolName.
     */
    public JLabel getLblIdolName() {
        return lblIdolName;
    }

    /**
     * Retrieves the current JLabel of lblIdolType.
     * @return The current lblIdolType.
     */
    public JLabel getLblIdolType() {
        return lblIdolType;
    }

    /**
     * Retrieves the current JButton of btnIdolFb.
     * @return The current btnIdolFb.
     */
    public JButton getBtnIdolFb() {
        return btnIdolFb;
    }

    /**
     * Retrieves the current JButton of btnIdolIg.
     * @return The current btnIdolIg.
     */
    public JButton getBtnIdolIg() {
        return btnIdolIg;
    }

    /**
     * Retrieves the current JButton of btnIdolX.
     * @return The current btnIdolX.
     */
    public JButton getBtnIdolX() {
        return btnIdolX;
    }

    /**
     * Retrieves the current JLabel of lblIdolQuote.
     * @return The current lblIdolQuote.
     */
    public JLabel getLblIdolQuote() {
        return lblIdolQuote;
    }

    /**
     * Retrieves the current idolBio.
     * @return The current idolBio.
     */
    public String getIdolBio() {
        return idolBio;
    }

    /**
     * Retrieves the current JTextArea of txaIdolBio.
     * @return The current txaIdolBio.
     */
    public JTextArea getTxaIdolBio() {
        return txaIdolBio;
    }

    /**
     * Retrieves the current availDays.
     * @return The current availDays.
     */
    public String getAvailDays() {
        return availDays;
    }

    /**
     * Retrieves the current availTime.
     * @return The current availTime.
     */
    public String getAvailTime() {
        return availTime;
    }

    /**
     * Retrieves the current JLabel of lblVidRate.
     * @return The current lblVidRate.
     */
    public JLabel getLblVidRate() {
        return lblVidRate;
    }

    /**
     * Retrieves the current JLabel of lblVoiceRate.
     * @return The current lblVoiceRate.
     */
    public JLabel getLblVoiceRate() {
        return lblVoiceRate;
    }

    /**
     * Retrieves the current JRadioButton of radVidCall.
     * @return The current radVidCall.
     */
    public JRadioButton getRadVidCall() {
        return radVidCall;
    }

    /**
     * Retrieves the current JRadioButton of radVoiceCall.
     * @return The current radVoiceCall.
     */
    public JRadioButton getRadVoiceCall() {
        return radVoiceCall;
    }

    /**
     * Retrieves the current JComboBox of cmbDate.
     * @return The current cmbDate.
     */
    public JComboBox<String> getCmbDate() {
        return cmbDate;
    }

    /**
     * Retrieves the current JComboBox of cmbTime.
     * @return The current cmbTime.
     */
    public JComboBox<String> getCmbTime() {
        return cmbTime;
    }

    /**
     * Retrieves the current JComboBox of cmbDuration.
     * @return The current cmbDuration.
     */
    public JComboBox<Integer> getCmbDuration() {
        return cmbDuration;
    }

    /**
     * Retrieves the current JTextField of txtAmount.
     * @return The current txtAmount.
     */
    public JTextField getTxtAmount() {
        return txtAmount;
    }

    /**
     * Retrieves the current JButton of btnBook.
     * @return The current btnBook.
     */
    public JButton getBtnBook() {
        return btnBook;
    }

    /**
     * Sets a specified action listener for btnBack.
     * @param actionListener The specified action listener.
     */
    public void setBackListener(ActionListener actionListener) {
        btnBack.addActionListener(actionListener);
    }

    /**
     * Sets a specified action listener for btnBook.
     * @param actionListener The specified action listener.
     */
    public void setBookListener(ActionListener actionListener) {
        btnBook.addActionListener(actionListener);
    }

    /**
     * Sets a specified action listener for btnIdolFb.
     * @param actionListener The specified action listener.
     */
    public void setFbListener(ActionListener actionListener) {
        btnIdolFb.addActionListener(actionListener);
    }

    /**
     * Sets a specified action listener for btnIdolIg.
     * @param actionListener The specified action listener.
     */
    public void setIgListener(ActionListener actionListener) {
        btnIdolIg.addActionListener(actionListener);
    }

    /**
     * Sets a specified action listener fot btnIdolX.
     * @param actionListener The specified action listener.
     */
    public void setXListener(ActionListener actionListener) {
        btnIdolX.addActionListener(actionListener);
    }

    /**
     * Sets a new idolBio.
     * @param idolBio The new idolBio.
     */
    public void setIdolBio(String idolBio) {
        this.idolBio = idolBio;
    }

    /**
     * Sets a new availDays.
     * @param availDays The new availDays.
     */
    public void setAvailDays(String availDays) {
        this.availDays = availDays;
    }

    /**
     * Sets a new availTime.
     * @param availTime The new availTime.
     */
    public void setAvailTime(String availTime) {
        this.availTime = availTime;
    }

    /**
     * Retrieves the current JTextArea of txaAvailDays.
     * @return The current txaAvailDays.
     */
    public JTextArea getTxaAvailDays() {
        return txaAvailDays;
    }

    /**
     * Retrieves the current JTextArea of txaAvailTime.
     * @return The current txaAvailTime.
     */
    public JTextArea getTxaAvailTime() {
        return txaAvailTime;
    }
}
