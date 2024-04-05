package client.idol.view.application_pages;

import shared.res.Stylesheet;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * The AccountSettingsView contains the idol's account information. It contains:
 * 1. Personal details.
 * 2. Security details.
 * 3. Bio
 * 4. Profile picture (pending).
 * 5. Available days and respective available times.
 * 6. Delete account.
 */
public class AccountSettingsView extends JFrame {

    Stylesheet style = new Stylesheet();

    private JPanel pnlHeader;
    private JPanel pnlTitle;
    private JPanel pnlEdit;



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
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
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

        JPanel container;

        SidePanel pnlSide;

        DetailsPanel pnlDetails;

        AvailabilityPanel pnlAvailability;

        public EditPanel() {
            this.setLayout(new BorderLayout());
            this.setBorder(style.padding);

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
    }

    public class SidePanel extends JPanel {

        JPanel pnlPadded;
        JPanel container;

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
            JLabel lblProfile = style.createLblP("My Profile", style.purple);
            JLabel lblDelete = style.createLblP("Delete Account", style.red);

            container.add(lblProfile);
            container.add(lblDelete);

            pnlPadded.add(container, BorderLayout.NORTH);

            add(pnlPadded);
        }
    }

    public class DetailsPanel extends JPanel{

        JLabel lblUserPfp;

        JPanel container;

        JPanel pnlPadded;

        public DetailsPanel() {
            this.setLayout(new BorderLayout());
            this.setBackground(style.white);
            this.setPreferredSize(new Dimension(375,575));
            this.setBorder(BorderFactory.createMatteBorder(0,0,0,1, style.gray));

            pnlPadded = new JPanel();
            pnlPadded.setBorder(style.padding);
            pnlPadded.setPreferredSize(new Dimension(375,575));
            pnlPadded.setBackground(style.black);


            container = new JPanel();
            container.setPreferredSize(new Dimension(375,510));
            container.setLayout(new BorderLayout());


            pnlPadded.add(container);
            this.add(pnlPadded);


        }
    }

    public class AvailabilityPanel extends JPanel {

        public AvailabilityPanel() {
            this.setLayout(new BorderLayout());
            this.setBackground(style.white);
            this.setPreferredSize(new Dimension(375, 575));
        }
    }



    public static void main(String[] args) {
        AccountSettingsView view = new AccountSettingsView();

    }

}
