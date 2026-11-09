import java.util.Calendar;

/**
 * Adapter: implements NewDateInterface and delegates all work to a
 * java.util.Calendar instance (the adaptee).
 */
public class CalendarToNewDateAdapter implements NewDateInterface {

    private final Calendar calendar;

    /** Creates an adapter initialised to today's date. */
    public CalendarToNewDateAdapter() {
        this(Calendar.getInstance());
    }

    /** Creates an adapter that wraps an existing Calendar. */
    public CalendarToNewDateAdapter(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public void setDay(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Day must be between 1 and 31: " + day);
        }
        calendar.set(Calendar.DAY_OF_MONTH, day);
    }

    @Override
    public void setMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12: " + month);
        }
        // Calendar months are 0-based (JANUARY = 0), the new interface uses 1-12.
        calendar.set(Calendar.MONTH, month - 1);
    }

    @Override
    public void setYear(int year) {
        calendar.set(Calendar.YEAR, year);
    }

    @Override
    public int getDay() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public int getMonth() {
        return calendar.get(Calendar.MONTH) + 1;
    }

    @Override
    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    @Override
    public void advanceDays(int days) {
        // Calendar.add handles month/year rollover and leap years.
        calendar.add(Calendar.DAY_OF_MONTH, days);
    }

    @Override
    public String toString() {
        return String.format("%02d.%02d.%04d", getDay(), getMonth(), getYear());
    }
}
