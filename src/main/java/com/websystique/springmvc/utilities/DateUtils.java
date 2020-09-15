package com.websystique.springmvc.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static String addDayToDate(String originalDate, String dateFormat, int addDay) {
        String resultingDate = "";
        SimpleDateFormat sDateFormat = new SimpleDateFormat(dateFormat);
        Date dateValue = null;
        try {
            dateValue = sDateFormat.parse(originalDate);
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
        Calendar c = Calendar.getInstance();
        c.setTime(dateValue);
        c.add(Calendar.DATE, addDay);
        dateValue.setTime( c.getTime().getTime() );
        resultingDate = sDateFormat.format(dateValue);
        return resultingDate;
    }
    
}
