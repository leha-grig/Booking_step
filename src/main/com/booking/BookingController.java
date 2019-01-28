package com.booking;
import com.booking.Exceptions.BookingAlreadyExist;
import com.booking.Flights.Flight;
import com.booking.Flights.FlightController;

import java.util.List;

public class BookingController {

    private BookingsService bookingsService;

    public BookingController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    public Booking createBooking(Flight flight, String name, String surname, FlightController flightController)throws BookingAlreadyExist {
        return bookingsService.createBooking(flight, name, surname, flightController);
    }

    public boolean deleteBookingByID(int ID) {
        return bookingsService.deleteBookingByID(ID);
    }

    public List<Booking> showSelectedBookings(String name, String surname) {
        return bookingsService.showSelectedBookings(name, surname);
    }

}