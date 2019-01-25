package com.booking;



import java.util.Set;

public interface BookingsService {

//    private BookingsDAO bookingsDAO;
    Booking createBooking(Flight flight, String name, String surname);
    boolean c(int ID);// delegate to DAO
    Set<Booking> showSelectedBookings(String name, String surname);
    boolean deleteBookingByID(int index);
}
