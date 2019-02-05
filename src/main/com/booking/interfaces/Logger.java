package com.booking.interfaces;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public interface Logger {
    String logFile = "./booking_app.log";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm ");

    void info(String msg);

    //private static String logFile = "./booking_app.log";
    //private final static DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm ");

    void error(String msg);
}
