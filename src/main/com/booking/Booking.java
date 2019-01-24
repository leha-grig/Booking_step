package main.com.booking;

import java.time.LocalDate;
import java.util.Objects;

public class Booking {

    private String flight;
    private LocalDate date;
    private String name;
    private String surname;
    private int ID;

    public Booking(String flight, LocalDate date, String name, String surname, int ID) {
        this.flight = flight;
        this.date = date;
        this.name = name;
        this.surname = surname;
        this.ID = ID;
    }

    public String getFlight() {
        return flight;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getID() {
        return ID;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "flight='" + flight + '\'' +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", ID=" + ID +
                '}';
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
        return Objects.hash(flight, date, name, surname, ID);
    }

}
