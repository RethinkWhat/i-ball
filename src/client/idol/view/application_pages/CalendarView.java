package client.idol.view.application_pages;

import shared.res.Stylesheet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 * The CalendarView contains an overview of the bookings of the idol in a given calendar format.
 */
public class CalendarView extends JPanel {
    /**
     * The next month button.
     */
    private JButton btnNextMonth;
    /**
     * The previous month button.
     */
    private JButton btnPrevMonth;
    /**
     * The month and year label.
     */
    private JLabel lblMonthYear;

    private Stylesheet style = new Stylesheet();

    private CalendarPanel calendarPanel;
    private TablePanel tablePanel;


    /**
     * Constructs a panel of CalendarView.
     */
    public CalendarView() {
        this.setBackground(style.lightGray);
        this.setLayout(new BorderLayout());

        calendarPanel = new CalendarPanel();
        tablePanel = new TablePanel();

        add(new HeaderPanel(), BorderLayout.NORTH);
        add(calendarPanel, BorderLayout.CENTER);
//        add(new TablePanel(), BorderLayout.CENTER);

        this.setSize(1100, 755);
    }

    /**
     * The HeaderPanel.
     */
    class HeaderPanel extends JPanel {

        JButton btnBack;
        JButton btnRefresh;
        /**
         * Constructs a HeaderPanel.
         */
        public HeaderPanel() {
            this.setBackground(style.purple);
            this.setLayout(new BorderLayout());
            this.setPreferredSize(new Dimension(1000, 45));

            btnBack = style.createBtnIconOnly(style.iconBackWhite,30,30);
            btnRefresh = style.createBtnIconOnly(style.iconRefresh,20,20);

            // Add buttons to the panel
            add(btnBack, BorderLayout.WEST);
            add(btnRefresh, BorderLayout.EAST);
        }
    }

    /**
     * Retrieves the current panel of CalendarPanel.
     * @return The current calendarPanel.
     */
    public CalendarPanel getCalendarPanel() {
        return calendarPanel;
    }

    /**
     * The CalendarPanel contains the calendar grid and navigation buttons.
     */
    public class CalendarPanel extends JPanel {

        private final String[] DAY_NAMES = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        private final String[] MONTH_NAMES = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};
        public int currentMonthIndex;
        public int currentYear;

        /**
         * Constructs a CalendarPanel.
         */
        public CalendarPanel() {
            this.setLayout(new BorderLayout());
            this.setBorder(style.padding);

            JPanel container = style.createPnlRounded(1000, 670, style.white, style.lightGray);
            container.setLayout(new GridLayout(0, 7, 5, 5));
            container.setBorder(style.padding);
            add(container, BorderLayout.CENTER);

            JPanel pnlMonthYear = new JPanel(new FlowLayout(FlowLayout.CENTER));
            lblMonthYear = new JLabel();
            btnPrevMonth = new JButton("<"); // change with icons
            btnNextMonth = new JButton(">"); // change with icons
            add(pnlMonthYear, BorderLayout.NORTH);

            // Set current month and year
            LocalDate currentDate = LocalDate.now();
            currentMonthIndex = currentDate.getMonthValue() - 1;
            currentYear = currentDate.getYear();
            updateMonthYearLabel(lblMonthYear);

            pnlMonthYear.add(btnPrevMonth);
            pnlMonthYear.add(lblMonthYear);
            pnlMonthYear.add(btnNextMonth);

            // Create and add weekday labels
            Font dayFont = style.createLblH3(null, null).getFont();
            for (String dayName : DAY_NAMES) {
                JLabel lblDay = style.createLblH3(dayName, style.black);
                lblDay.setHorizontalAlignment(JLabel.CENTER);
                container.add(lblDay);
            }

            // Get the first day of the current month
            LocalDate current = LocalDate.now().withDayOfMonth(1);
            // Calculate the day of the week of the first day
            int offset = current.getDayOfWeek().getValue() % 7;

            // Fill empty spaces before the first day
            for (int i = 0; i < offset; i++) {
                JLabel lblEmpty = new JLabel("", JLabel.CENTER);
                container.add(lblEmpty);
            }

            // Create and add clickable date buttons
            Font dateFont = style.createLblCalendar(null, null).getFont();
            for (int i = 1; i <= current.lengthOfMonth(); i++) {
                JButton btnDate = new JButton(Integer.toString(i));
                btnDate.setFont(dateFont);
                btnDate.setBackground(style.white);
                btnDate.setOpaque(false);
                btnDate.setBorder(BorderFactory.createLineBorder(style.black, 3));
                btnDate.setForeground(style.black);
                container.add(btnDate);
            }
        }

        /**
         * Updates the month and year label.
         * @param lblMY The label to be updated.
         */
        public void updateMonthYearLabel(JLabel lblMY) {
            lblMY.setText(MONTH_NAMES[currentMonthIndex] + " " + currentYear);
            lblMY.setFont(style.createLblCalendar(null, null).getFont());
            lblMY.setForeground(style.black);
        }


        /**
         * Retrieves the next month button.
         * @return The next month button.
         */
        public JButton getBtnNextMonth() {
            return btnNextMonth;
        }

        /**
         * Retrieves the previous month button.
         * @return The previous month button.
         */
        public JButton getBtnPrevMonth() {
            return btnPrevMonth;
        }

        /**
         * Retrieves the month and year label.
         * @return The month and year label.
         */
        public JLabel getLblMonthYear() {
            return lblMonthYear;
        }

        /**
         * Sets an action listener for the next month button.
         * @param actionListener The action listener to be set.
         */
        public void setNextMonthListener(ActionListener actionListener) {
            btnNextMonth.addActionListener(actionListener);
        }

        /**
         * Sets an action listener for the previous month button.
         * @param actionListener The action listener to be set.
         */
        public void setPrevMonthListener(ActionListener actionListener) {
            btnPrevMonth.addActionListener(actionListener);
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

            pnlTable = style.createPnlRounded(1000,700,style.iconGray, style.white);
            pnlTable.setBorder(style.padding);
            pnlTable.setLayout(new BorderLayout());
            container.add(pnlTable, BorderLayout.CENTER);

            tblFanbaseModel = new DefaultTableModel();
            tblFanbaseModel.addColumn("Time");
            tblFanbaseModel.addColumn("Fan");
            tblFanbaseModel.addColumn("Type");

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

        /**
         * Retrieves the current panel of TablePanel.
         * @return The current tablePanel.
         */
        public TablePanel getTablePanel() {
            return tablePanel;
        }

        public void setDate(String date) {
            lblDate.setText(date);
            this.revalidate();
        }

        public void populateTable(String[][] sessions) {
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
}
