package com.booking.consoleApp;

import com.booking.Exceptions.FileReadingException;
import com.booking.consoleApp.avtorization.Auth;

public class CmdHelp extends CommandBase implements Command {
    private Auth a;



    public CmdHelp(com.booking.consoleApp.log.Logger log, Storage storage, Auth a) {
        super(log, storage);
        this.a=a;
    }

    @Override
    public void doCommand() throws FileReadingException {
        System.out.println("Available commands are: ");
        for(Command c: Commands.all(log, storage, a)){
            System.out.println(c.text());
        }
    }

    @Override
    public String text() {
        return "HELP";
    }
}
