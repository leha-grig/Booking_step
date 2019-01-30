package com.booking.bookings;
import com.booking.Exceptions.BookingAlreadyExist;
import com.booking.flights.Flight;
import com.booking.flights.FlightController;

import java.util.List;

public class BookingController {

    private BookingsService bookingsService;

    public BookingController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    public Booking createBooking(Flight flight, String name, String surname)throws BookingAlreadyExist {
        return bookingsService.createBooking(flight, name, surname);
    }

    public void deleteBookingByID(int ID) {
        bookingsService.deleteBookingByID(ID);
    }

    public List<Booking> showSelectedBookings(String name, String surname) {
        return bookingsService.showSelectedBookings(name, surname);
    }

}