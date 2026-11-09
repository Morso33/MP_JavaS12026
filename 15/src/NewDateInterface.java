/**
 * Target interface that the client code uses.
 * Months are 1-based (1 = January ... 12 = December).
 */
public interface NewDateInterface {
    void setDay(int day);

    void setMonth(int month);

    void setYear(int year);

    int getDay();

    int getMonth();

    int getYear();

    /**
     * Advances the date by the given number of days (negative values move backwards).
     */
    void advanceDays(int days);
}
