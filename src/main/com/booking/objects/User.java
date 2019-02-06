package com.booking.objects;

import com.booking.interfaces.Identifiable;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable, Identifiable <String> {
    private String id;
    private String name;
    private String surname;
    private int yearOfBirth;
    private String login;
    private String password;
    private Autentification who;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.who = Autentification.UNKNOWN;
    }
    public User() {
        this.who = Autentification.UNKNOWN;
    }

    public User(String name, String surname, int yearOfBirth, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
        this.login = login;
        this.password = password;
        this.id = login;
        this.who = Autentification.USER;
    }

    public String id() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Autentification getWho() {
        return who;
    }

    public enum Autentification {
        USER, ADMIN, UNKNOWN
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return yearOfBirth == user.yearOfBirth &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname);
    }

    @Override
    public int hashCode() {
        return Math.abs(Objects.hash(name, surname, yearOfBirth));
    }

    @Override
    public String toString() {
        String str = String.format("%-15s%-15s%-7d%-22s", getName(), getSurname(), getYearOfBirth(), getLogin());
        return str;
    }
}
