package com.booking.consoleApp;

import com.booking.bookings.BookingController;
import com.booking.flights.FlightController;

public class CmdRemoveReserve implements Command {
    private BookingController bc;
    private int parametr;
    private FlightController flightController;

    @Override
    public void doCommand() {
        bc.deleteBookingByID(parametr);

    }

    @Override
    public String text() {
        return "REMOVE RESERVATION";
    }
}
