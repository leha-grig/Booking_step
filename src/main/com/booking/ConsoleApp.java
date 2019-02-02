package com.booking;

import com.booking.DAO.CollectionBookingsDAO;
import com.booking.DAO.FlightsDAO;
import com.booking.DAO.UserDAO;
import com.booking.Exceptions.*;
import com.booking.objects.Booking;
import com.booking.objects.Flight;
import com.booking.objects.User;
import com.booking.services.*;
import com.booking.utils.UserInfoFormatChecker;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {

    public final static String valdiateID = "([A-Z]){2}[0-9]{4,8}";
    private final String[] initialOptions = {
            "Log in", "New user registration", "Exit"
    };

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
    private UserDAO userDAO = new UserDAO();
    private UserService userService = new UserService(userDAO);
    private UserController userController = new UserController(userService);
    private UserInfoFormatChecker userInfoFormatChecker = new UserInfoFormatChecker();

    private final static Scanner scanner = new Scanner(System.in);

    public void chooseIniOption() {
        System.out.println("Enter number of command!");
        boolean flag = true;
        User user = null;
        while (flag) {
            displayChooseItem(initialOptions);
            int choose = checkNumberString();
            switch (choose) {
                case 1:
                    user = userLogin();
                    chooseCommand(user);
                    continue;
                case 2:
                    user = userRegistration();
                    chooseCommand(user);
                    continue;
                case 3:
                    System.out.println("EXIT");
                    flag = false;
                    break;
                default:
                    System.out.println("Enter number of command from 1 to 3!");
            }
        }
    }

    private User userRegistration() {
        String name = checkInputString("Enter your name");
        String surname = checkInputString("Enter your surname");
        int year;
        String login;
        String password;
        User user = null;
        year = getUserYear();
        login = getUserLoginName();
        return getUserPassword(name, surname, year, login, user);
    }

    private int getUserYear() {
        int year;
        while (true) {
            System.out.println("Enter the year of your birth");
            year = checkNumberString();
            try {
                userInfoFormatChecker.yearChecker(year);
                break;
            } catch (YearOfBirthFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        return year;
    }

    private String getUserLoginName() {
        String login;
        while (true) {
            System.out.println("Enter your login name");
            login = scanner.nextLine();
            try {
                userInfoFormatChecker.loginChecker(login);
                String finalLogin = login;
                userController.getAllUsers().forEach(u -> {
                    if (u.getLogin().equals(finalLogin)) {
                        try {
                            throw new LoginMatchException("User with this login is already exist.");
                        } catch (LoginMatchException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            } catch (LoginFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        return login;
    }

    private User getUserPassword(String name, String surname, int year, String login, User user) {
        String password;
        while (true) {
            System.out.println("Enter your password");
            password = scanner.nextLine();
            try {
                userInfoFormatChecker.passwordChecker(password);
                break;
            } catch (PasswordFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            user = userController.createNewUser(name, surname, year, login, password);
        } catch (PasswordFormatException | LoginFormatException | StringValidationException | YearOfBirthFormatException | LoginMatchException | UserMatchException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    private User userLogin() {
        System.out.println("Enter your login");
        String login = scanner.nextLine();
        System.out.println("Enter your password");
        String password = scanner.nextLine();
        User user = null;
        try {
            user = userController.getUser(login, password);
        } catch (IncorrectLoginPasswordException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public void chooseCommand(User user) {
        if (user == null) {
            return;
        }
        System.out.println("Enter number of command!");
       boolean flag = true;
        while (flag) {
            displayChooseItem(options);

            int choose = checkNumberString();

            switch (choose) {
                case 1:
                    flightController.showFlightsFor24hours();
                    continue;
                case 2:
                    showFlightById();
                    continue;
                case 3:
                    showFlightsAndCreateBooking();
                    break;
                case 4:
                    deleteBookingById();
                    continue;
                case 5:
                    showSelectedBookings();
                    continue;
                case 6:
                    System.out.println("EXIT");
                    break;
                default:
                    System.out.println("Enter number from 1 to 6!");
            }
        }
    }

    private void showSelectedBookings() {
        String name1 = checkInputString("Enter your name!");
        String surname1 = checkInputString("Enter your surname!");
        bookingsController.showSelectedBookings(name1, surname1);
    }

    private void deleteBookingById() {
        System.out.println("Enter reservation number!");
        int number = checkNumberString();
        bookingsController.deleteBookingByID(number);
    }

    private void showFlightsAndCreateBooking() {
        String dest = checkInputString("Enter destination!");
        String date = checkDate();
        int numberOfPeople = getCorrectNumber("Enter number of people!");
        List<Flight> list = flightController.showSelectedFlights(dest, date, numberOfPeople);
        System.out.println("To proceed booking, please, enter the Num of flight from the list above or press zero to exit!");
        int numberOfFlight = checkNumberOfFlight(list);

        if (numberOfFlight == 0) {
            System.out.println("You are back in the main menu!");
            return;
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
    }

    private void showFlightById() {
        String idString = checkID();
        flightController.showFlightByID(idString);
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
                if (number < 0 || number > l.size())
                    throw new InputMismatchException("Please enter the correct flight number from the list above");
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
                if (number > 4)
                    throw new LargeBokingException("You can not make reservation for more than 4 persons at once");
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
