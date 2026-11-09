/**
 * Client code: works only with NewDateInterface and knows nothing about Calendar.
 */
public class Main {

    public static void main(String[] args) {
        NewDateInterface date = new CalendarToNewDateAdapter();

        // Set year and month before the day so that e.g. day 31 is applied to the right month.
        date.setYear(2026);
        date.setMonth(11);
        date.setDay(9);
        printDate("Start date", date);

        date.advanceDays(10);
        printDate("After advancing 10 days", date);

        date.advanceDays(45);
        printDate("After advancing 45 more days (crosses into a new year)", date);

        // Leap year check: 28.02.2028 + 1 day = 29.02.2028
        date.setYear(2028);
        date.setMonth(2);
        date.setDay(28);
        printDate("New date set", date);

        date.advanceDays(1);
        printDate("After advancing 1 day (leap year)", date);

        date.advanceDays(365);
        printDate("After advancing 365 days", date);

        date.advanceDays(-7);
        printDate("After going back 7 days", date);
    }

    private static void printDate(String label, NewDateInterface date) {
        System.out.printf("%-55s %02d.%02d.%04d%n",
                label + ":", date.getDay(), date.getMonth(), date.getYear());
    }
}
