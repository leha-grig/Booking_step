package com.booking.utils;

import com.booking.Exceptions.LoginFormatException;
import com.booking.Exceptions.PasswordFormatException;
import com.booking.Exceptions.StringValidationException;
import com.booking.Exceptions.YearOfBirthFormatException;

import java.time.LocalDateTime;

public class UserInfoFormatChecker {

    public void nameChecker (String name) throws StringValidationException {
        if (!name.matches("[\\w+]{1,7}[\\s, -]?[\\w+]{1,7}")) {
            throw new StringValidationException("The name may contain Latin letters, digits and one '-' or space in between. 15 symbols maximum are allowed");
        }
    }
    public void loginChecker (String password) throws LoginFormatException {
        if (!password.matches("[\\w-_.]{3,20}")){
            throw new LoginFormatException("Incorrect login format. Login must have from 3 to 20 symbols and may contain letters, digits, '.', '-' and '_' ");

        }
    }
    public void passwordChecker (String password) throws PasswordFormatException {
        if (!password.matches("[\\S]{5,20}")){
            throw new PasswordFormatException("Incorrect password format. Password must have from 5 to 20 symbols and must not contain spaces");

        }
    }
    public void yearChecker (int year) throws YearOfBirthFormatException {
        int currentYear = LocalDateTime.now().getYear();
        if (currentYear-year < 0 || currentYear-year > (currentYear-1900)){
            throw new YearOfBirthFormatException("Incorrect year of birth. It must be in format 'yyyy', and must be in range 1900 - " + currentYear);

        }
    }
}
