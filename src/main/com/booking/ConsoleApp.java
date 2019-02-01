package com.booking;

import com.booking.DAO.CollectionBookingsDAO;
import com.booking.DAO.FlightsDAO;
import com.booking.Exceptions.*;
import com.booking.objects.Booking;
import com.booking.services.BookingController;
import com.booking.services.BookingsService;
import com.booking.objects.Flight;
import com.booking.services.FlightController;
import com.booking.services.FlightsService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {

    public final static String valdiateID = "([A-Z]){2}[0-9]{4,8}";

    private String[] options = {
            "Show all flights for 24hours", "Show flight by ID",
            "Flight search and booking", "Cancel reservation",
            "My bookings", "EXIT"};
    private FlightsDAO dao = new FlightsDAO();
    private FlightsService flightsService = new FlightsService(dao);
    private FlightController flightController = new FlightController(flightsService);
    private CollectionBookingsDAO bookingsDAO = new CollectionBookingsDAO();
    private BookingsService bookingsService = new BookingsService(bookingsDAO, flightsService);
    private BookingController bookingsController = new BookingController(bookingsService);

    private final static Scanner scanner = new Scanner(System.in);

    public void chooseCommand() {
        System.out.println("Enter number of command!");

        outerLoop:
        while (true) {
            displayChooseItem(options);

            int choose = checkNumberString();

            switch (choose) {
                case 1:
                    flightController.showFlightsFor24hours();
                    continue outerLoop;
                case 2:
                    String idString = checkID();
                    flightController.showFlightByID(idString);
                    continue outerLoop;
                case 3:
                    String dest = checkInputString("Enter destination!");
                    String date = checkDate();
                    int numberOfPeople = getCorrectNumber("Enter number of people!");
                    List<Flight> list = flightController.showSelectedFlights(dest, date, numberOfPeople);

                    System.out.println("To proceed booking, please, enter the Num of flight from the list above or press zero to exit!");
                    int numberOfFlight = checkNumberOfFlight(list);

                    if (numberOfFlight == 0) {
                        System.out.println("You are back in the main menu!");
                        break;
                    }

                    for (int i = 1; i <= numberOfPeople; i++) {

                        String name = checkInputString("Enter name of the " + i + " passenger");

                        String surname = checkInputString("Enter surname of the " + i + " passenger");
                        try {
                            Booking booking = bookingsController.createBooking(list.get(numberOfFlight - 1), name, surname);
                            System.out.println("The new booking was created: " + booking);
                        } catch (BookingAlreadyExist bookingAlreadyExist) {
                            System.out.println(bookingAlreadyExist.getMessage());
                        }
                    }
                    continue outerLoop;
                case 4:
                    System.out.println("Enter reservation number!");
                    int number = checkNumberString();

                    bookingsController.deleteBookingByID(number);
                    continue outerLoop;
                case 5:

                    String name1 = checkInputString("Enter your name!");

                    String surname1 = checkInputString("Enter your surname!");
                    bookingsController.showSelectedBookings(name1, surname1);
                    continue outerLoop;
                case 6:
                    System.out.println("EXIT");
                    break outerLoop;

                default:
                    System.out.println("Enter number from 1 to 6!");
                    continue outerLoop;
            }
        }
    }

    private int checkNumberString() {
        String string;
        while (true) {
            string = scanner.nextLine();
            if (string.matches("^.*\\D+.*$") || string.length() == 0) {
                try {
                    throw new IncorrectNumberInStringException("digits are only acceptable in this field!");

                } catch (IncorrectNumberInStringException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            }
            break;
        }
        return Integer.parseInt(string);
    }

    private int checkNumberOfFlight(List l) {
        int number;
        while (true) {
            try {

                number = checkNumberString();
                if (number < 0 || number > l.size()) throw new InputMismatchException("Please enter the correct flight number from the list above");
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println(e.getMessage());
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
                number = checkNumberString();
                if (number < 0) throw new NumberBelowZeroException("The number can not be below zero!");
                if (number > 4) throw new LargeBokingException("You can not make reservation for more than 4 persons at once");
                if (number == 0) break;
            } catch (NumberBelowZeroException | LargeBokingException e) {
                System.out.println(e.getMessage());
                continue;
            }
            break;
        }
        return number;
    }

    private String checkID() {
        System.out.println("Enter flight ID");
        String checkStr = scanner.nextLine();
        while (!checkStr.matches((valdiateID))) {
            System.out.println("Enter id in format AA111111");
            checkStr = scanner.nextLine();
        }
        return checkStr;
    }

    private String checkDate() {
        System.out.println("Enter date in format yyyy-MM-dd");
        boolean flag = true;
        String date = null;
        while (flag) {
            date = scanner.nextLine();
            while (!date.matches("\\d{4}[-./]\\d{2}[-./]\\d{2}")) {
                System.out.println("Enter date in format yyyy-MM-dd");
                date = scanner.nextLine();
            }

            try {
                LocalDate parsedDate = LocalDate.parse(date);
                flag = false;
            } catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
                System.out.println("The date is not allowed. Try again");
            }
        }
        return date;
    }

    private String checkInputString(String question) {
        System.out.println(question);
        String line;
        int sizeName;
        while (true) {
            line = scanner.nextLine().trim();
            try {
                sizeName = line.length();
                if (sizeName == 0) throw new EmptyStringException("This field can bot be empty!");
                if (!line.matches("[\\w+]{1,7}[\\s, -]?[\\w+]{1,7}")) {
                    throw new StringValidationException("The field may contain Latin letters, digits and one '-' or space in between. 15 symbols maximum are allowed");
                }

            } catch (EmptyStringException | StringValidationException e) {
                System.out.println(e.getMessage());
                continue;
            }
            break;
        }
        return line;
    }


    private void displayChooseItem(String[] options) {
        int number = 0;
        for (String option : options) {
            System.out.println((number + 1) + ":" + option);
            ++number;
        }
    }
}
