package com.booking;

import java.util.List;

public class FlightController {
    private FlightsService flightsService;

    public FlightController(FlightsService flightsService) {
        this.flightsService = flightsService;
    }
    List<Flight> showFlightsFor24hours(){
        return flightsService.showFlightsFor24hours();
    }
    Flight showFlightById(String id){
        return flightsService.showFlightByID(id);
    }
    List<Flight> showSelectedFlights(String destination, String date, int passangers){
        return flightsService.showSelectedFlights(destination, date, passangers);
    }
}
