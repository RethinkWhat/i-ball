package client.fan.view.application_pages;

import shared.res.Stylesheet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The IdolView is the home page of the application where fans can choose an idol to book. It contains:
 * 1. All idols
 */
public class IdolsView extends JPanel {
    /**
     * The search bar.
     */
    private JTextField txtSearchbar;
    /**
     * The search button.
     */
    private JButton btnSearch;
    /**
     * Button to filter all idols.
     */
    private JButton btnFilterAll;
    /**
     * Button to filter gamers.
     */
    private JButton btnFilterGamers;
    /**
     * Button to filter influencers.
     */
    private JButton btnFilterInfls;
    /**
     * Button to filter celebrities.
     */
    private JButton btnFilterCelebs;
    /**
     * Panel holding all the idols.
     */
    private JPanel pnlIdolsContainer;
    /**
     * Array of IdolDetailsPanel.
     */
    private List<IdolDetailsPanel> idolPanels = new ArrayList<>();
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

        add(new HeaderPanel(), BorderLayout.NORTH);

        JPanel container = new JPanel(new BorderLayout());
        container.setBorder(style.padding);
        container.setBackground(style.white);
        add(container, BorderLayout.CENTER);

        container.add(new FilterButtonsPanel(), BorderLayout.NORTH);
        container.add(new IdolsPanel(), BorderLayout.CENTER);

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
         * The scroll pane.
         */
        private JScrollPane scrollPane;
        /**
         * The filter label.
         */
        private JLabel lblFilter;

