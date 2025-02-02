package client.idol.view;

import client.idol.view.application_pages.VirtualMeetupView;
import client.idol.view.application_pages.AccountSettingsView;
import client.idol.view.application_pages.CalendarView;
import client.idol.view.application_pages.FanbaseView;
import shared.res.Stylesheet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * The main application for idol users that contain the different subpages.
 */
public class IdolApplicationView extends JFrame {
    /**
     * Panel of idolsView.
     */
    private FanbaseView fanbaseView;
    /**
     * Panel of booking view.
     */
    private AccountSettingsView accountSettingsView;
    /**
     * Panel of calendar view.
     */
    private CalendarView calendarView;
    /**
     * Panel of virtual meetup view.
     */
    private VirtualMeetupView virtualMeetupView;
    /**
     * Stylesheet of UI components.
     */
    private Stylesheet style = new Stylesheet();
    /**
     * The header panel.
     */
    private HeaderPanel pnlHeader;
    /**
     * The sidebar panel.
     */
    private SidebarPanel pnlSidebar;
    /**
     * JPanel that holds multiple components.
     */
    private JPanel pnlCards;
    /**
     * The card layout that controls different components.
     */
    private CardLayout cardLayout;
    /**
     * The idol searchbar.
     */
    private JTextField txtSearchbar;
    /**
     * The button to initiate search.
     */
    private JButton btnSearch;
    /**
     * The Home button.
     */
    private JButton btnNavHome;
    /**
     * The Account button.
     */
    private JButton btnNavAccount;
    /**
     * The Calendar account.
     */
    private JButton btnNavCalendar;
    /**
     * The Logout button.
     */
    private JButton btnNavLogout;
    /**
     * Full name of the user
     */
    private JLabel lblUser;
    /**
     * The type of user (fan or verified idol).
     */
    private JLabel lblUserType;
    /**
     * The profile picture of the user.
     */
    private JLabel lblUserPfp;

