package com.booking;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Flight flight = new Flight(LocalDateTime.now(), "Kiev", "Lvov", 70);
        System.out.println(flight.getTime());
        System.out.println(flight.getId());

        FlightsDAO dao = new FlightsDAO();
        System.out.println(dao.getFlights());
        System.out.println(dao.getFlightByID("KA1241615"));
    }
}
