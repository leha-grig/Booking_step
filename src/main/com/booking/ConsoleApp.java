package com.booking;

import com.booking.DAO.CollectionBookingsDAO;
import com.booking.DAO.FlightsDAO;
import com.booking.DAO.UserDAO;
import com.booking.Exceptions.*;
import com.booking.logger.BookingServiceLogger;
import com.booking.objects.Booking;
import com.booking.objects.User;
import com.booking.services.*;
import com.booking.objects.Flight;
import com.booking.utils.UserInfoFormatChecker;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {

    private final BookingServiceLogger logger = new BookingServiceLogger();


    public final static String valdiateFlightID = "([A-Z]){6}[0-9]{4,8}";
    private final String[] initialOptions = {
            "Log in", "New user registration", "Exit"
    };
    private final String[] options = {
            "Show all flights for 24hours", "Show flight by ID",
            "Flight search and booking", "Cancel reservation",
            "My bookings", "Log out"};

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

    public ConsoleApp() throws FileReadingException {
    }

    public void chooseIniOption() {
        System.out.println("Enter number of command!");
        boolean flag = true;
        User user;
        while (flag) {
            displayChooseItem(initialOptions);
            int choose = checkNumberString();
            switch (choose) {
                case 1:
                    user = userLogin();
                    logger.info(user.getName() + " " + user.getSurname() + "chose login option");
                    mainMenu(user);
                    break;
                case 2:
                    user = userRegistration();
                    logger.info(user.getName() + " " + user.getSurname() + "chose registration option");
                    mainMenu(user);
                    break;
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
        User user = new User(name, surname);
        while (true) {
            System.out.println("Enter the year of your birth");
            year = checkNumberString();
            try {
                logger.info(user.getName() + " " + user.getSurname() + " check new user date of birth information input");
                userInfoFormatChecker.yearChecker(year);
                logger.info(user.getName() + " " + user.getSurname() + " success new user input date of birth");
                break;
            } catch (YearOfBirthFormatException e) {
                logger.error(user.getName() + " " + user.getSurname() + " incorrect user's date of birth input");
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            System.out.println("Enter your login name");
            login = scanner.nextLine();
            try {
                logger.info(user.getName() + " " + user.getSurname() + " check new user login input");
                userInfoFormatChecker.loginChecker(login);
                logger.info(user.getName() + " " + user.getSurname() + " success new user login input");
                String finalLogin = login;
                userController.getAllUsers().forEach(u -> {
                    if (u.getLogin().equals(finalLogin)) {
                        throw new LoginMatchException("User with this login is already exist.");
                    }
                    logger.error("user login is already exist");
                });
                break;
            } catch (LoginFormatException | LoginMatchException e) {
                logger.error(user.getName() + " " + user.getSurname() + " incorrect user's login input");
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            System.out.println("Enter your password");
            password = scanner.nextLine();
            try {
                logger.info(user.getName() + " " + user.getSurname() + " check new user password input");
                userInfoFormatChecker.passwordChecker(password);
                logger.info(user.getName() + " " + user.getSurname() + " success new user password input");
                break;
            } catch (PasswordFormatException e) {
                logger.error(user.getName() + " " + user.getSurname() + " incorrect user's password input");
                System.out.println(e.getMessage());
            }
        }
        try {
            user = userController.createNewUser(name, surname, year, login, password);
            logger.info("new user was created");
        } catch (PasswordFormatException | LoginFormatException | StringValidationException | YearOfBirthFormatException | LoginMatchException e) {
            logger.error("user wasn't created");
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
            logger.info("check user's login/password input");
            user = userController.getUser(login, password);
            logger.info("success user's login/password input");
        } catch (IncorrectLoginPasswordException e) {
            logger.error("incorrect user's login/password input");
            System.out.println(e.getMessage());
        }
        return user;
    }

    private void mainMenu(User user) {
        if (user == null) {return;}
        System.out.println("Enter number of command!");
        boolean flag = true;
        while (flag) {
            displayChooseItem(options);

            int choose = checkNumberString();

            switch (choose) {
                case 1:
                    flightController.showFlightsFor24hours();
                    logger.info(user.getName() + " " + user.getSurname() + " viewed flights for 24 hours");
                    break;
                case 2:
                    showFlightById();
                    logger.info(user.getName() + " " + user.getSurname() + " viewed flight by ID");
                    break;
                case 3:
                    FlightSelectionAndBooking();
                    logger.info(user.getName() + " " + user.getSurname() + " selected flight and made booking");
                    break;
                case 4:
                    bookingRemoving();
                    logger.info(user.getName() + " " + user.getSurname() + " removed booking");
                    break;
                case 5:
                    showUserBookings(user);
                    logger.info(user.getName() + " " + user.getSurname() + " viewed bookings");
                    break;
                case 6:
                    System.out.println("Log out");
                    logger.info(user.getName() + " " + user.getSurname() + " log out from app");
                    flag = false;
                    break;
                default:
                    System.out.println("Enter number from 1 to 6!");
            }
        }
    }

    private void showUserBookings(User user) {
        String name1 = user.getName();
        String surname1 = user.getSurname();
        bookingsController.showSelectedBookings(name1, surname1);
    }

    private void bookingRemoving() {
        System.out.println("Enter reservation number!");
        int number = checkNumberString();
        bookingsController.deleteBookingByID(number);
    }

    private void FlightSelectionAndBooking() {
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
        String idString = checkFlightID();
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
                if (number > 4) throw new LargeBookingException("You can not make reservation for more than 4 persons at once");
                if (number == 0) break;
            } catch (NumberBelowZeroException | LargeBookingException e) {
                System.out.println(e.getMessage());
                continue;
            }
            break;
        }
        return number;
    }

    private String checkFlightID() {
        System.out.println("Enter flight ID");
        String checkStr = scanner.nextLine();
        while (!checkStr.matches((valdiateFlightID))) {
            System.out.println("Enter id in format AAAAAA111111");
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
