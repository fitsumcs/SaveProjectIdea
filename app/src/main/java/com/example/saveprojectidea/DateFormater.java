package com.example.saveprojectidea;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateFormater {



    public   String getFormatedDate()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy");
        Calendar calendar = Calendar.getInstance();
        String today = simpleDateFormat.format(calendar.getTime());
        return today;
    }


}
