package client.fan.view;

import shared.res.Stylesheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The RegisterView is the registration page of the fan.
 */
public class RegisterView extends JFrame {
    /**
     * Instance of stylesheet.
     */
    private Stylesheet style = new Stylesheet();
    /**
     * Username input field.
     */
    private JTextField txtUsername;
    /**
     * Email input field.
     */
    private JTextField txtEmail;
    /**
     * GCash Number input field.
     */
    private JTextField txtGCash;
    /**
     * Password input field.
     */
    private JPasswordField txtPassword;
    /**
     * Confirm password input field.
     */
    private JPasswordField txtConfirmPassword;
    /**
     * Show password check box.
     */
    private JCheckBox chkShowPassword;
    /**
     * Show confirm password check box.
     */
    private JCheckBox chkShowConfirmPassword;
    /**
     * Signup button.
     */
    private JButton btnSignup;
    /**
     * Login button.
     */
    private JButton btnLogin;
    /**
     * Error message.
     */
    private JLabel lblErrorMessage;
    /**
     * GridBagConstraints to control positioning of UI components.
     */
    private GridBagConstraints gbc;

    /**
     * Constructs a RegisterView frame.
     */
    public RegisterView() {
        super("Sign Up");

        Container contentArea = new JPanel(new BorderLayout());
        contentArea.setBackground(style.purple);

        JPanel pnlSignup = new SignupPanel();
        pnlSignup.setPreferredSize(new Dimension(500,680));
        contentArea.add(pnlSignup, BorderLayout.CENTER);

        this.setContentPane(contentArea);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(600,700);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Panel that contains signup I/O components.
     */
    class SignupPanel extends JPanel {
        /**
         * Constructs a panel of SignupPanel.
         */
        public SignupPanel() {
            this.setBackground(style.white);
            this.setBorder(style.padding);
            this.setLayout(new GridBagLayout());

            gbc = new GridBagConstraints();
            gbc.insets = new Insets(2,80,2,80);
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 2;

            gbc.ipady = 40;
            gbc.gridx = 0;
            gbc.gridy = 0;
            JLabel lblHeading = style.createLblH1("Sign Up", style.black);
            lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
            add(lblHeading, gbc);

            gbc.ipady = 5;
            gbc.gridy = 1;
            JLabel lblUsername = style.createLblH3("Username", style.black);
            add(lblUsername, gbc);

            gbc.gridy = 2;
            txtUsername = style.createTxtRounded("Username", style.lightGray, style.gray, 20);
            add(txtUsername, gbc);

            gbc.gridy = 3;
            JLabel lblEmail = style.createLblH3("Email", style.black);
            add(lblEmail, gbc);

            gbc.gridy = 4;
            txtEmail = style.createTxtRounded("Email", style.lightGray, style.gray, 20);
            add(txtEmail, gbc);

            gbc.gridy = 5;
            JLabel lblGCashNumber = style.createLblH3("GCash Number", style.black);
            add(lblGCashNumber, gbc);

            gbc.gridy = 6;
            txtGCash = style.createTxtRounded("GCash Number", style.lightGray, style.gray, 20);
            add(txtGCash, gbc);

            gbc.gridy = 7;
            JLabel lblPassword = style.createLblH3("Password", style.black);
            add(lblPassword, gbc);

            gbc.gridy = 8;
            txtPassword = style.createPwdRounded(style.lightGray, style.gray, 20);
            txtPassword.setText("Password");
            txtPassword.setEchoChar((char)0);
            add(txtPassword, gbc);

            gbc.gridy = 9;
            chkShowPassword = new JCheckBox("Show Password");
            chkShowPassword.setFont(new Font("Arial", Font.PLAIN, 14));
            chkShowPassword.setHorizontalAlignment(SwingConstants.LEFT);
            chkShowPassword.setBackground(style.white);
            add(chkShowPassword, gbc);

            gbc.gridy = 10;
            JLabel lblConfirmPassword = style.createLblH3("Confirm Password", style.black);
            add(lblConfirmPassword, gbc);

            gbc.gridy = 11;
            txtConfirmPassword = style.createPwdRounded(style.lightGray, style.gray, 20);
            txtConfirmPassword.setText("Confirm Password");
            txtConfirmPassword.setEchoChar((char)0);
            add(txtConfirmPassword, gbc);

            gbc.gridy = 12;
            chkShowConfirmPassword = new JCheckBox("Show Password");
            chkShowConfirmPassword.setFont(new Font("Arial", Font.PLAIN, 14));
            chkShowConfirmPassword.setHorizontalAlignment(SwingConstants.LEFT);
            chkShowConfirmPassword.setBackground(style.white);
            add(chkShowConfirmPassword, gbc);

            gbc.gridy = 13;
            lblErrorMessage = style.createLblP("", style.red);
            add(lblErrorMessage, gbc);

            gbc.ipady = 10;
            gbc.gridy = 14;
            btnSignup = style.createBtnRounded("Sign Up", style.white, style.purple,10);
            add(btnSignup, gbc);

            gbc.ipady = 5;
            gbc.gridy = 15;
            JLabel lblHaveAccount = style.createLblP("Have an account?", style.black);
            lblHaveAccount.setHorizontalAlignment(SwingConstants.CENTER);
            add(lblHaveAccount, gbc);

            gbc.gridy = 16;
            btnLogin = style.createBtnTxtOnly("Log in.", style.purple);
            add(btnLogin, gbc);

            this.setPreferredSize(new Dimension(500,540));
        }
    }

    /**
     * Retrieves the current JTextField of txtUsername.
     * @return The current txtUsername.
     */
    public JTextField getTxtUsername() {
        return txtUsername;
    }

    /**
     * Retrieves the current JTextField of txtEmail.
     * @return The current txtEmail.
     */
    public JTextField getTxtEmail() {
        return txtEmail;
    }

    /**
     * Retrieves the current JTextField of txtGCash.
     * @return The current txtGCash.
     */
    public JTextField getTxtGCash() {
        return txtGCash;
    }

    /**
     * Retrieves the current JTextField of txtPassword.
     * @return The current txtPassword.
     */
    public JTextField getTxtPassword() {
        return txtPassword;
    }

    /**
     * Retrieves the current JCheckBox of chkShowPassword.
     * @return The current chkShowPassword.
     */
    public JCheckBox getChkShowPassword() {
        return chkShowPassword;
    }

    /**
     * Retrieves the current JCheckBox of chkShowConfirmPassword.
     * @return The current chkShowConfirmPassword.
     */
    public JCheckBox getChkShowConfirmPassword() {
        return chkShowConfirmPassword;
    }

    /**
     * Retrieves the current JButton of btnSignup.
     * @return The current btnSignup.
     */
    public JButton getBtnSignup() {
        return btnSignup;
    }

    /**
     * Retrieves the current JButton of btnLogin.
     * @return The current btnLogin.
     */
    public JButton getBtnLogin() {
        return btnLogin;
    }

    /**
     * Retrieves the current JLabel of lblErrorMessage.
     * @return The current lblErrorMessage.
     */
    public JLabel getLblErrorMessage() {
        return lblErrorMessage;
    }

    /**
     * Sets a specified action listener for btnSignup.
     * @param actionListener The specified action listener.
     */
    public void setSignupListener(ActionListener actionListener) {
        btnSignup.addActionListener(actionListener);
    }

    /**
     * Sets a specified action listener for btnLogin.
     * @param actionListener The specified action listener.
     */
    public void setLoginListener(ActionListener actionListener) {
        btnLogin.addActionListener(actionListener);
    }
}
