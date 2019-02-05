package com.booking.consoleApp;

import com.booking.Exceptions.FileReadingException;
import com.booking.consoleApp.avtorization.Auth;
import com.booking.consoleApp.log.ConsoleLogger;

import java.util.ArrayList;
import java.util.List;

public class Commands {
    private Auth a;
    //private ConsoleLogger log;
    private ConsoleLogger log;
    private String command;
    private String parametr;
    public Commands(String input, Storage storage) throws FileReadingException {
        String[] s = input.split("\\s+");
        try{
            this.command = all(log, storage, a).get(1).text();
        } catch(IllegalArgumentException e){
            this.command = all(log, storage, a).get(0).text();
        }
        //parametr???
    }


    public static List<Command> all(ConsoleLogger log, Storage storage, Auth a) throws FileReadingException {
        return new ArrayList<Command>(){{
            add(new CmdHelp(log, storage, a));
            add(new CmdShowAllFlights(log));
            add(new CmdShowFlightId(log));
            add(new CmdCreateBooking(log));
            add(new CmdRemoveReserve(log));
            add(new CmdAutorize((com.booking.consoleApp.log.Logger) log, storage,a));
            add(new CmdExit((com.booking.consoleApp.log.Logger) log));
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
