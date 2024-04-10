package client.idol.controller.application_pages;

import client.idol.model.application_pages.CalendarModel;
import client.idol.view.application_pages.CalendarView;
import shared.res.Session;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * The CalendarController processes the user requests. Based on the user request, the CalendarController
 * defines methods and invokes methods in the View and Model to accomplish the requested action.
 */
public class CalendarController {
    /**
     * The model
     */
    private CalendarModel model;
    /**
     * The view.
     */
    private CalendarView view;

    /**
     * Constructs a CalendarController with a specified view and model.
     * @param view The specified view.
     * @param model The specified model.
     */
    public CalendarController(CalendarView view, CalendarModel model) {
        this.view = view;
        this.model = model;

        // Add action listeners to the view components
        view.getCalendarPanel().setNextMonthListener(new NextMonthListener());
        view.getCalendarPanel().setPrevMonthListener(new PrevMonthListener());
        view.getPnlHeader().setBackListener(e -> view.getCardLayout().show(view.getPnlCards(), "calendar"));
        view.getPnlHeader().setRefreshListener(new RefreshListener());

        updateCalendar();

        for (JButton button : view.getButtons()) {
            button.addActionListener(new DateListener(button));
        }
    }

    /**
     * ActionListener for the Next Month button.
     */
    class NextMonthListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Increment the month index
            view.getCalendarPanel().currentMonthIndex++;
            // Check if we need to increment the year
            if (view.getCalendarPanel().currentMonthIndex > 11) {
                view.getCalendarPanel().currentMonthIndex = 0;
                view.getCalendarPanel().currentYear++;
            }
            updateCalendar();
        }
    }

    /**
     * ActionListener for the Previous Month button.
     */
    class PrevMonthListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Decrement the month index
            view.getCalendarPanel().currentMonthIndex--;
            // Check if we need to decrement the year
            if (view.getCalendarPanel().currentMonthIndex < 0) {
                view.getCalendarPanel().currentMonthIndex = 11;
                view.getCalendarPanel().currentYear--;
            }
            updateCalendar();
        }
    }

    class DateListener implements ActionListener {
        private String date;
        public DateListener(JButton button) {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            int currDay = parseInt(button.getText());
            int currMonth = view.getCalendarPanel().getCurrentMonthIndex() + 1;
            int currYear = view.getCalendarPanel().getCurrentYear();

            LocalDate currDate = LocalDate.of(currYear, currMonth, currDay);
            date = currDate.format(dateFormat);

            String txtButton = button.getText();
            int selectedDay = Integer.parseInt(txtButton);

            LocalDate selectedDate = LocalDate.of(view.getCalendarPanel().currentYear, view.getCalendarPanel().currentMonthIndex + 1, selectedDay);
            date = selectedDate.toString();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            view.getTablePanel().getTblFanbaseModel().setRowCount(0);
            model.getIdolSession(date);

            for (Session session : model.getSessions()) {
                view.getTablePanel().getTblFanbaseModel().addRow(new Object[]{
                        session.getStartTime(), session.getDuration(), session.getFanName(),
                        session.getSessionType(), session.getAmount()});
            }

            view.getTablePanel().setDate(date);
            view.getCardLayout().show(view.getPnlCards(), "table");
        }
    }

    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    /**
     * Update the calendar grid based on the current month and year.
     */
    private void updateCalendar() {
        // Update the month and year label in the view
        view.getCalendarPanel().updateMonthYearLabel(view.getCalendarPanel().getLblMonthYear());

        // Get the current month and year from the view
        int currentMonth = view.getCalendarPanel().currentMonthIndex + 1;
        int currentYear = view.getCalendarPanel().currentYear;

        // Calendar Data
        LocalDate[][] calendarData = model.generateCalendarData(currentYear, currentMonth);

        // Updating the calendar grid
        view.updateCalendarGrid(calendarData);
    }
}
