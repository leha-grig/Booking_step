package com.booking.flights;

import java.util.List;

public class FlightController {
    private FlightsService flightsService;

    public FlightController(FlightsService flightsService) {
        this.flightsService = flightsService;
    }

    public List<Flight> showFlightsFor24hours(){
        return flightsService.showFlightsFor24hours();
    }

    public Flight showFlightByID(String id){
        return flightsService.showFlightByID(id);
    }
    public List<Flight> showSelectedFlights(String destination, String date, int passangers){
        return flightsService.showSelectedFlights(destination, date, passangers);
    }
    public void saveFlight(Flight flight){
        flightsService.saveFlight(flight);
    }

}
