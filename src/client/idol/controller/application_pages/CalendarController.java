package client.idol.controller.application_pages;

import client.idol.model.application_pages.CalendarModel;
import client.idol.view.application_pages.CalendarView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

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
        this.view.getCalendarPanel().setNextMonthListener(new NextMonthListener());
        this.view.getCalendarPanel().setPrevMonthListener(new PrevMonthListener());

        updateCalendar();
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
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("reached");
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
