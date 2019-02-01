package com.booking.consoleApp;

import com.booking.Exceptions.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CheckingMethods {
    Scanner scanner = new Scanner(System.in);
    public final static String valdiateID = "([A-Z]){2}[0-9]{4,8}";
    public int checkNumberString() {
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

    public int checkNumberOfFlight(List l) {
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

    public int getCorrectNumber(String s) {
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

    public String checkID() {
        System.out.println("Enter flight ID");
        String checkStr = scanner.nextLine();
        while (!checkStr.matches((valdiateID))) {
            System.out.println("Enter id in format AA111111");
            checkStr = scanner.nextLine();
        }
        return checkStr;
    }

    public String checkDate() {
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

    public String checkInputString(String question) {
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
}
