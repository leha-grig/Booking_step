package com.booking.services;

import com.booking.DAO.UserDAO;
import com.booking.Exceptions.*;
import com.booking.objects.User;
import com.booking.utils.UserInfoFormatChecker;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private UserDAO userDAO;
    private UserInfoFormatChecker userInfoFormatChecker = new UserInfoFormatChecker();

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User createNewUser(String name, String surname, int yearOfBirth, String login, String password) throws LoginMatchException, PasswordFormatException, LoginFormatException, StringValidationException, YearOfBirthFormatException, UserMatchException {

        userInfoFormatChecker.nameChecker(name);
        userInfoFormatChecker.nameChecker(surname);
        userInfoFormatChecker.loginChecker(login);
        userInfoFormatChecker.passwordChecker(password);
        userInfoFormatChecker.yearChecker(yearOfBirth);

        userDAO.getAll().forEach(user -> {
            if (user.getName().equals(name) && user.getSurname().equals(surname) && user.getYearOfBirth() == yearOfBirth) {
                try {
                    throw new UserMatchException("This user is already exist");
                } catch (UserMatchException e) {
                    e.printStackTrace();
                }
            }
        });

        loginMatchChecker(login);

        User user = new User(name, surname, yearOfBirth, login, password);
        userDAO.save(user);
        return user;
    }

    private void loginMatchChecker(String login) throws LoginMatchException {
        userDAO.getAll().forEach(user -> {
            if (user.getLogin().equals(login)) {
                try {
                    throw new LoginMatchException("User with this login is already exist.");
                } catch (LoginMatchException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    public User getUser(String login, String password) throws IncorrectLoginPasswordException {
        User user = userDAO.getById(login);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        throw new IncorrectLoginPasswordException("The user login or password is incorrect");

    }

    public void showUserFull(String login) {
        User user = userDAO.getById(login);
        if (user == null) {
            return;
        }
        System.out.println(user + "password: " + user.getPassword());
    }

    public List<User> showAllUsers() {
        System.out.println(String.format("%-15s%-15s%-7s%-22s", "User name", "User surname", "Birth", "User login"));
        userDAO.getAll().forEach(System.out::println);
        return new ArrayList<>(userDAO.getAll());
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userDAO.getAll());
    }

    public void deleteUser(String id) {
        userDAO.remove(id);
    }

    public void deleteUser(User user) {
        userDAO.remove(user);
    }
}
