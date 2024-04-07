package client.idol.model.application_pages;

import shared.res.Idol;
import java.time.LocalDate;
import java.time.YearMonth;

/**
 * The CalendarModel provides methods for displaying the bookings of the idol.
 */
public class CalendarModel {

    private Idol idol;
    public CalendarModel(Idol idol) {
        this.idol = idol;
    }

    /**
     * Generate sample calendar data for the specified month and year.
     */
    public LocalDate[][] generateCalendarData(int year, int month) {
        // Get the number of days in the specified month
        int daysInMonth = YearMonth.of(year, month).lengthOfMonth();

        // Get the first day of the month
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);

        // Calculate the day of the week of the first day
        int startDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue();

        // Calculate the number of weeks needed to display all the days
        int weeks = (int) Math.ceil((double) (daysInMonth + startDayOfWeek) / 7);

        // Create a 2D array to store the calendar data
        LocalDate[][] data = new LocalDate[weeks][7];

        // Initialize LocalDate object to represent the first day of the month
        LocalDate currentDate = firstDayOfMonth.minusDays(startDayOfWeek - 1);

        // Fill the array with LocalDate objects representing each day in the month
        for (int i = 0; i < weeks; i++) {
            for (int j = 0; j < 7; j++) {
                // Populate the array if it's within the month's range
                if (currentDate.getMonthValue() == month) {
                    data[i][j] = currentDate;
                } else {
                    data[i][j] = null;
                }
                currentDate = currentDate.plusDays(1);
            }
        }
        return data;
    }

}
