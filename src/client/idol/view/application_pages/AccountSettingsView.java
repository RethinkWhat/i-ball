package client.idol.view.application_pages;

import shared.res.Stylesheet;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The AccountSettingsView contains the idol's account information. It contains:
 * 1. Personal details.
 * 2. Security details.
 * 3. Bio
 * 4. Profile picture (pending).
 * 5. Available days and respective available times.
 * 6. Delete account.
 */
public class AccountSettingsView extends JPanel {

    Stylesheet style = new Stylesheet();

    private JPanel pnlHeader;
    private JPanel pnlTitle;
    private EditPanel pnlEdit;


    public Stylesheet getStyle() {
        return style;
    }


    public AccountSettingsView() {
        this.setBackground(style.lightGray);
        this.setLayout(new BorderLayout());

        pnlHeader = new HeaderPanel();
        pnlTitle = new TitlePanel();
        pnlEdit = new EditPanel();

        add(pnlHeader, BorderLayout.NORTH);
        add(pnlTitle, BorderLayout.CENTER);
        add(pnlEdit, BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(1100, 755));
        this.setVisible(true);
    }

    public EditPanel getPnlEdit() {
        return pnlEdit;
    }

    public class HeaderPanel extends JPanel {

        public HeaderPanel() {
            this.setBackground(style.purple);
            this.setLayout(new FlowLayout());
            this.setPreferredSize(new Dimension(1000,45));

        }
    }

    public class TitlePanel extends JPanel {
        private JLabel lblAccountSettings;

        public TitlePanel() {
            this.setLayout(new BorderLayout());
            this.setBorder(style.padding);

            lblAccountSettings = new JLabel("Account Settings");
            //lblAccountSettings.setFont(style.createLblH3(null,null).getFont());
            lblAccountSettings.setFont(style.createLblCalendar(null, null).getFont());
            lblAccountSettings.setForeground(style.black);
            lblAccountSettings.setHorizontalAlignment(JLabel.LEFT);
            lblAccountSettings.setVerticalAlignment(JLabel.CENTER);
            this.add(lblAccountSettings, BorderLayout.CENTER);
            this.setPreferredSize(new Dimension(1000,105));
        }
    }

    public class EditPanel extends JPanel {

        private JPanel container;

        private SidePanel pnlSide;

        private DetailsPanel pnlDetails;

        private AvailabilityPanel pnlAvailability;

        public EditPanel() {
            this.setLayout(new BorderLayout());
            this.setBorder(new EmptyBorder(10,20,20,20));

            container = style.createPnlRounded(700,575,style.white,style.gray);
            container.setLayout(new BorderLayout());
            container.setBorder(style.padding);
            add(container, BorderLayout.CENTER);

            pnlSide = new SidePanel();
            pnlDetails = new DetailsPanel();
            pnlAvailability = new AvailabilityPanel();

            container.add(pnlSide, BorderLayout.WEST);
            container.add(pnlDetails, BorderLayout.CENTER);
            container.add(pnlAvailability, BorderLayout.EAST);
        }

        public SidePanel getPnlSide() {
            return pnlSide;
        }

        public DetailsPanel getPnlDetails() {
            return pnlDetails;
        }

        public AvailabilityPanel getPnlAvailability() {
            return pnlAvailability;
        }
    }

    public class SidePanel extends JPanel {

        JPanel pnlPadded;
        JPanel container;

        JButton btnDelete;

        JButton btnProfile;

        public SidePanel() {
            this.setLayout(new BorderLayout());
            this.setBorder(BorderFactory.createMatteBorder(0,0,0,1, style.gray));

            pnlPadded = new JPanel();
            pnlPadded.setBorder(new EmptyBorder(30,20,50,0));
            pnlPadded.setSize(new Dimension(700,575));
            pnlPadded.setBackground(style.white);


            container = new JPanel();
            container.setPreferredSize(new Dimension(170,100));
            container.setBackground(style.white);
            container.setLayout(new GridLayout(2,1));
            btnProfile = style.createBtnTxtOnly("My Profile", style.purple);
            btnDelete = style.createBtnTxtOnly("Delete Account", style.red);

            container.add(btnProfile);
            container.add(btnDelete);

            pnlPadded.add(container, BorderLayout.NORTH);

            add(pnlPadded);
        }

