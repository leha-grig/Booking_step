package com.booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingsService {
   
    private BookingsDAO bookingDAO;

    public Booking createBooking(Flight flight, String name, String surname) {
        int flightSeats = flight.getBookedSits();
        Booking booking = new Booking(flight, name, surname);
        if (flight.getFreeSits() > 0) {
            flight.setBookedSits(flightSeats++);
        } else {
            System.out.println("There are no free seats on this flight");
        }
        bookingDAO.saveBooking(booking);
        return bookingDAO.getBookingByID(booking.getID());
    }

    public boolean deleteBookingByID(int ID) {
        return bookingDAO.deleteBooking(ID);
    }

    public List<Booking> showSelectedBookings(String name, String surname, int ID) {
        List<Booking> bookings = new ArrayList<>();
        for (Booking booking : bookingDAO.getAllBookings()) {
            if (booking.getName().equals(name)
                    && booking.getSurname().equals(surname)
                    && booking.getID() == ID) {
                bookings.add(booking);
            }
        }
        return bookings;
    }

}
