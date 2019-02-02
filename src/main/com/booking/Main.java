package com.booking;

//TODO продвинутое ДЗ
//TODO добавить в консоль возможность выхода в мэйн меню из любой точки?
//TODO в консоли вынести методы проверки в отдельный класс?
//TODO "красивая" консоль - без свитчей, с стрим-фильтром и Enum?
//TODO переделать HashMap для Bookings - так, чтоб ключами стояли АйДи букингс? Идея в оптимизации поиска.


import com.booking.DAO.UserDAO;
import com.booking.Exceptions.*;
import com.booking.services.UserController;
import com.booking.services.UserService;

public class Main {
    public static void main(String[] args) {

        /*UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        UserController userController = new UserController(userService);

            try {
                userController.createNewUser("Alex", "Grig", 1978, "alex-grig", "qwerty");
                userController.createNewUser("Mike", "Smith", 2017, "mike_smith", "qwerty");
                userController.createNewUser("Max", "Fry", 1980, "max_fry", "qwerty");

            } catch (PasswordFormatException | LoginFormatException | StringValidationException | UserMatchException | LoginMatchException | YearOfBirthFormatException e) {
                System.out.println(e.getMessage());
            }*/


        ConsoleApp console = new ConsoleApp();
        console.chooseIniOption();

        /*FlightsDAO dao = new FlightsDAO();
        FlightsService flightsService = new FlightsService(dao);
        FlightController flightController = new FlightController(flightsService);

        CollectionBookingsDAO newBookings = new CollectionBookingsDAO();
        BookingsService bookingsService = new BookingsService(newBookings, flightsService);
        BookingController bookingController = new BookingController(bookingsService);

//        flightController.showFlightsFor24hours();
        Flight f1 = flightController.showSelectedFlights("Lima", "2019-01-30", 1).get(0);
        Flight f2 = flightController.showSelectedFlights("Athens", "2019-01-30", 1).get(0);

        flightController.showFlightByID("KL130915");

        try {
            bookingController.createBooking(f1, "Alex", "Smith");
        } catch (BookingAlreadyExist e) {
            System.out.println(e.getMessage());
        }
        try {
            bookingController.createBooking(f2, "Alex", "Smith");
        } catch (BookingAlreadyExist e) {
            System.out.println(e.getMessage());
        }
        flightController.showFlightByID("KL130915");
        try {
            bookingController.createBooking(f1, "Oleg", "Biller");
        } catch (BookingAlreadyExist e) {
            System.out.println(e.getMessage());
        }
        // 1954320473
        flightController.showFlightByID("KL130915");

        System.out.println(newBookings.getAll());
        bookingController.showSelectedBookings("Alex", "Smith");
        bookingController.deleteBookingByID(1954320473);
        flightController.showFlightByID("KL130915");
        System.out.println(newBookings.getAll());
        bookingController.showSelectedBookings("Alex", "Smith");*/
    }


}
