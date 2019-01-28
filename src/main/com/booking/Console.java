package com.booking;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Console {
    // List<Flight> generateFlights; // при первом запуске программы сгенерировать рейсы
    /*{
        FlightsDAO flightsDAO = new FlightsDAO();


    }*/
    public final static String valdiateID = "^([A-Z]){2}[0-9]{6,7}";

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

    private final static Scanner scanner = new Scanner(System.in);

    public void chooseCommand() {
        System.out.println("Enter number of command!");

        outerLoop:
        while (true) {
            displayChooseItem(options);
            int choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    flightController.showFlightsFor24hours();
                    continue outerLoop;
                case 2:
                    String number = scheckID(false, true);
                    flightController.showFlightByID(number);
                    continue outerLoop;
                case 3:
                    String dest = checkInputString("Enter desination!");
                    System.out.println("Enter date!");
                    String date = scanner.nextLine();
                    int numberOfPeople = getCorrectNumber("Enter number of people!");
                    List<Flight> list = flightController.showSelectedFlights(dest, date, numberOfPeople);
                    System.out.println(list);
                    System.out.println("If you chose something, enter number of flight or press zero to exit!");
                    int numberOfFlight = checkNumberOfFligth(list);
                    while (!(numberOfFlight >= 0 && numberOfFlight <= list.size())) {
                        System.out.println("Write number!");
                        numberOfFlight = scanner.nextInt();
                    }
                    if (numberOfFlight == 0) {
                        System.out.println("You back in main menu!");
                        break;
                    }

                    for (int i = 1; i <= numberOfPeople; i++) {

                        String name = checkInputString("Enter name of the " + i + " pasanjer!");

                        String surname = checkInputString("Enter surname of the " + i + " pasanjer!");
                        try {
                            Booking booking = bookingsController.createBooking(list.get(numberOfFlight - 1), name, surname, flightController);
                            System.out.println("The booking was created: " + booking);
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

                    String name1 = checkInputString("Enter your name!");

                    String surname1 = checkInputString("Enter your surname!");
                    bookingsController.showSelectedBookings(name1, surname1);
                    continue outerLoop;
                case 6:
                    System.out.println("EXIT");
                    break outerLoop;
                //return;
                default:
                    System.out.println("Enter number from 1 to 6!");
                    continue outerLoop;
            }
        }
    }

    private int checkNumberOfFligth(List l) {
        int number;
        while (true) {
            try {
                number = scanner.nextInt();
                if (number < 0 || number > l.size()) throw new InputMismatchException("I did not find flight with such index!");
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
                continue;
            }
            break;
        }
        return number;
    }

    private int getCorrectNumber(String s) {
        System.out.println(s);
        int number;
        while (true) {
            try {
                number = scanner.nextInt();
                if (number < 0 || number > 10) throw new BookingAlreadyExist("Booking is full!");
                if( number == 0 ) break;
            } catch (BookingAlreadyExist e) {
                System.out.println("Booking does not have so many sits!");
                System.out.println(e.getMessage());
                scanner.nextLine();
                continue;
            }
            break;
        }
        return number;
    }

    private String scheckID(boolean id, boolean messageFlage) {
        if (messageFlage) System.out.println(!id ? "Enter correct id" : "Enter id format QAS23456");
        String checkStr = scanner.nextLine();
        while (!checkStr.matches((valdiateID))) {
            System.out.println("Enter once more!");
            checkStr = scanner.nextLine();
        }
        return checkStr;
    }

    private  String checkInputString(String question) {
            System.out.println(question);
            String line;
            int sizeName;
            while (true) {
                line = scanner.nextLine().trim().toLowerCase();
                try {
                    sizeName = line.length();
                    if (sizeName == 0) throw new NullPointerException("Can not be null!");
                    for (int i = 0; i < sizeName; i++) {
                        if (Character.isDigit(line.charAt(i))) throw new NumberFormatException("Can not be number!");
                    }
                } catch (NullPointerException | NumberFormatException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                break;
            }
            return line.substring(0,1).toUpperCase()+line.substring(1);
        }


    private void displayChooseItem(String[] options) {
        int number = 0;
        for (String option : options) {
            System.out.println((number + 1) + ":" + option);
            ++number;
        }
    }

}
//сделать проверку даты
//убрать баг
//choose проверить