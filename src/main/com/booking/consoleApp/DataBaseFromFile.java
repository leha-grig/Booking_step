package com.booking.consoleApp;

import com.booking.DAO.CollectionBookingsDAO;
import com.booking.DAO.FlightsDAO;
import com.booking.objects.Booking;
import com.booking.objects.Flight;

import java.util.List;
import java.util.Map;


public class DataBaseFromFile implements DataBase {
    private Map<String, Flight> flights;
    private Map<Flight, List<Booking>> bookings;

    public DataBaseFromFile(FlightsDAO flights, CollectionBookingsDAO bookings) {
        this(flights.getFlights(), bookings.getBookings());

    }
    public DataBaseFromFile(Map<String, Flight> flights, Map<Flight, List<Booking>> bookings) {
        this.flights = flights;
        this.bookings = bookings;
    }


    @Override
    public List<Flight> items() {
        return null;
    }

}
