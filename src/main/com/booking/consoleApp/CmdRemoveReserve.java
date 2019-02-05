package com.booking.consoleApp;

import com.booking.DAO.CollectionBookingsDAO;
import com.booking.DAO.FlightsDAO;
import com.booking.Exceptions.FileReadingException;
import com.booking.consoleApp.log.ConsoleLogger;
import com.booking.controller.BookingController;
import com.booking.services.BookingsService;
import com.booking.services.FlightsService;

public class CmdRemoveReserve extends CommandBase implements Command {
    CheckingMethods cm =new CheckingMethods();
    private final FlightsService fs = new FlightsService(new FlightsDAO());
    private CollectionBookingsDAO bookingsDAO = new CollectionBookingsDAO();
    private BookingsService bookingsService = new BookingsService(bookingsDAO, fs);
    private BookingController bc = new BookingController(bookingsService);

    public CmdRemoveReserve(ConsoleLogger log, Storage storage) throws FileReadingException {
        super(log, storage);
    }

    public CmdRemoveReserve(ConsoleLogger log) throws FileReadingException {
        super(log);
    }

    @Override
    public void doCommand() {
        System.out.println("Enter reservation number!");
        int number = cm.checkNumberString();
        bc.deleteBookingByID(number);
    }

    @Override
    public String text() {
        return "REMOVE RESERVATION";
    }
}
