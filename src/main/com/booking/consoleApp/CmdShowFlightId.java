package com.booking.consoleApp;


import com.booking.DAO.FlightsDAO;
import com.booking.services.FlightController;
import com.booking.services.FlightsService;


public class CmdShowFlightId implements Command {
    CheckingMethods cm =new CheckingMethods();
    private final FlightsService fs = new FlightsService(new FlightsDAO());
    private final FlightController fc = new FlightController(fs);

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
