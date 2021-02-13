package art.cipher581.commons.util;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {

    public static Timestamp createTimestamp(int day, int month, int year) {
        return createTimestamp(day, month, year, 0, 0);
    }


    public static Timestamp createTimestamp(int day, int month, int year, int hour, int minute) {
        return createTimestamp(day, month, year, hour, minute, 0);
    }


    public static Timestamp createTimestamp(int day, int month, int year, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();

        cal.setLenient(true);

        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);

        return new Timestamp(cal.getTimeInMillis());
    }


    public static Date add(Date date, int value, int field) {
        Calendar cal = Calendar.getInstance();

        cal.setLenient(true);

        cal.setTime(date);

        cal.add(field, value);

        return cal.getTime();
    }


    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }


    public static Date startOfDay(Date date) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        

        return cal.getTime();
    }
}
