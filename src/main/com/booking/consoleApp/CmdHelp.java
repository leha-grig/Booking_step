package com.booking.consoleApp;

public class CmdHelp implements Command {
    @Override
    public void doCommand() {
        System.out.println("Available commands are: ");
        for(Command c: Commands.all()){
            System.out.println(c);
        }
    }

    @Override
    public String text() {
        return "HELP";
    }
}
