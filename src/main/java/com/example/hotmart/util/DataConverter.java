package com.example.hotmart.util;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataConverter {
    private static final Logger logger = LoggerFactory.getLogger(DataConverter.class);

    public static String formatDate(Date date) {
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return dt.format(date);
    }
}
