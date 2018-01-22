package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * class to handle anyhting to do with system time in the game
 *
 * @author Graham
 *
 */
public class Time {

    private static long startTime;
    
    /**
     * returns a formatted date stamp with the year, month, day and time to the
     * second
     *
     * @return the current date and time, formatted yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentTimeStamp() {
        //creates a date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //gets the current date
        Date now = new Date();
        //formats the date using the formatter and stores it in a string
        String date = dateFormat.format(now);
        //returns the string
        return date;
    }
    
    public static boolean hasBeenTime(long startTime, double desiredTimeSeconds){
        if(getCurrentTime() - startTime >= desiredTimeSeconds * 1000000000){
            return true;
        }
        return false;
    }

    public static long getStartTimeNano() {
        return startTime;
    }

    public static void setStartTimeNano(long startTime) {
        Time.startTime = startTime;
    }
    
    public static long getCurrentTime(){
        return System.nanoTime();
    }
}
