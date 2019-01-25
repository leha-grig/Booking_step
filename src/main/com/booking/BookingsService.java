package com.booking;

import java.util.Set;

public interface BookingsService {

//    private BookingsDAO bookingsDAO;
//    Booking createBooking(String flight, String name, String surname){
//
//    };
    boolean deleteBookingByID(int ID);// delegate to DAO
    Set<Booking> showSelectedBookings(String name, String surname);
}
