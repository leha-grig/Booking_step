package com.booking.bookings;

import com.booking.flights.Flight;
import com.booking.Identifiable;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Booking implements Serializable, Identifiable<Integer> {

    private Flight flight;
    private LocalDate date;
    private String name;
    private String surname;
    private int ID;


    public Booking(Flight flight, String name, String surname) {
        this.flight = flight;
        this.date = flight.getDate();
        this.name = name;
        this.surname = surname;
        this.ID = hashCode();
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer id() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {

        String str = String.format("%-12s%-15s%-15s%s%n", ID, name, surname, flight);
        
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return ID == booking.ID &&
                Objects.equals(flight, booking.flight) &&
                Objects.equals(date, booking.date) &&
                Objects.equals(name, booking.name) &&
                Objects.equals(surname, booking.surname);
    }

    @Override
    public int hashCode() {
        return Math.abs((flight.id().hashCode()*3+name.hashCode()*3+surname.hashCode()*3)*3);
    }
}
