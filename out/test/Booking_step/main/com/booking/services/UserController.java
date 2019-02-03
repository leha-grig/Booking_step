package com.booking.services;

import com.booking.Exceptions.*;
import com.booking.objects.User;

import java.util.List;

public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User createNewUser (String name, String surname, int yearOfBirth, String login, String password) throws LoginMatchException, UserMatchException, PasswordFormatException, LoginFormatException, StringValidationException, YearOfBirthFormatException {

        return userService.createNewUser(name, surname, yearOfBirth, login, password);
    }

    public User getUser (String login, String password) throws IncorrectLoginPasswordException {

        return userService.getUser(login, password);
    }

    public List<User> showAllUsers () {

        return userService.showAllUsers();
    }
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    public void deleteUser(String id) {
        userService.deleteUser(id);
    }

    public void deleteUser(User user) {
        userService.deleteUser(user);
    }
}
