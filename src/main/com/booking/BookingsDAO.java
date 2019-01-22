package com.booking;

import java.util.List;
import java.util.Set;

public interface BookingsDAO {

    Set<Booking> getAllBookings();
    Booking getBookingByIndex(int index);
    Booking getBookingByID(int ID);
    boolean deleteBooking(Booking booking);
    boolean deleteBooking(int ID);
    void saveBooking(Booking booking);

}
