package com.booking;

import java.util.List;

public interface FlightsDAO {
    List<Flight> getAllFlights();
    Flight getFlightByIndex(int index);
    Flight getFlightByID(String ID);
    /*boolean deleteFlight(Booking booking);
    boolean deleteFlight(int index);
    void saveFlight(Booking booking);*/
}
