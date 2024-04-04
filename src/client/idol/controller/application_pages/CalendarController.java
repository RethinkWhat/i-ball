package client.idol.controller.application_pages;

import client.idol.model.application_pages.CalendarModel;
import client.idol.view.application_pages.CalendarView;

/**
 * The CalendarController processes the user requests. Based on the user request, the CalendarController
 * defines methods and invokes methods in the View and Model to accomplish the requested action.
 */
public class CalendarController {

    CalendarModel model;
    CalendarView view;

    public CalendarController(CalendarView view, CalendarModel model) {
        this.view = view;
        this.model = model;
    }
}
