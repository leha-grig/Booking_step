package com.booking.consoleApp;

import com.booking.bookings.BookingController;
import com.booking.flights.FlightController;

public class CmdCreateBooking implements Command {
    private BookingController bc;



    @Override
    public void doCommand() {
        //bc.createBooking();

    }
    @Override
    public String text() {
        return "SEARCH FLIGHT AND BOOKING";
    }
}
