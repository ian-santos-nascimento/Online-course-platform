package com.example.hotmart.util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DataConverter {

    public static String formatDate(Date date) {
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return dt.format(date);
    }
}
