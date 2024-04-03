package client.fan.view.application_pages;

import shared.res.Stylesheet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * The IdolView is the home page of the application where fans can choose an idol to book. It contains:
 * 1. All idols
 */
public class IdolsView extends JPanel {
    /**
     * Array of buttons.
     */
    private JButton[] btnIdols;

    private JButton btnView;
    /**
     * The search bar.
     */
    private JTextField txtSearchbar;
    /**
     * The search button.
     */
    private JButton btnSearch;
    /**
     * The stylesheet.
     */
    private Stylesheet style = new Stylesheet();
    /**
     * Constructs a panel of IdolsView
     */
    public IdolsView() {
        this.setBackground(style.lightGray);
        this.setLayout(new BorderLayout());

        JPanel container = new JPanel(new BorderLayout());
        container.setBorder(style.padding);
        container.setBackground(style.white);
        add(container, BorderLayout.CENTER);

        add(new HeaderPanel(), BorderLayout.NORTH);

        this.setPreferredSize(new Dimension(1100,755));
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

            txtSearchbar = style.createTxtRounded("Search idol",style.lightGray, style.gray, 30);
            add(txtSearchbar);

            btnSearch = style.createBtnIconOnly(style.iconSearch, 25,25);
            add(btnSearch);

            this.setPreferredSize(new Dimension(1100,45));
        }
    }

    /**
     * The panel to hold the idols.
     */
    class IdolsPanel extends JPanel {
        /**
         * The scroll pane to hold the idols.
         */
        private JScrollPane scrollPane;
        /**
         * Constructs a panel of IdolsPanel.
         */
        public IdolsPanel() {
            GridLayout gridLayout = new GridLayout(5,2);
            gridLayout.setHgap(10);
            gridLayout.setVgap(10);
            this.setLayout(gridLayout);
            this.setBackground(style.black);

            btnIdols = new JButton[10];
            for (int i = 0; i < 10; i++) {
                JButton btnIdol = style.createBtnIconOnly(style.iconPfpPlaceholder,20,20);
                btnIdols[i] = btnIdol;
                add(btnIdol);
            }

            this.setPreferredSize(new Dimension(1100,500));
        }
    }
}
