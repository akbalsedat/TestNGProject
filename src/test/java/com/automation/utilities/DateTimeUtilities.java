package com.automation.utilities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeUtilities {

    /** this method returns current date as a string
     * Provide format as a parameter
     *MM like: 01,02,03
     * MMM like: Jan, Feb, Mar
     * dd like: 01,02, 03
     * yyyy like: 2010, 2011
     * @param format for example MMM dd, yyyy: March 29, 2020
     * @return
     */
    public static String getCurrentDate(String format){
        return LocalDate.now().format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * This method with return between end and start time
     * @param start
     * @param end
     * @param format
     * @return
     */
    public static long getTimeDifference(String start, String end, String format){
        LocalTime startTime = LocalTime.parse(start, DateTimeFormatter.ofPattern(format));
        LocalTime endTime = LocalTime.parse(end, DateTimeFormatter.ofPattern(format));
        return ChronoUnit.HOURS.between(startTime, endTime);
    }
}
