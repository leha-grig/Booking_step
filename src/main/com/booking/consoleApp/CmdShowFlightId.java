package com.booking.consoleApp;


import com.booking.DAO.FlightsDAO;
import com.booking.Exceptions.FileReadingException;
import com.booking.consoleApp.log.ConsoleLogger;
import com.booking.controller.FlightController;
import com.booking.services.FlightsService;


public class CmdShowFlightId extends CommandBase implements Command {
    CheckingMethods cm =new CheckingMethods();
    private final FlightsService fs = new FlightsService(new FlightsDAO());
    private final FlightController fc = new FlightController(fs);

    public CmdShowFlightId(ConsoleLogger log) throws FileReadingException {
        super(log);
    }

    @Override
    public void doCommand() {
        String idString = cm.checkID();
        fc.showFlightByID(idString);
    }

    @Override
    public String text() {
        return "SHOW FLIGHT BY ID";
    }
}
