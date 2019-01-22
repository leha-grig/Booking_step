package com.booking;

import java.util.Set;

public class BookingController {
    private BookingsService bookingsService;

    Booking createBooking(Flight flight, String name, String surname) {
        return bookingsService.createBooking(flight, name, surname);
    }


    boolean deleteBookingByID(int ID) {
        return bookingsService.deleteBookingByID(ID);
    }

    Set<Booking> showSelectedBookings(String name, String surname) {
        return bookingsService.showSelectedBookings(name, surname);
    }


}
