package com.mbds.nfc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by macbook on 12/04/16.
 */
public class DateUtils {

    public static Date getDateFromString(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm");
        try {

            Date date = formatter.parse(dateInString);
            return date;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
