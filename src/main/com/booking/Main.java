package com.booking;

import java.time.Duration;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Flight f1 = new Flight(LocalDateTime.now(), "Kiev", "Lvov", 70);
        Flight f2 = new Flight(LocalDateTime.now().plusMinutes(15), "Kiev", "Lvov", 70);
        System.out.println((int) Duration.between(f2.getDateTime(), f1.getDateTime()).getSeconds());

        FlightsDAO dao = new FlightsDAO();
        FlightsService flightsService = new FlightsService(dao);

        flightsService.showFlightsFor24hours();
        flightsService.showSelectedFlights("Lima", "2019/01.25", 3);
        flightsService.showFlightByID("KB1261330");
        CollectionBookingsDAO newBookings = new CollectionBookingsDAO();
        BookingsService bookingsService = new BookingsService(newBookings);
        Booking newBooking = new Booking(f1, "Alex", "Smith");
        newBookings.saveBooking(newBooking);

        System.out.println(newBooking);
//        System.out.println(newBookings.getAllBookings());
        bookingsService.showSelectedBookings("Alex", "Smith");
    }
}
