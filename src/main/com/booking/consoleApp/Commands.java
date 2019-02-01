package com.booking.consoleApp;

import java.util.ArrayList;
import java.util.List;

public class Commands {
    private String command;
    private String parametr;
    public Commands(String input){
        String[] s = input.split("\\s+");
        try{
            this.command = all().get(1).text();
        } catch(IllegalArgumentException e){
            this.command = all().get(0).text();
        }
        //parametr???
    }
    public static List<Command> all() {
        return new ArrayList<Command>(){{
            add(new CmdHelp());
            add(new CmdShowAllFlights());
            add(new CmdShowFlightId());
            add(new CmdCreateBooking());
            add(new CmdRemoveReserve());
            add(new CmdExit());
        }};
    }

    @Override
    public String toString() {
        return "Commands{" +
                "command=" + command +
                ", parametr='" + parametr + '\'' +
                '}';
    }
}
