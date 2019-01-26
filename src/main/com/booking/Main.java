package com.booking;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        FlightsDAO dao = new FlightsDAO();
        FlightsService flightsService = new FlightsService(dao);
        FlightController flightController = new FlightController(flightsService);

//        flightController.showFlightsFor24hours();
        Flight f1 = flightController.showSelectedFlights("Lima", "2019-01-27", 1).get(0);
        Flight f2 = flightController.showSelectedFlights("Athens", "2019-01-27", 1).get(0);



        flightsService.showFlightByID("KA127015");
        CollectionBookingsDAO newBookings = new CollectionBookingsDAO();
        BookingsService bookingsService = new BookingsService(newBookings);
        try {
            bookingsService.createBooking(f1, "Alex", "Smith", flightController);
        } catch (BookingAlreadyExist e) {
            System.out.println(e.getMessage());
        }
        try {
            bookingsService.createBooking(f2, "Alex", "Smith", flightController);
        } catch (BookingAlreadyExist e) {
            System.out.println(e.getMessage());
        }
        flightsService.showFlightByID("KA127015");


        System.out.println(newBookings.getAllBookings());
        bookingsService.showSelectedBookings("Alex", "Smith");
        Scanner scanner = new Scanner(System.in);
        Console console = new Console();
        console.chooseCommand(scanner);
    }
}
