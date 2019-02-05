package com.booking.consoleApp;

import com.booking.Exceptions.FileReadingException;

interface Command {
    void doCommand() throws FileReadingException;

    default boolean isExit() {
        return false;
    }

    String text();
}
