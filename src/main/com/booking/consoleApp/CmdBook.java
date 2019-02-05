package com.booking.consoleApp;

import com.booking.interfaces.Logger;

public class CmdBook extends CommandBase implements Command{
    public CmdBook(Logger log, Storage storage) {
        super((com.booking.consoleApp.log.Logger) log, storage);
    }

    @Override
    public String text() {
        return "BOOK";
    }

    @Override
    public void doCommand() {
        System.out.println("Booking...");
    }
}