        public JButton getBtnDelete() {
            return btnDelete;
        }

        public void setBtnDeleteListener(ActionListener listener) {
            btnDelete.addActionListener(listener);
        }
    }

    public class DetailsPanel extends JPanel{


        JPanel container;

        JPanel pnlPadded;
        JLabel lblUserPfp;

        JLabel lblAccountType;


        JPanel pnlDetails;

        JTextField txtName;
        JTextField txtUsername;
        JTextField txtEmail;
        JTextField txtGCash;

        JButton btnEditPFP;
        JButton btnEditName;
        JButton btnEditUsername;
        JButton btnEditEmail;
        JButton btnEditGCash;

        public DetailsPanel() {
            this.setLayout(new BorderLayout());
            this.setBackground(style.white);
            this.setPreferredSize(new Dimension(375,575));
            this.setBorder(BorderFactory.createMatteBorder(0,0,0,1, style.gray));

            pnlPadded = new JPanel();
            pnlPadded.setBorder(new EmptyBorder(30,20,10,20));
            pnlPadded.setPreferredSize(new Dimension(375,575));
            pnlPadded.setBackground(style.white);


            container = new JPanel();
            container.setPreferredSize(new Dimension(375,510));
            container.setLayout(new BorderLayout());

            JPanel pnlHeader = new JPanel();
            pnlHeader.setLayout(new BorderLayout());
            pnlHeader.setPreferredSize(new Dimension(375,80));
            pnlHeader.setBackground(style.white);

            JPanel pnlEmpty = new JPanel();
            pnlEmpty.setPreferredSize(new Dimension(150,50));
            pnlEmpty.setBackground(style.white);
            pnlEmpty.setLayout(new BorderLayout());
            pnlHeader.add(pnlEmpty, BorderLayout.WEST);

            JPanel pnlPfp = new JPanel();
            pnlPfp.setPreferredSize(new Dimension(170,50));
            pnlPfp.setBackground(style.white);
            pnlPfp.setLayout(new BorderLayout());
            pnlHeader.add(pnlPfp, BorderLayout.CENTER);

            JPanel pnlEdit = new JPanel();
            pnlEdit.setPreferredSize(new Dimension(150,50));
            pnlEdit.setBackground(style.white);
            pnlEdit.setLayout(new BorderLayout());
            pnlHeader.add(pnlEdit, BorderLayout.EAST);


            lblUserPfp = new JLabel();
            lblUserPfp.setIcon(style.iconBlackPfpPlaceholder);
            lblUserPfp.setHorizontalAlignment(SwingConstants.CENTER);
            pnlPfp.add(lblUserPfp);

            btnEditPFP = style.createBtnIconOnly(style.iconEdit,20,20);
            pnlEdit.add(btnEditPFP, BorderLayout.NORTH);



            lblAccountType = style.createLblP("Account Type: Idol", style.black);
            lblAccountType.setHorizontalAlignment(SwingConstants.CENTER);



            pnlDetails = new JPanel();
            pnlDetails.setLayout(new GridBagLayout());
            pnlDetails.setPreferredSize(new Dimension(150,375));
            pnlDetails.setBackground(style.white);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(0,25,0,0);
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx=150;
            gbc.weighty= 0;

            gbc.ipady = 15;
            gbc.gridy = 0;
            JLabel lblPersonal = style.createLblH3("Personal Details", style.black);
            pnlDetails.add(lblPersonal, gbc);

            gbc.ipady = 0;
            gbc.gridy = 1;
            JLabel lblFullName = style.createLblH4("Full Name", style.black);
            pnlDetails.add(lblFullName, gbc);

            gbc.gridy = 2;
            txtName = style.createTxtRounded("Juan Dela Cruz", style.lightGray, style.gray, 20);
            pnlDetails.add(txtName,gbc);

            gbc.ipady = 15;
            gbc.gridx = 2;
            btnEditName = style.createBtnIconOnly(style.iconEdit,20,20);
            pnlDetails.add(btnEditName,gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            JLabel lblUsername = style.createLblH4("Username", style.black);
            pnlDetails.add(lblUsername, gbc);

            gbc.ipady = 0;
            gbc.gridy = 4;
            txtUsername = style.createTxtRounded("juandelacruz", style.lightGray, style.gray, 20);
            pnlDetails.add(txtUsername,gbc);


            gbc.gridx = 2;
            btnEditUsername = style.createBtnIconOnly(style.iconEdit,20,20);
            pnlDetails.add(btnEditUsername,gbc);

            gbc.ipady = 20;
            gbc.gridx = 0;
            gbc.gridy = 5;
            JLabel lblEmail = style.createLblH4("Idol Type", style.black);
            pnlDetails.add(lblEmail,gbc);

            gbc.ipady = 0;
            gbc.gridy = 6;
            txtEmail = style.createTxtRounded("Content Creator", style.lightGray, style.gray, 20);
            pnlDetails.add(txtEmail,gbc);

            gbc.gridx = 2;
            btnEditEmail = style.createBtnIconOnly(style.iconEdit, 20, 20);
            pnlDetails.add(btnEditEmail,gbc);

            gbc.ipady = 20;
            gbc.gridx = 0;
            gbc.gridy = 7;
            JLabel lblGCash = style.createLblH4("GCash Number", style.black);
            pnlDetails.add(lblGCash, gbc);

            gbc.ipady = 0;
            gbc.gridy = 8;
            txtGCash = style.createTxtRounded("0929 123 4567", style.lightGray,style.gray, 20);
            pnlDetails.add(txtGCash, gbc);

            gbc.gridx = 2;
            btnEditGCash = style.createBtnIconOnly(style.iconEdit, 20, 20);
            pnlDetails.add(btnEditGCash, gbc);


            container.add(pnlHeader, BorderLayout.NORTH );
            container.setBackground(style.white);
            container.add(lblAccountType, BorderLayout.CENTER);
            container.add(pnlDetails, BorderLayout.SOUTH);

            pnlPadded.add(container);

            txtName.setEnabled(false);
            txtUsername.setEnabled(false);
            txtEmail.setEnabled(false);
            txtGCash.setEnabled(false);

            this.add(pnlPadded);
        }

        public JLabel getLblUserPfp() {
            return lblUserPfp;
        }

        public JTextField getTxtName() {
            return txtName;
        }

        public void setName(String txtName) {
            this.txtName.setText(txtName);
        }

        public JTextField getTxtUsername() {
            return txtUsername;
        }

        public void setUsername(String username) {
            this.txtUsername.setText(username);
        }

        public JTextField getTxtEmail() {
            return txtEmail;
        }

        public void seEmail(String email) {
            this.txtEmail.setText(email);
        }

        public JTextField getTxtGCash() {
            return txtGCash;
        }

        public void setTxtGCash(JTextField txtGCash) {
            this.txtGCash = txtGCash;
        }

        public JButton getBtnEditName() {
            return btnEditName;
        }

        public void setBtnEditNameListener(ActionListener listener) {
            this.btnEditName.addActionListener(listener);
        }

        public JButton getBtnEditUsername() {
            return btnEditUsername;
        }

        public void setBtnEditUsernameListener(ActionListener listener) {
            this.btnEditUsername.addActionListener(listener);
        }

        public JButton getBtnEditEmail() {
            return btnEditEmail;
        }

        public void setBtnEditEmailListener(ActionListener listener) {
            this.btnEditEmail.addActionListener(listener);
        }

        public JButton getBtnEditPFP() {
            return btnEditPFP;
        }

        public void setBtnEditPFPListener(ActionListener listener) {
            this.btnEditPFP.addActionListener(listener);
        }

        public JButton getBtnEditGCash() {
            return btnEditGCash;
        }

        public void setBtnEditGCashListener(ActionListener listener) {
            this.btnEditGCash.addActionListener(listener);
        }

        public void setBtnEditPFP(JButton btnEditPFP) {
            this.btnEditPFP = btnEditPFP;
        }

        public void setBtnEditName(JButton btnEditName) {
            this.btnEditName = btnEditName;
        }

        public void setBtnEditUsername(JButton btnEditUsername) {
            this.btnEditUsername = btnEditUsername;
        }

        public void setBtnEditEmail(JButton btnEditEmail) {
            this.btnEditEmail = btnEditEmail;
        }

        public void setBtnEditGCash(JButton btnEditGCash) {
            this.btnEditGCash = btnEditGCash;
        }

    }

