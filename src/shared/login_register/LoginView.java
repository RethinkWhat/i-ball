package shared.login_register;

import shared.res.Stylesheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The LoginView is the sign-in page of the fan.
 */
public class LoginView extends JFrame {
    /**
     * Instance of stylesheet.
     */
    private Stylesheet style = new Stylesheet();
    /**
     * Username input field.
     */
    private JTextField txtUsername;
    /**
     * Password input field.
     */
    private JPasswordField txtPassword;
    /**
     * Show password check box.
     */
    private JCheckBox chkShowPassword;
    /**
     * Login button.
     */
    private JButton btnLogin;
    /**
     * Signup button.
     */
    private JButton btnSignup;
    /**
     * Error message.
     */
    private JLabel lblErrorMessage;
    /**
     * GridBagConstraints to control positioning of UI components.
     */
    private GridBagConstraints gbc;

    /**
     * Constructs a LoginView frame.
     */
    public LoginView() {
        super("Sign In");

        Container contentArea = new JPanel(new BorderLayout());

        JPanel pnlLogin = new LoginPanel();
        contentArea.add(pnlLogin, BorderLayout.EAST);

        JPanel pnlLogo = new LogoPanel();
        contentArea.add(pnlLogo, BorderLayout.WEST);

        this.setContentPane(contentArea);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(950,560);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Panel that contains login I/O components.
     */
    class LoginPanel extends JPanel {
        /**
         * Constructs a panel of LoginPanel.
         */
        public LoginPanel() {
            this.setBackground(style.white);
            this.setLayout(new GridBagLayout());
            this.setBorder(style.padding);

            gbc = new GridBagConstraints();
            gbc.insets = new Insets(2,0,2,0);
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridwidth = 1;

            gbc.ipady = 80;
            gbc.gridx = 0;
            gbc.gridy = 0;
            JLabel lblHeading = style.createLblH1("Log In", style.black);
            lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
            add(lblHeading, gbc);

            gbc.ipady = 5;
            gbc.gridy = 1;
            JLabel lblUsername = style.createLblH3("Username", style.black);
            add(lblUsername, gbc);

            gbc.ipady = 5;
            gbc.gridy = 2;
            txtUsername = style.createTxtRounded("Username", style.lightGray, style.gray, 20);
            add(txtUsername, gbc);

            gbc.ipady = 10;
            gbc.gridy = 3;
            JLabel lblPassword = style.createLblH3("Password", style.black);
            add(lblPassword, gbc);

            gbc.ipady = 5;
            gbc.gridy = 4;
            txtPassword = style.createPwdRounded(style.lightGray, style.gray, 20);
            txtPassword.setText("Password");
            txtPassword.setEchoChar((char)0);
            add(txtPassword, gbc);

            gbc.gridy = 5;
            lblErrorMessage = style.createLblP("",style.red);
            add(lblErrorMessage, gbc);

            gbc.gridy = 6;
            chkShowPassword = new JCheckBox("Show Password");
            chkShowPassword.setFont(new Font("Arial", Font.PLAIN, 14));
            chkShowPassword.setHorizontalAlignment(SwingConstants.LEFT);
            chkShowPassword.setBackground(style.white);
            add(chkShowPassword, gbc);

            gbc.ipady = 10;
            gbc.gridy = 7;
            btnLogin = style.createBtnRounded("Log In", style.white, style.purple,10);
            add(btnLogin, gbc);

            gbc.ipady = 5;
            gbc.gridy = 8;
            JLabel lblNoAccount = style.createLblP("Don't have an account?", style.black);
            lblNoAccount.setHorizontalAlignment(SwingConstants.CENTER);
            add(lblNoAccount, gbc);

            gbc.gridy = 9;
            btnSignup = style.createBtnTxtOnly("Sign up for free.", style.purple);
            add(btnSignup, gbc);

            this.setPreferredSize(new Dimension(475,560));
        }
    }

    /**
     * Panel that contains the logo and tagline.
     */
    class LogoPanel extends JPanel {
        /**
         * Constructs a panel of LogoPanel.
         */
        public LogoPanel() {
            this.setBorder(style.padding);
            this.setLayout(new GridBagLayout());
            this.setBackground(style.purple);

            gbc = new GridBagConstraints();
            gbc.insets = new Insets(20,10,20,10);
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.CENTER;

            gbc.gridx = 0;
            gbc.gridy = 0;
            JLabel lblLogo = style.createLblP("",style.white);
            lblLogo.setIcon(style.iBallLogo);
            add(lblLogo, gbc);

            gbc.gridy = 1;
            JLabel lblTagline = style.createLblH2("Meet your idols",style.white);
            lblTagline.setHorizontalAlignment(SwingConstants.CENTER);
            add(lblTagline, gbc);

            this.setPreferredSize(new Dimension(475,560));
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
     * Retrieves the String that populates the txtUsername
     */
    public String getUsername() {
        return txtUsername.getText();
    }


    /**
     * Retrieves the current JPasswordField of txtPassword.
     * @return The current txtPassword.
     */
    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    /**
     * Retrieves the String that populates the txtPassword
     */
    public String getPassword() {
        return String.valueOf(txtPassword.getPassword());
    }

    /**
     * Retrieves the current JCheckBox of chkShowPassword.
     * @return The current chkShowPassword.
     */
    public JCheckBox getChkShowPassword() {
        return chkShowPassword;
    }

    /**
     * Retrieves the current JButton of btnLogin.
     * @return The current btnLogin.
     */
    public JButton getBtnLogin() {
        return btnLogin;
    }

    /**
     * Retrieves the current JButton of btnSignup.
     * @return The current btnSignup.
     */
    public JButton getBtnSignup() {
        return btnSignup;
    }

    /**
     * Sets a specified action listener for btnLogin.
     * @param actionListener The specified action listener.
     */
    public void setLoginListener(ActionListener actionListener) {
        btnLogin.addActionListener(actionListener);
    }

    /**
     * Sets a specified action listener for btnSignup.
     * @param actionListener
     */
    public void setSignupListener(ActionListener actionListener) {
        btnSignup.addActionListener(actionListener);
    }
}
