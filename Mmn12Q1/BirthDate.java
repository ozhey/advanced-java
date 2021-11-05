import java.util.Calendar;
import java.util.GregorianCalendar;

public class BirthDate {

    private final Calendar calendar;

    // constructor
    public BirthDate(int yearOfBirth, int monthOfBirth, int dayOfBirth) {
        calendar = new GregorianCalendar(yearOfBirth, monthOfBirth, dayOfBirth);
    }

    // return month of birth
    public int getMonth() {
        return calendar.get(Calendar.MONTH);
    }

    // return birth day of the month
    public int getDay() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    // return year of birth
    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    // return String representation of BirthDate object
    @Override
    public String toString() {
        return String.format("birthdate: %d.%d.%d", getDay(), getMonth() + 1, getYear());
    }
}
