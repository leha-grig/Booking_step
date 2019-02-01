package com.booking.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingServiceLogger {

    private static String logFile = "./booking_app.log";
    private final static DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm ");

    public void info(String msg) {
        try {
            Date now = new Date();
            String currentTime = BookingServiceLogger.df.format(now);
            FileWriter fileWriter = new FileWriter(logFile, true);
            fileWriter.write(currentTime + " [DEBUG] " + msg
                    + System.getProperty("line.separator"));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void error(String msg) {
        try {
            Date now = new Date();
            String currentTime = BookingServiceLogger.df.format(now);
            FileWriter fileWriter = new FileWriter(logFile, true);
            fileWriter.write(currentTime + " [ERROR] " + msg
                    + System.getProperty("line.separator"));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