    public class AvailabilityPanel extends JPanel {

        JPanel container;

        JButton btnEditDay;

        JComboBox<String> cmbDate;

        JComboBox<String> cmbStartTime;

        JComboBox<String> cmbEndTime;

        JButton btnConfirm;

        JPanel pnlDate;

        JTextField txtVideoRate;

        JTextField txtVoiceRate;

        JLabel lblVideoRate;

        JLabel lblVoiceRate;

        public AvailabilityPanel() {
            this.setLayout(new BorderLayout());
            this.setBackground(style.white);
            this.setPreferredSize(new Dimension(375, 575));
            this.setBorder(style.padding);

            this.setBorder(new EmptyBorder(40,0,0,0));
            JLabel lblSetTime = style.createLblH1("Set Time", style.black);
            lblSetTime.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(lblSetTime, BorderLayout.NORTH);



            pnlDate = new JPanel(new BorderLayout());
            pnlDate.setBorder(style.padding);
            pnlDate.setBackground(style.white);

            cmbDate = new JComboBox<>(new String[]{
                    "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"});
            cmbDate.setPreferredSize(new Dimension(150,30));

            btnEditDay = style.createBtnTxtOnly("EDIT", style.purple);
            btnEditDay.setPreferredSize(new Dimension(80,30));
            pnlDate.add(cmbDate, BorderLayout.WEST);
            pnlDate.add(btnEditDay, BorderLayout.EAST);

            container = new JPanel();
            container.setLayout(new BorderLayout());
            container.setPreferredSize(new Dimension(375,350));
            container.setBackground(style.white);
            container.setBorder(style.padding);


            JPanel pnlTimeHeader = new JPanel();
            pnlTimeHeader.setLayout(new BorderLayout());
            pnlTimeHeader.setPreferredSize(new Dimension(375,50));
            pnlTimeHeader.setBackground(style.white);
            pnlTimeHeader.setBorder(new EmptyBorder(0,10,0,6));


            JLabel lblStartTime = style.createLblH3("Start Time", style.black);
            pnlTimeHeader.add(lblStartTime, BorderLayout.WEST);

            JLabel lblEndTime = style.createLblH3("End Time", style.black);
            pnlTimeHeader.add(lblEndTime, BorderLayout.EAST);

            container.add(pnlTimeHeader, BorderLayout.NORTH);

            cmbStartTime = new JComboBox<>(new String[]{
                    "07:00:00", "8:00:00", "9:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00","16:00:00","17:00:00" });
            cmbStartTime.setPreferredSize(new Dimension(150,30));
            container.add(cmbStartTime, BorderLayout.WEST);

            cmbEndTime = new JComboBox<>(new String[]{
                    "8:00:00", "9:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00","16:00:00","17:00:00", "18:00:00"});
            cmbEndTime.setPreferredSize(new Dimension(150,30));
            container.add(cmbEndTime, BorderLayout.EAST);

            btnConfirm = style.createBtnRounded("Confirm", style.purple, style.white, 10);
            btnConfirm.setPreferredSize(new Dimension(100,50));

            JPanel pnlFooter = new JPanel();
            pnlFooter.setPreferredSize(new Dimension(100,250));
            pnlFooter.setBorder(new EmptyBorder(5,0,0,0));
            pnlFooter.setBackground(style.white);
            lblVideoRate = style.createLblH3("Video Call Rate:", style.black);
            lblVoiceRate = style.createLblH3("Voice Call Rate:", style.black);

            txtVideoRate = style.createTxtRounded("", style.white, style.black, 10);
            txtVoiceRate = style.createTxtRounded("", style.white, style.black, 10);

            pnlFooter.setLayout(new GridBagLayout());
            pnlFooter.setBackground(style.white);

            GridBagConstraints gbcLblVideoRate = new GridBagConstraints();
            gbcLblVideoRate.gridx = 0;
            gbcLblVideoRate.gridy = 0;
            gbcLblVideoRate.anchor = GridBagConstraints.WEST;
            gbcLblVideoRate.insets = new Insets(5, 10, 5, 10);

            GridBagConstraints gbcTxtVideoRate = new GridBagConstraints();
            gbcTxtVideoRate.gridx = 0;
            gbcTxtVideoRate.gridy = 1;
            gbcTxtVideoRate.fill = GridBagConstraints.HORIZONTAL;
            gbcTxtVideoRate.weightx = 1.0;
            gbcTxtVideoRate.insets = new Insets(5, 10, 5, 10);

            pnlFooter.add(lblVideoRate, gbcLblVideoRate);
            pnlFooter.add(txtVideoRate, gbcTxtVideoRate);

            GridBagConstraints gbcLblVoiceRate = new GridBagConstraints();
            gbcLblVoiceRate.gridx = 1;
            gbcLblVoiceRate.gridy = 0;
            gbcLblVoiceRate.anchor = GridBagConstraints.WEST;
            gbcLblVoiceRate.insets = new Insets(5, 10, 5, 10);

            GridBagConstraints gbcTxtVoiceRate = new GridBagConstraints();
            gbcTxtVoiceRate.gridx = 1;
            gbcTxtVoiceRate.gridy = 1;
            gbcTxtVoiceRate.fill = GridBagConstraints.HORIZONTAL;
            gbcTxtVoiceRate.weightx = 1.0;
            gbcTxtVoiceRate.insets = new Insets(5, 10, 5, 10);

            pnlFooter.add(lblVoiceRate, gbcLblVoiceRate);
            pnlFooter.add(txtVoiceRate, gbcTxtVoiceRate);

            GridBagConstraints gbcButton = new GridBagConstraints();
            gbcButton.gridx = 0;
            gbcButton.gridy = 2;
            gbcButton.gridwidth = 2;
            gbcButton.anchor = GridBagConstraints.CENTER;
            gbcButton.insets = new Insets(20, 0, 0, 0);

            pnlFooter.add(btnConfirm, gbcButton);
            container.add(pnlFooter,BorderLayout.SOUTH);

            this.add(pnlDate, BorderLayout.CENTER);
            this.add(container, BorderLayout.SOUTH);
        }

