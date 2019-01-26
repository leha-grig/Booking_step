package com.booking;

import java.util.List;
import java.util.Scanner;

public class Console {
// List<Flight> generateFlights; // при первом запуске программы сгенерировать рейсы
    /*{
        FlightsDAO flightsDAO = new FlightsDAO();


    }*/

    private String[] options = {
            "Show all flights for 24hours", "Show flith by ID",
            "Fyight search and booking", "Cancel reservation",
            "My flights", "EXIT"};
    private FlightsDAO dao = new FlightsDAO();
    private FlightsService flightsService = new FlightsService(dao);
    private FlightController flightController = new FlightController(flightsService);
    private BookingsDAO bookingsDAO = new CollectionBookingsDAO();
    private BookingsService bookingsServise = new BookingsService(bookingsDAO);
    private BookingController bookingsController = new BookingController(bookingsServise);

    private Scanner scanner = new Scanner(System.in);

    public void chooseCommand(Scanner sc) {
        //displayChooseItem(options);
        System.out.println("Enter number of command!");
        //int choose;
        outerLoop:
        while (true) {
            displayChooseItem(options);
            int choose;
            String name;
            String surname;
            choose = sc.nextInt();
            switch (choose) {
                case 1:
                    flightController.showFlightsFor24hours();
                    continue outerLoop;
                case 2:
                    System.out.println("Enter ID of flight!");
                    String number = scanner.nextLine();
                    flightController.showFlightByID(number);
                    continue outerLoop;
                case 3:
                    System.out.println("Enter desination");
                    String dest = scanner.nextLine();
                    System.out.println("Enter date");
                    String date = scanner.nextLine();
                    System.out.println("Enter number of people!");
                    String numberOfPeople = scanner.nextLine();
                    List<Flight> list = flightController.showSelectedFlights(dest, date, Integer.parseInt(numberOfPeople));
                    System.out.println(list);
                    System.out.println("If you chose something, enter number of flight or press zero to exit!");
                    int numberOfFlight = scanner.nextInt();
                    while(!(numberOfFlight >= 0 && numberOfFlight <= list.size())){System.out.println("Write number!");
                        numberOfFlight = scanner.nextInt();}
                    if ( numberOfFlight == 0) {
                        System.out.println("You back in main menu!");
                        break;
                    }

                    for (int i = 1; i <= numberOfFlight; i++) {
                        System.out.println("Enter name of the " + i + " pasanjer!");
                        name = scanner.nextLine();
                        System.out.println("Enter surname of the " + i + " pasanjer!");
                        surname = scanner.nextLine();
                        try {
                            Booking booking = bookingsController.createBooking(list.get(numberOfFlight - 1), name, surname, flightController);
                            System.out.println("The booking was created: "+booking);
                        } catch (BookingAlreadyExist bookingAlreadyExist) {
                            System.out.println(bookingAlreadyExist.getMessage());
                        }
                    }
                    continue outerLoop;
                case 4:
                    System.out.println("Enter reservation number!");
                    number = scanner.nextLine();
                    bookingsController.deleteBookingByID(Integer.parseInt(number) - 1);
                    continue outerLoop;
                case 5:
                    System.out.println("Enter your name!");
                    String name1 = scanner.nextLine();
                    System.out.println("Enter your surname!");
                    String surname1 = scanner.nextLine();
                    bookingsController.showSelectedBookings(name1, surname1);
                    continue outerLoop;
                case 6:
                    System.out.println("EXIT");
                    break outerLoop;
                //return;
                default:
                    System.out.println("Enter number from 1 to 7!");
                    //continue;
            }
        }
    }

    private void createReservation() {
        System.out.println("ax");
    }


    private void displayChooseItem(String[] options) {
        int number = 0;
        for (String option : options) {
            System.out.println((number + 1) + ":" + option);
            ++number;
        }
    }
}
