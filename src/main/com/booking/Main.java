package com.booking;

import com.booking.Exceptions.BookingAlreadyExist;
import com.booking.Flights.Flight;
import com.booking.Flights.FlightController;
import com.booking.Flights.FlightsDAO;
import com.booking.Flights.FlightsService;

//
//TODO разбить проект по пакетам
//TODO сделать ДАО интерфейс генерифицированный, имплементить для букингов
//TODO продвинутое ДЗ
//TODO тесты


public class Main {
    public static void main(String[] args) {

        FlightsDAO dao = new FlightsDAO();
        FlightsService flightsService = new FlightsService(dao);
        FlightController flightController = new FlightController(flightsService);

//        flightController.showFlightsFor24hours();
        Flight f1 = flightController.showSelectedFlights("Lima", "2019-01-27", 1).get(0);
        Flight f2 = flightController.showSelectedFlights("Athens", "2019-01-27", 1).get(0);


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


        System.out.println(newBookings.getAllBookings());
        bookingsService.showSelectedBookings("Alex", "Smith");
    }


}
