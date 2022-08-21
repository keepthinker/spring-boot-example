package com.keepthinker.spring.springbootexample.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class DateUtils {
    public static Date parse(String str, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(format);
        DateTime dateTime = dateTimeFormatter.parseDateTime(str);
        return dateTime.toDate();
    }

    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(format);
    }
}
