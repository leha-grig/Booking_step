package com.booking;

import com.booking.bookings.BookingController;
import com.booking.bookings.BookingsService;
import com.booking.bookings.CollectionBookingsDAO;
import com.booking.Exceptions.BookingAlreadyExist;
import com.booking.flights.Flight;
import com.booking.flights.FlightController;
import com.booking.flights.FlightsDAO;
import com.booking.flights.FlightsService;

//TODO проверки корректности файлов и их наличия - список городов, флайтов, букингов - как это сделать?
//TODO продвинутое ДЗ
//TODO добавить в консоль возможность выхода в мэйн меню из любой точки?
//TODO в консоли вынести методы проверки в отдельный класс?
//TODO в консоли вынести эксепшены в отдельный класс?
//TODO "красивая" консоль - без свитчей, с стрим-фильтром и Enum?
//TODO переделать HashMap для Bookings - так, чтоб ключами стояли АйДи букингс? Идея в оптимизации поиска.



public class Main {
    public static void main(String[] args) {
        Console console = new Console();
        console.chooseCommand();

   /*     FlightsDAO dao = new FlightsDAO();
        FlightsService flightsService = new FlightsService(dao);
        FlightController flightController = new FlightController(flightsService);

        CollectionBookingsDAO newBookings = new CollectionBookingsDAO();
        BookingsService bookingsService = new BookingsService(newBookings);
        BookingController bookingController = new BookingController(bookingsService);*/

//        flightController.showFlightsFor24hours();
/*        Flight f1 = flightController.showSelectedFlights("Lima", "2019-01-30", 1).get(0);
        Flight f2 = flightController.showSelectedFlights("Athens", "2019-01-30", 1).get(0);

        flightController.showFlightByID("KL130915");

        try {
            bookingController.createBooking(f1, "Alex", "Smith", flightController);
        } catch (BookingAlreadyExist e) {
            System.out.println(e.getMessage());
        }
        try {
            bookingController.createBooking(f2, "Alex", "Smith", flightController);
        } catch (BookingAlreadyExist e) {
            System.out.println(e.getMessage());
        }
        flightController.showFlightByID("KL130915");
        try {
            bookingController.createBooking(f1, "Oleg", "Biller", flightController);
        } catch (BookingAlreadyExist e) {
            System.out.println(e.getMessage());
        }*/
        // 1954320473
       /* flightController.showFlightByID("KL130915");

        System.out.println(newBookings.getAll());
        bookingController.showSelectedBookings("Alex", "Smith");
        bookingController.deleteBookingByID(1954320473, flightController);
        flightController.showFlightByID("KL130915");
        System.out.println(newBookings.getAll());
        bookingController.showSelectedBookings("Alex", "Smith");*/
    }


}
