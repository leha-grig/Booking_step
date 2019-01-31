package com.booking.consoleApp;

import com.booking.flights.FlightController;

public class CmdShowFlightId implements Command {
    private FlightController fc;
    private String parametr;

    @Override
    public void doCommand() {
        fc.showFlightByID(parametr);

    }

    @Override
    public String text() {
        return "SHOW FLIGHT BY ID";
    }
}
