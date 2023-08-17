package com.gotracrat.managelocation.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


public class DateUtils {

    private static LocalDate currentdate = LocalDate.now();

    public static String getDateAsString(Date anyDate) {
        if (anyDate != null) {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ssZ");
            String dateAddedOn = dateFormat.format(anyDate);
            return dateAddedOn;
        } else {
            return null;
        }
    }

    public static String getDateAsStringFormat(Date anyDate) {
        if (anyDate != null) {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String dateAddedOn = dateFormat.format(anyDate);
            return dateAddedOn;
        } else {
            return null;
        }
    }

    public static Date getStringasDate(String anyStringDate) throws ParseException {
        if (anyStringDate != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd'T'HH:mm:ss.'000Z'");
            Date anyDate = dateFormat.parse(anyStringDate);
            return anyDate;
        } else {
            return null;
        }
    }


    public static String getLastYearDateFromToday() {
        LocalDate date = currentdate.minusYears(1);
        String lasyOneYearDate = date.toString();
        return lasyOneYearDate;
    }

    public static String getLastTwoYearDateFromToday() {
        LocalDate date = currentdate.minusYears(2);
        String lastTwoYearDate = date.toString();
        return lastTwoYearDate;
    }


    public static String getTodayDate() {
        String today = currentdate.toString();
        return today;
    }

    public static String getLastThirdMonthFromToday() {
        LocalDate currentdate = LocalDate.now();
        return currentdate.minusMonths(3).toString();
    }

    public static String getLastMonth() {
        return currentdate.minusMonths(1).toString();
    }
}
