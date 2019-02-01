package com.booking.consoleApp;

interface Command {
    void doCommand();

    default boolean isExit() {
        return false;
    }

    String text();
}