        public JButton getBtnEditDay() {
            return btnEditDay;
        }

        public JComboBox<String> getCmbDate() {
            return cmbDate;
        }

        public JComboBox<String> getCmbStartTime() {
            return cmbStartTime;
        }

        public JComboBox<String> getCmbEndTime() {
            return cmbEndTime;
        }

        public JButton getBtnConfirm() {
            return btnConfirm;
        }

        public void setBtnEditDayListener(ActionListener listener) {
            this.btnEditDay.addActionListener(listener);
        }

        public void setCmbDate(String[] cmbDate) {
            this.cmbDate = new JComboBox<>(cmbDate);
        }

        public void setCmbDateListener(ActionListener listener) {
            this.cmbDate.addActionListener(listener);
        }

        public void setCmbStartTime(String[] cmbStartTime) {
            this.cmbStartTime.setModel(new DefaultComboBoxModel<>(cmbStartTime));
        }

        public void setCmbEndTime(String[] cmbEndTime) {
            this.cmbEndTime.setModel(new DefaultComboBoxModel<>(cmbEndTime));
        }

        public String getDateChosen() {
            return cmbDate.getItemAt(cmbDate.getSelectedIndex());
        }

        public String getStartTimeChosen() {
            return cmbStartTime.getItemAt(cmbStartTime.getSelectedIndex());
        }

        public String getEndTimeChosen() {
            return cmbEndTime.getItemAt(cmbEndTime.getSelectedIndex());
        }

        public void setBtnConfirmListener(ActionListener listener) {
            this.btnConfirm.addActionListener(listener);
        }

        public JTextField getTxtVideoRate() {
            return txtVideoRate;
        }

        public JTextField getTxtVoiceRate() {
            return txtVoiceRate;
        }

    }



    public static void main(String[] args) {
        AccountSettingsView view = new AccountSettingsView();

    }

}
