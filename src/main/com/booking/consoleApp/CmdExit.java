package com.booking.consoleApp;

import com.booking.consoleApp.log.Logger;


public class CmdExit extends CommandBase implements Command {
    public CmdExit(Logger log, Storage storage) {
        super(log, storage);
    }

    public CmdExit(Logger log) {
        super(log);
    }

    @Override
    public void doCommand() {
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String text() {
        return "EXIT";
    }
    /*@Override
    public boolean isAllowToUnAuth() {
        return true;
    }*/
}
