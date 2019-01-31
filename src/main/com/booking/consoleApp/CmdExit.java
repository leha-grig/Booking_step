package com.booking.consoleApp;

public class CmdExit implements Command {
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
}
