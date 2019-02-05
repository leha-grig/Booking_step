package com.booking.consoleApp;

import com.booking.consoleApp.avtorization.Auth;
import com.booking.consoleApp.log.Logger;

public class CmdAutorize extends CommandBase implements Command {
    public CmdAutorize(Logger log, Storage storage, Auth a) {
        super(log, storage);
    }

    @Override
    public void doCommand() {

    }

    @Override
    public String text() {
        return null;
    }

   /* @Override
    public boolean isAllowToUnAuth() {
        return true;
    }*/
}
