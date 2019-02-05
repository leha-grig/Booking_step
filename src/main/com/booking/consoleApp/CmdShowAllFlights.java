package com.booking.consoleApp;

import com.booking.DAO.FlightsDAO;
import com.booking.Exceptions.FileReadingException;
import com.booking.consoleApp.log.ConsoleLogger;
import com.booking.controller.FlightController;
import com.booking.services.FlightsService;

public class CmdShowAllFlights extends CommandBase implements Command {
   private FlightsService fs;

    {
        try {
            fs = new FlightsService(new FlightsDAO());
        } catch (FileReadingException e) {
            e.printStackTrace();
        }
    }

    private final FlightController fc = new FlightController(fs);

    public CmdShowAllFlights(ConsoleLogger log) {
        super(log);
    }

    @Override
    public void doCommand() {
        fc.showFlightsFor24hours();
   }

    @Override
    public String text() {
        return "SHOW ALL FLIGHTS";
    }
}
