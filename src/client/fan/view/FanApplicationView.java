package client.fan.view;

import client.fan.view.application_pages.BookingView;
import client.fan.view.application_pages.IdolsView;
import client.fan.view.application_pages.MyIdolsView;
import client.fan.view.application_pages.VirtualMeetupView;
import shared.res.Stylesheet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * The main application for fan users that contain the different subpages.
 */
public class FanApplicationView extends JFrame {
    /**
     * Panel of idolsView.
     */
    private IdolsView idolsView;
    /**
     * Panel of booking view.
     */
    private BookingView bookingView;
    /**
     * Panel of my idols view.
     */
    private MyIdolsView myIdolsView;
    /**
     * Panel of virtual meetup view.
     */
    private VirtualMeetupView virtualMeetupView;
    /**
     * Stylesheet of UI components.
     */
    private Stylesheet style = new Stylesheet();
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
     * The MyIdols button.
     */
    private JButton btnNavMyIdols;
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
    public FanApplicationView() {
        Container contentArea = new JPanel(new BorderLayout());
        contentArea.setBackground(style.white);

        pnlSidebar = new SidebarPanel();
        contentArea.add(pnlSidebar, BorderLayout.WEST);

        cardLayout = new CardLayout(0,0);

        pnlCards = new JPanel(cardLayout);
        pnlCards.setBackground(style.white);
        pnlCards.setPreferredSize(new Dimension(1100,755));
        contentArea.add(pnlCards, BorderLayout.EAST);

        idolsView = new IdolsView();
        pnlCards.add(idolsView, "home");

        myIdolsView = new MyIdolsView();
        pnlCards.add(myIdolsView, "idols");

        bookingView = new BookingView();
        pnlCards.add(bookingView, "booking");

        virtualMeetupView = new VirtualMeetupView();
        pnlCards.add(virtualMeetupView, "meetup");

        cardLayout.show(pnlCards, "home");

        this.setContentPane(contentArea);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(1300,800);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public SidebarPanel getPnlSidebar() {
        return pnlSidebar;
    }



    /**
     * The SidebarPanel contains the navigation buttons and user details.
     */
    public class SidebarPanel extends JPanel {
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
            lblUserType = style.createLblP("Fan", style.white);
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
            btnNavMyIdols = style.createBtnTxtOnly("MY IDOLS", style.white);
            btnNavMyIdols.setHorizontalAlignment(SwingConstants.LEFT);
            add(btnNavMyIdols, gbc);

            gbc.gridy = 7;
            btnNavLogout = style.createBtnTxtOnly("LOGOUT", style.white);
            btnNavLogout.setHorizontalAlignment(SwingConstants.LEFT);
            add(btnNavLogout, gbc);

            this.setPreferredSize(new Dimension(200,800));
        }

        public void setSideBarName(String name) {
            lblUser.setText(name);
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
     * Retrieves the current JTextField of txtSearchBar.
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
     * Retrieves the current JButton of btnNavMyIdols.
     * @return The current btnNavMyIdols.
     */
    public JButton getBtnNavMyIdols() {
        return btnNavMyIdols;
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

    public static void main(String[] args) {
        new FanApplicationView();
    }
}