    /**
     * Constructs a frame of FanApplicationView.
     */
    public IdolApplicationView() {
        Container contentArea = new JPanel(new BorderLayout());
        contentArea.setBackground(style.white);

        pnlSidebar = new SidebarPanel();
        contentArea.add(pnlSidebar, BorderLayout.WEST);

        pnlHeader = new HeaderPanel();
        contentArea.add(pnlHeader, BorderLayout.EAST);

        cardLayout = new CardLayout(0,0);

        pnlCards = new JPanel(cardLayout);
        pnlCards.setBackground(style.white);
        pnlCards.setPreferredSize(new Dimension(1100,755));
        contentArea.add(pnlCards, BorderLayout.EAST);

        fanbaseView = new FanbaseView();
        pnlCards.add(fanbaseView, "home");

        accountSettingsView = new AccountSettingsView();
        pnlCards.add(accountSettingsView, "account");

        calendarView = new CalendarView();
        pnlCards.add(calendarView, "calendar");

        virtualMeetupView = new VirtualMeetupView();
        pnlCards.add(virtualMeetupView, "meetup");

        this.setContentPane(contentArea);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(1300,800);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * The HeaderPanel contains the search bar and the navigation.
     */
    class HeaderPanel extends JPanel {
        /**
         * Constructs a panel of HeaderPanel.
         */
        public HeaderPanel() {
            this.setBackground(style.purple);
            this.setLayout(new FlowLayout());
            this.setBorder(new EmptyBorder(0,0,0,60));

            txtSearchbar = style.createTxtRounded("Search idol",style.lightGray, style.gray, 20);
            add(txtSearchbar);

            btnSearch = style.createBtnIconOnly(style.iconSearch, 25,25);
            add(btnSearch);

            this.setPreferredSize(new Dimension(1100,45));
        }
    }

    /**
     * The SidebarPanel contains the navigation buttons and user details.
     */
    class SidebarPanel extends JPanel {
        /**
         * Constructs a panel of SidebarPanel.
         */
        public SidebarPanel() {
            this.setBackground(style.black);
            this.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(2,0,2,0);
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.BOTH;

            gbc.ipady = 10;
            gbc.gridy = 1;
            lblUserPfp = new JLabel();
            lblUserPfp.setIcon(style.iconPfpPlaceholder);
            lblUserPfp.setHorizontalAlignment(SwingConstants.CENTER);
            add(lblUserPfp, gbc);

            gbc.ipady = 4;
            gbc.gridy = 2;
            lblUser = style.createLblH3("John Doe", style.white);
            lblUser.setHorizontalAlignment(SwingConstants.CENTER);
            add(lblUser, gbc);

            gbc.ipady = 5;
            gbc.gridy = 3;
            lblUserType = style.createLblP("Verified Idol", style.white);
            lblUserType.setHorizontalAlignment(SwingConstants.CENTER);
            add(lblUserType, gbc);

            gbc.ipady = 80;
            gbc.gridy = 4;
            JLabel lblBreaker = new JLabel("");
            add(lblBreaker, gbc);

            gbc.ipady = 30;
            gbc.gridy = 5;
            btnNavHome = style.createBtnTxtOnly("HOME", style.white);
            btnNavHome.setHorizontalAlignment(SwingConstants.LEFT);
            add(btnNavHome, gbc);

            gbc.gridy = 6;
            btnNavAccount = style.createBtnTxtOnly("ACCOUNT", style.white);
            btnNavAccount.setHorizontalAlignment(SwingConstants.LEFT);
            add(btnNavAccount, gbc);

            gbc.gridy = 7;
            btnNavCalendar = style.createBtnTxtOnly("CALENDAR", style.white);
            btnNavCalendar.setHorizontalAlignment(SwingConstants.LEFT);
            add(btnNavCalendar, gbc);

            gbc.gridy = 8;
            btnNavLogout = style.createBtnTxtOnly("LOGOUT", style.white);
            btnNavLogout.setHorizontalAlignment(SwingConstants.LEFT);
            add(btnNavLogout, gbc);

            this.setPreferredSize(new Dimension(200,800));
        }
    }

    /**
     * Retrieves the current JPanel of pnlCards.
     * @return The current pnlCards.
     */
    public JPanel getPnlCards() {
        return pnlCards;
    }

    /**
     * Retrieves the current CardLayout of cardLayout.
     * @return The current cardLayout.
     */
    public CardLayout getCardLayout() {
        return cardLayout;
    }

    /**
     * Retrieves the current JTextField of txtSearchbar.
     * @return The current txtSearchbar.
     */
    public JTextField getTxtSearchbar() {
        return txtSearchbar;
    }

    /**
     * Retrieves the current JButton of btnSearch.
     * @return The current btnSearch.
     */
    public JButton getBtnSearch() {
        return btnSearch;
    }

    /**
     * Retrieves the current JButton of btnNavHome.
     * @return The current btnNavHome.
     */
    public JButton getBtnNavHome() {
        return btnNavHome;
    }

    /**
     * Retrieves the current JButton of btnNavAccount.
     * @return The current btnNavAccount.
     */
    public JButton getBtnNavAccount() {
        return btnNavAccount;
    }

    /**
     * Retrieves the current JButton of btnNavCalendar.
     * @return The current btnNavCalendar.
     */
    public JButton getBtnNavCalendar() {
        return btnNavCalendar;
    }

    /**
     * Retrieves the current JButton of btnNavLogout.
     * @return The current btnNavLogout.
     */
    public JButton getBtnNavLogout() {
        return btnNavLogout;
    }

    /**
     * Retrieves the current JLabel of lblUser.
     * @return The current lblUser.
     */
    public JLabel getLblUser() {
        return lblUser;
    }

    /**
     * Retrieves the current JLabel of lblUserType.
     * @return The current lblUserType.
     */
    public JLabel getLblUserType() {
        return lblUserType;
    }

    /**
     * Retrieves the current JLabel of lblUserPfp.
     * @return The current lblUserPfp.
     */
    public JLabel getLblUserPfp() {
        return lblUserPfp;
    }

    public FanbaseView getFanbaseView() {
        return fanbaseView;
    }

    public void setFanbaseView(FanbaseView fanbaseView) {
        this.fanbaseView = fanbaseView;
    }

    public AccountSettingsView getAccountSettingsView() {
        return accountSettingsView;
    }

    public void setAccountSettingsView(AccountSettingsView accountSettingsView) {
        this.accountSettingsView = accountSettingsView;
    }

    public CalendarView getCalendarView() {
        return calendarView;
    }

    public void setCalendarView(CalendarView calendarView) {
        this.calendarView = calendarView;
    }

    public VirtualMeetupView getVirtualMeetupView() {
        return virtualMeetupView;
    }

    public void setVirtualMeetupView(VirtualMeetupView virtualMeetupView) {
        this.virtualMeetupView = virtualMeetupView;
    }
}
