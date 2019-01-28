package com.booking;

import com.booking.bookings.BookingsService;
import com.booking.bookings.CollectionBookingsDAO;
import com.booking.Exceptions.BookingAlreadyExist;
import com.booking.flights.Flight;
import com.booking.flights.FlightController;
import com.booking.flights.FlightsDAO;
import com.booking.flights.FlightsService;

//TODO проверки корректности файлов и их наличия - список городов, флайтов, букингов
//TODO продвинутое ДЗ

//TODO добавить в консоль возможность выхода в мэйн меню из любой точки?

//TODO проверить remove в букингДАО!!!


public class Main {
    public static void main(String[] args) {
        Console console = new Console();
        console.chooseCommand();
        /*FlightsDAO dao = new FlightsDAO();
        FlightsService flightsService = new FlightsService(dao);
        FlightController flightController = new FlightController(flightsService);

        flightController.showFlightsFor24hours();
        Flight f1 = flightController.showSelectedFlights("Lima", "2019-01-29", 1).get(0);
        Flight f2 = flightController.showSelectedFlights("Athens", "2019-01-29", 1).get(0);


        flightsService.showFlightByID("KA1271215");
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
        flightsService.showFlightByID("KA1271215");


        System.out.println(newBookings.getAll());
        bookingsService.showSelectedBookings("Alex", "Smith");
      */
    }


}
