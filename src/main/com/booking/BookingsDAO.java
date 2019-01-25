package com.booking;
import java.util.List;


public interface BookingsDAO {

    List<Booking> getAllBookings();

    Booking getBookingByIndex(int index);

    Booking getBookingByID(int ID);

    boolean deleteBooking(Booking booking);

    boolean deleteBooking(int ID);

    void saveBooking(Booking booking);



}