        /**
         * Constructs a panel of IdolsPanel.
         */
        public IdolsPanel() {
            this.setBackground(style.white);
            this.setBorder(style.padding);
            this.setLayout(new BorderLayout());

            lblFilter = style.createLblH2("All Idols",style.black);
            add(lblFilter, BorderLayout.NORTH);

            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setHgap(50);
            flowLayout.setVgap(30);
            flowLayout.setAlignment(FlowLayout.LEFT);

            pnlIdolsContainer = new JPanel(flowLayout);
            pnlIdolsContainer.setBackground(style.white);
            pnlIdolsContainer.setBorder(style.padding);
            pnlIdolsContainer.setPreferredSize(new Dimension(1000,1550));

            scrollPane = new JScrollPane(pnlIdolsContainer);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            add(scrollPane, BorderLayout.CENTER);

            this.setPreferredSize(new Dimension(1100, 700));
        }
    }

    /**
     * Panel that holds idol type filter buttons.
     */
    class FilterButtonsPanel extends JPanel {

        /**
         * Constructs a panel of FilterButtonsPanel.
         */
        public FilterButtonsPanel() {
            this.setBackground(style.white);
            this.setLayout(new FlowLayout(FlowLayout.CENTER));
            this.setBorder(style.padding);

            btnFilterAll = style.createBtnTxtOnly("ALL", style.purple);
            add(btnFilterAll);

            btnFilterGamers = style.createBtnTxtOnly("GAMERS", style.purple);
            add(btnFilterGamers);

            btnFilterInfls = style.createBtnTxtOnly("INFLUENCERS", style.purple);
            add(btnFilterInfls);

            btnFilterCelebs = style.createBtnTxtOnly("CELEBRITIES", style.purple);
            add(btnFilterCelebs);

            this.setPreferredSize(new Dimension(1100,60));
        }
    }

    /**
     * Holds the details of idol.
     */
    public class IdolDetailsPanel extends JPanel {
        /**
         * The profile picture button.
         */
        private JButton btnPfp;
        /**
         * The URL of the idolPfp.
         */
        private String idolPfp;
        /**
         * Full name of the idol.
         */
        private String idolName;
        /**
         * Type of the idol.
         */
        private String idolType;
        /**
         * Video call rate.
         */
        private String idolVidRate;
        /**
         * Voice call rate.
         */
        private String idolVoiceRate;

        /**
         * Constructs a panel of IdolPanel.
         */
        public IdolDetailsPanel(String idolPfp, String idolName, String idolType, String idolVidRate, String idolVoiceRate) {
            this.setLayout(new BorderLayout());
            this.setBackground(style.iconGray);

            ImageIcon icon = new ImageIcon(idolPfp);
            btnPfp = style.createBtnIconOnly(icon, 355,200);
            btnPfp.setHorizontalAlignment(SwingConstants.CENTER);
            add(btnPfp, BorderLayout.CENTER);

            JPanel pnlDetails = new JPanel(new GridBagLayout());
            pnlDetails.setBackground(style.black);
            pnlDetails.setBorder(style.padding);
            add(pnlDetails, BorderLayout.SOUTH);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.WEST;
            gbc.ipady = 3;

            gbc.gridx = 0;
            gbc.gridy = 0;
            JLabel lblName = style.createLblH3(idolName, style.white);
            pnlDetails.add(lblName, gbc);

            gbc.gridy = 1;
            JLabel lblType = style.createLblH4(idolType, style.white);
            pnlDetails.add(lblType, gbc);

            gbc.gridy = 2;
            JLabel lblVidRate = style.createLblP("Video Call Rate: P" + idolVidRate, style.white);
            pnlDetails.add(lblVidRate, gbc);

            gbc.gridy = 3;
            JLabel lblVoiceRate = style.createLblP("Voice Call Rate: P" + idolVoiceRate, style.white);
            pnlDetails.add(lblVoiceRate, gbc);

            this.setPreferredSize(new Dimension(250,350));
        }

        /**
         * Retrieves the current JButton of btnPfp.
         * @return The current btnPfp.
         */
        public JButton getBtnPfp() {
            return btnPfp;
        }

        /**
         * Retrieves the current idolPfp.
         * @return The current idolPfp.
         */
        public String getIdolPfp() {
            return idolPfp;
        }

        /**
         * Retrieves the current idolName.
         * @return The current idolName.
         */
        public String getIdolName() {
            return idolName;
        }

        /**
         * Retrieves the current idolType.
         * @return The current idolType.
         */
        public String getIdolType() {
            return idolType;
        }

        /**
         * Retrieves the current idolVidRate.
         * @return The current idolVidRate.
         */
        public String getIdolVidRate() {
            return idolVidRate;
        }

        /**
         * Retrieves the current idolVoiceRate.
         * @return The current idolVoiceRate.
         */
        public String getIdolVoiceRate() {
            return idolVoiceRate;
        }

        /**
         * Mutates the current idolPfp.
         * @param idolPfp The new idolPfp.
         */
        public void setIdolPfp(String idolPfp) {
            this.idolPfp = idolPfp;
        }

        /**
         * Mutates the current idolName.
         * @param idolName The new idolName.
         */
        public void setIdolName(String idolName) {
            this.idolName = idolName;
        }

        /**
         * Mutates the current idolType.
         * @param idolType The new idolType.
         */
        public void setIdolType(String idolType) {
            this.idolType = idolType;
        }

        /**
         * Mutates the current idolVidRate.
         * @param idolVidRate The new idolVidRate.
         */
        public void setIdolVidRate(String idolVidRate) {
            this.idolVidRate = idolVidRate;
        }

        /**
         * Mutates the current idolVoiceRate.
         * @param idolVoiceRate The new idolVoiceRate.
         */
        public void setIdolVoiceRate(String idolVoiceRate) {
            this.idolVoiceRate = idolVoiceRate;
        }

        /**
         * Sets a specified action listener to btnPfp.
         * @param actionListener The specified action listener.
         */
        public void setPfpListener(ActionListener actionListener) {
            btnPfp.addActionListener(actionListener);
        }
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
     * Retrieves the current JButton of btnFilterAll.
     * @return The current btnFilterAll.
     */
    public JButton getBtnFilterAll() {
        return btnFilterAll;
    }

    /**
     * Retrieves the current JButton of btnFilterGamers.
     * @return The current btnFilterGamers.
     */
    public JButton getBtnFilterGamers() {
        return btnFilterGamers;
    }

    /**
     * Retrieves the current JButton of btnFilterInfls.
     * @return The current btnFilterInfls.
     */
    public JButton getBtnFilterInfls() {
        return btnFilterInfls;
    }

    /**
     * Retrieves the current JButton of btnFilterCelebs
     * @return The current btnFilterCelebs.
     */
    public JButton getBtnFilterCelebs() {
        return btnFilterCelebs;
    }

    /**
     * Retrieves the current JPanel of btnPnlIdolsContainer
     * @return The current pnlIdolsContainer.
     */
    public JPanel getPnlIdolsContainer() {
        return pnlIdolsContainer;
    }

    /**
     * Retrieves the current IdolDetailsPanel array of idolPanels.
     * @return The current array of idolPanels.
     */
    public List<IdolDetailsPanel> getIdolPanels() {
        return idolPanels;
    }

    /**
     * Adds a new IdolDetailsPanel in the pnlContainer with specified details.
     * @param idolPfp The specified idol pfp file url.
     * @param idolName The specified idol name.
     * @param idolType The specified idol type.
     * @param idolVidRate The specified idol video call rate.
     * @param idolVoiceRate The specified idol voice call rate.
     */
    public void addIdolPanel(String idolPfp, String idolName, String idolType, String idolVidRate, String idolVoiceRate) {
        IdolDetailsPanel idolDetailsPanel = new IdolDetailsPanel(idolPfp, idolName, idolType, idolVidRate, idolVoiceRate);
        pnlIdolsContainer.add(idolDetailsPanel);
        idolPanels.add(idolDetailsPanel);
    }


}
