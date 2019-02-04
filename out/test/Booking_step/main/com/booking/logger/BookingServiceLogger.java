package com.booking.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class BookingServiceLogger implements Logger {

    @Override
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

    @Override
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
