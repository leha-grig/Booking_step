package com.booking;

//TODO проверки корректности файлов и их наличия - список городов, флайтов, букингов - как это сделать?
//TODO продвинутое ДЗ
//TODO добавить в консоль возможность выхода в мэйн меню из любой точки?
//TODO в консоли вынести методы проверки в отдельный класс?
//TODO в консоли вынести эксепшены в отдельный класс?
//TODO "красивая" консоль - без свитчей, с стрим-фильтром и Enum?



public class AppStart {
    public static void main(String[] args) {
        Application console = new Application();
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
      /* Map<String, Flight> flights = new CollectionGenerator().generateNewFlightsCollection(10,10);

    FlightPrintable ttp = new FlightPrintable(flights);
        ttp.print();*/
    }


}
