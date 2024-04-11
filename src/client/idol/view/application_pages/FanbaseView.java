package client.idol.view.application_pages;

import shared.res.Stylesheet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The FanbaseView contains the bookings of the idol in a given day. It contains:
 * 1. Tabular format of the current bookings.
 * 2. Buttons to join the respective bookings.
 */
public class FanbaseView extends JPanel {

    /**
     * The searchbar to search date.
     */
    private JTextField txtSearchbar;
    /**
     * The search button.
     */
    private JButton btnSearch;
    /**
     * The join button.
     */
    private JButton btnJoin;
    /**
     * The return to today's date button.
     */
    private JButton btnReturn;
    /**
     * The current or searched date.
     */
    /**
     * The stylesheet.
     */
    private Stylesheet style = new Stylesheet();

    private TablePanel tablePanel;

    /**
     * Constructs a panel of FanbaseView.
     */
    public FanbaseView() {
        this.setBackground(style.lightGray);
        this.setLayout(new BorderLayout());

        add(new HeaderPanel(), BorderLayout.NORTH);

        tablePanel = new TablePanel();

        add(tablePanel, BorderLayout.CENTER);

        JPanel pnlButtons = new JPanel(new FlowLayout());
        pnlButtons.setPreferredSize(new Dimension(1100,70));
        pnlButtons.setBackground(style.lightGray);
        add(pnlButtons, BorderLayout.SOUTH);

        btnJoin = style.createBtnRounded("Join Session",style.white, style.purple, 10);
        pnlButtons.add(btnJoin);

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

            txtSearchbar = style.createTxtRounded("Search date (YYYY-MM-DD)",style.lightGray, style.gray, 20);
            add(txtSearchbar);

            btnSearch = style.createBtnIconOnly(style.iconSearch, 25,25);
            add(btnSearch);

            btnReturn = style.createBtnTxtOnly("Return to Today",style.white);
            btnReturn.setEnabled(false);
            add(btnReturn);

            this.setPreferredSize(new Dimension(1100,45));
        }
    }

    /**
     * The TablePanel contains the tabular format of the user's bookings.
     */
    public class TablePanel extends JPanel {
        /**
         * Constructs a panel of TablePanel.
         */

        JLabel lblDate;

        JPanel container;
        JPanel pnlTable;

        /**
         * The table of the fanbase.
         */
        private JTable tblFanbase;
        /**
         * The model of the fanbaseTable.
         */
        private DefaultTableModel tblFanbaseModel;
        public TablePanel() {
            this.setLayout(new BorderLayout());
            this.setBorder(style.padding);

            container = style.createPnlRounded(1000, 700, style.white, style.lightGray);
            container.setLayout(new BorderLayout());
            container.setBorder(style.padding);
            add(container, BorderLayout.CENTER);

            lblDate = style.createLblH1("Date", style.black);
            container.add(lblDate, BorderLayout.NORTH);

            pnlTable = style.createPnlRounded(990,690,style.iconGray, style.white);
            pnlTable.setBorder(style.padding);
            pnlTable.setLayout(new BorderLayout());
            container.add(pnlTable, BorderLayout.CENTER);

            tblFanbaseModel = new DefaultTableModel();
            tblFanbaseModel.addColumn("Time");
            tblFanbaseModel.addColumn("Fan");
            tblFanbaseModel.addColumn("Type");
            tblFanbaseModel.addColumn("Duration");

            tblFanbase = new JTable(tblFanbaseModel);
            tblFanbase.getTableHeader().setResizingAllowed(false);
            tblFanbase.getTableHeader().setReorderingAllowed(false);
            tblFanbase.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
            tblFanbase.setAutoResizeMode(0);
            tblFanbase.setDragEnabled(false);
            tblFanbase.setOpaque(false);
            tblFanbase.setFillsViewportHeight(true);
            tblFanbase.setPreferredSize(new Dimension(1000,1000));

            tblFanbase.getColumnModel().getColumn(0).setPreferredWidth(80);
            tblFanbase.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblFanbase.getColumnModel().getColumn(2).setPreferredWidth(60);
            tblFanbase.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            JScrollPane scrollPane = new JScrollPane(tblFanbase);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            container.add(scrollPane, BorderLayout.CENTER);
        }

        public void setDate(String date) {
            lblDate.setText(date);
            this.revalidate();
        }

        public String getDate() {
            return lblDate.getText();
        }

        public void populateTable(String[][] sessions) {
            tblFanbaseModel.setRowCount(0);
            for (String[] row : sessions) {
                tblFanbaseModel.addRow(row);
            }
        }

        /**
         * Retrieves the current JTable of tblFanbase.
         * @return The current tblFanbase.
         */
        public JTable getTblFanbase() {
            return tblFanbase;
        }


        /**
         * Retrieves the current DefaultTableModel of tblFanbaseModel.
         * @return The current tblFanbaseModel.
         */
        public DefaultTableModel getTblFanbaseModel() {
            return tblFanbaseModel;
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
     * Retrieves the current JButton of btnJoin.
     * @return The current btnJoin.
     */
    public JButton getBtnJoin() {
        return btnJoin;
    }

    /**
     * Retrieves the current JButton of btnReturn.
     * @return The current btnReturn.
     */
    public JButton getBtnReturn() {
        return btnReturn;
    }

    /**
     * Sets a specified action listener for btnJoin.
     * @param actionListener The specified action listener.
     */
    public void setJoinListener(ActionListener actionListener) {
        btnJoin.addActionListener(actionListener);
    }

    /**
     * Sets a specified action listener for btnReturn.
     * @param actionListener The specified action li
     */
    public void setReturnListener(ActionListener actionListener) {
        btnReturn.addActionListener(actionListener);
    }

    public TablePanel getTablePanel() {
        return tablePanel;
    }

    public void setTablePanel(TablePanel tablePanel) {
        this.tablePanel = tablePanel;
    }
}
