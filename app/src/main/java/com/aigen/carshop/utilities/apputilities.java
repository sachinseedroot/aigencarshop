package com.aigen.carshop.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class apputilities {

    public static Date getDateFromString(String date) { //2019-09-05 04:27:11.0
        Date dateTime;
        try {
            String format = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat format2 = new SimpleDateFormat(format, Locale.ENGLISH);
            dateTime = format2.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            dateTime = null;
        }
        return dateTime;
    }

}
