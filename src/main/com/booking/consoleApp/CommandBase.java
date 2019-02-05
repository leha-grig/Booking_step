package com.booking.consoleApp;

import com.booking.consoleApp.log.ConsoleLogger;

public class CommandBase {
    protected final ConsoleLogger log;
    protected final Storage storage;

    public CommandBase(ConsoleLogger log, Storage storage) {
        this.log = log;
        this.storage = storage;
    }


    public CommandBase(com.booking.consoleApp.log.Logger log, Storage storage) {
this.log = (ConsoleLogger) log;
this.storage=storage;
    }

    public CommandBase(ConsoleLogger log) {
        this.log = log;
        storage = null;
    }

    public CommandBase(com.booking.consoleApp.log.Logger log) {
        this.log= (ConsoleLogger) log;
        storage=null;
    }
}
