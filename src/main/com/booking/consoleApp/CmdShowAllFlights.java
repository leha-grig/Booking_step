package com.booking.consoleApp;

import com.booking.flights.Flight;
import com.booking.flights.FlightController;

public class CmdShowAllFlights implements Command {
   private FlightController fc;


    @Override
    public void doCommand() {
        fc.showFlightsFor24hours();

    }

    @Override
    public String text() {
        return "SHOW ALL FLIGHTS";
    }
}
