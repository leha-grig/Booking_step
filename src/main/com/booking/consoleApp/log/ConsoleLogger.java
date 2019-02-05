package com.booking.consoleApp.log;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsoleLogger implements Logger {
    String logFile = "/main/com/booking/consoleApp/log/booking_app.log";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm ");
    @Override
    public void log(Object... s) {
        logAndError("[DEBUG]", s);
        /*Stream.of(s).forEach(System.out::print);
        System.out.println();*/
    }

    @Override
    public void error(Object... s) {
        logAndError("[ERROR]", s);

    }

    private void logAndError(String s2, Object[] s) {
        Date date = new Date();
        String format = df.format(date);
        try {
            FileWriter fileWriter = new FileWriter(logFile, true);
            fileWriter.write(new StringBuilder().append(format).append(s2).append(s).append(System.getProperty("line.separator")).toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
                e.printStackTrace();
            }
    }


}
