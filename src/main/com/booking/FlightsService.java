package com.booking;

import java.util.List;
import java.util.Map;

public interface FlightsService {

//    private FlightsDAO flightsDAO;

    Map <String, Flight> showFlightsFor24hours();
    Flight showFlightByID(String ID); // делегация в ДАО
    Map <String, Flight> showSelectedFlights(String destination, String date, int passangers);
}
