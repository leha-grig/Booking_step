package com.booking;

import java.util.List;

public interface FlightsService {

//    private FlightsDAO flightsDAO;

    List<Flight> showFlightsFor24hours();
    Flight showFlightByID(String ID); // делегация в ДАО
    List <Flight> showSelectedFlights(String destination, String date, int passangers);
}
