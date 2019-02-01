package com.booking.consoleApp;

import com.booking.DAO.CollectionBookingsDAO;
import com.booking.DAO.FlightsDAO;
import com.booking.services.BookingController;
import com.booking.services.BookingsService;
import com.booking.services.FlightsService;

public class CmdRemoveReserve implements Command {
    CheckingMethods cm =new CheckingMethods();
    private final FlightsService fs = new FlightsService(new FlightsDAO());
    private CollectionBookingsDAO bookingsDAO = new CollectionBookingsDAO();
    private BookingsService bookingsService = new BookingsService(bookingsDAO, fs);
    private BookingController bc = new BookingController(bookingsService);

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
