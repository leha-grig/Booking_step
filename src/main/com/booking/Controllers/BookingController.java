package com.booking.Controllers;
import com.booking.Exceptions.BookingAlreadyExist;
import com.booking.Exceptions.NoBookingException;
import com.booking.objects.Booking;
import com.booking.objects.Flight;
import com.booking.services.BookingsService;

import java.util.List;

public class BookingController {

    private BookingsService bookingsService;

    public BookingController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    public Booking createBooking(Flight flight, String name, String surname) {
        return bookingsService.createBooking(flight, name, surname);
    }

    public void deleteBookingByID(int ID) throws NoBookingException {
        bookingsService.deleteBookingByID(ID);
    }

    public List<Booking> showSelectedBookings(String name, String surname) {
        return bookingsService.showSelectedBookings(name, surname);
    }

}