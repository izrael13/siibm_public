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
    
    public static String addZerosToTime(String time) {
        if (time.length() < 1) {
            return "00:00:00";
        } else if (time.length() < 2) {
            return "00:0" + time + ":00";
        } else if (time.length() < 3) {
            return "00:" + time + ":00";
        } else if (time.length() < 4) {
            return "0" + time.substring(0,1) +":" + time.substring(1,3) + ":00";
        }  if (time.length() < 5) {
            return time.substring(0,2) +":" + time.substring(2,4) + ":00";
        }  else {
            return "00:00:00";
        }
    }
    
    public static Date getDateCalendar(String fecha) {
        fecha = fecha.replace("  ", " ").replace("   ", " ");
        String[] splitDate = fecha.split(" ");
        int month = getMonth(splitDate[0]) -1;
        int day = Integer.parseInt(splitDate[1]);
        int year = Integer.parseInt(splitDate[2]);
        int hour = getHour(splitDate[3]);
        int min = getMin(splitDate[3]);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, min);
        Date result = calendar.getTime();        
        return result;
    }
    
    public static Date getDateCalendar(String fecha, String hourPar) {
        fecha = fecha.replace("  ", " ").replace("   ", " ");
        String[] splitDate = fecha.split(" ");
        int month = getMonth(splitDate[0]) -1;
        int day = Integer.parseInt(splitDate[1]);
        int year = Integer.parseInt(splitDate[2]);
        int hour = getHourInt(hourPar);
        int min = getMinInt(hourPar);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, min);
        Date result = calendar.getTime();        
        return result;
    }
    
    public static int getHourInt(String hourPar) {
        if (hourPar.length() == 1) {
            return 0;
        } else if (hourPar.length() == 2) {
            return 0;
        } else if (hourPar.length() == 3) {
            String hour = hourPar.substring(0,1);
            return Integer.parseInt(hour);
        } else {
            String hour = hourPar.substring(0,2);
            return Integer.parseInt(hour);
        }
    }
    
    public static int getMinInt(String hourPar) {
        String minutos = "0";
        if (hourPar.length() == 1) {
            minutos = hourPar;
        } else if (hourPar.length() == 2) {
            minutos = hourPar;
        } else if (hourPar.length() == 3) {
            minutos = hourPar.substring(1,3);
        } else if (hourPar.length() == 4) {
            minutos = hourPar.substring(2,4);
        }
        return Integer.parseInt(minutos);
    }
    
    public static int getHour(String time) {
        String[] splitTime = time.split(":");
        int result = Integer.parseInt(splitTime[0]);
        if (splitTime[1].toLowerCase().contains("am")) {
             if (result == 12) {
                 result = 0;
             }
        } else {
            if (result == 1) {
                result = 13;
            }
            if (result == 2) {
                result = 14;
            }
            if (result == 3) {
                result = 15;
            }
            if (result == 4) {
                result = 16;
            }
            if (result == 5) {
                result = 17;
            }
            if (result == 6) {
                result = 18;
            }
            if (result == 7) {
                result = 19;
            }
            if (result == 8) {
                result = 20;
            }
            if (result == 9) {
                result = 21;
            }
            if (result == 10) {
                result = 22;
            }
            if (result == 11) {
                result = 23;
            }
        }
        return result;
    }
    public static int getMin(String time) {
        String[] splitTime = time.split(":");
        return Integer.parseInt(splitTime[1].replace("AM","").replace("PM", ""));
    }
    
    public static int getMonth(String month) {
        if (month.equalsIgnoreCase("ene")) {
            return 1;
        } else if (month.equalsIgnoreCase("feb")) {
            return 2;
        } else if (month.equalsIgnoreCase("mar")) {
            return 3;
        } else if (month.equalsIgnoreCase("abr")) {
            return 4;
        } else if (month.equalsIgnoreCase("may")) {
            return 5;
        } else if (month.equalsIgnoreCase("jun")) {
            return 6;
        } else if (month.equalsIgnoreCase("jul")) {
            return 7;
        } else if (month.equalsIgnoreCase("ago")) {
            return 8;
        } else if (month.equalsIgnoreCase("sep")) {
            return 9;
        }else if (month.equalsIgnoreCase("oct")) {
            return 10;
        }else if (month.equalsIgnoreCase("nov")) {
            return 11;
        }else if (month.equalsIgnoreCase("dic")) {
            return 12;
        } else {
            return 1;
        }
    }
}
