package com.booking;

//TODO продвинутое ДЗ
//TODO добавить в консоль возможность выхода в мэйн меню из любой точки?
//TODO в консоли вынести методы проверки в отдельный класс?
//TODO "красивая" консоль - без свитчей, с стрим-фильтром и Enum?
//TODO переделать HashMap для Bookings - так, чтоб ключами стояли АйДи букингс? Идея в оптимизации поиска.


public class Main {
    public static void main(String[] args) {

        ConsoleApp console = new ConsoleApp();
        console.chooseCommand();

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
