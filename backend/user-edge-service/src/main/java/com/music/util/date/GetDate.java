package com.music.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetDate {

    public static Date getCurrentDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String format = dateFormat.format(new Date());
        try {
           return dateFormat.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
