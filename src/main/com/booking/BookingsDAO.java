package com.booking;
import com.booking.Flights.Flight;

import java.util.List;
import java.util.Map;


public interface BookingsDAO {

    Map<Flight, List<Booking>> getAllBookings();

    Booking getBookingByID(int ID);

    boolean deleteBooking(Booking booking);

    boolean deleteBooking(int ID);

    public abstract void saveBooking(Booking booking);



}
