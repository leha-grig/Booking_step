package com.booking.consoleApp;

import com.booking.DAO.FlightsDAO;
import com.booking.services.FlightController;
import com.booking.services.FlightsService;

public class CmdShowAllFlights implements Command {
   private final FlightsService fs = new FlightsService(new FlightsDAO());
   private final FlightController fc = new FlightController(fs);

   @Override
    public void doCommand() {
        fc.showFlightsFor24hours();

    }

    @Override
    public String text() {
        return "SHOW ALL FLIGHTS";
    }
}
