package client.idol.controller.application_pages;

import client.idol.model.application_pages.CalendarModel;
import client.idol.view.application_pages.CalendarView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The CalendarController processes the user requests. Based on the user request, the CalendarController
 * defines methods and invokes methods in the View and Model to accomplish the requested action.
 */
public class CalendarController {

    private CalendarModel model;
    private CalendarView view;

    public CalendarController(CalendarView view, CalendarModel model) {
        this.view = view;
        this.model = model;

        // Add action listeners to the view components
        this.view.getCalendarPanel().setNextMonthListener(new NextMonthListener());
        this.view.getCalendarPanel().setPrevMonthListener(new PrevMonthListener());

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
        }
    }

}
