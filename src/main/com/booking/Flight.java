package com.booking;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Flight implements Serializable {
    private LocalDateTime dateTime;
    private String departureCity;
    private String destination;
    private String id;
    private int capasity;
    private int bookedSits;

    public Flight(LocalDateTime dateTime, String departureCity, String destination, int capasity) {
        this.dateTime = dateTime;
        this.departureCity = departureCity;
        this.destination = destination;
        this.id = setID();
        this.capasity = capasity;
    }
// геттеры, конструктор, Иквелс и хэш-код,
    // конструктор должен генерить уникальное ID (вызов приватного метода) и записывать его в поле id;
    // метод вывода свободных мест,
    // метод получения даты,
    // метод получения времени

    public LocalDate getDate() {
        return LocalDate.from(dateTime);
    }

    public String getTime (){
        return LocalTime.from(dateTime).format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    private String setID (){
        return ""+departureCity.charAt(0)+destination.charAt(0)+dateTime.getMonthValue()+dateTime.getDayOfMonth()+dateTime.getHour()+dateTime.getMinute();
    }

    @Override
    public String toString() {
        return id + " " + getDate() + " "+ getTime()+" "+ departureCity + " " + destination + " ";
    }

    public void setBookedSits(int bookedSits) {
        this.bookedSits = bookedSits;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getDestination() {
        return destination;
    }

    public String getId() {
        return id;
    }

    public int getCapasity() {
        return capasity;
    }

    public int getBookedSits() {
        return bookedSits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return capasity == flight.capasity &&
                Objects.equals(dateTime, flight.dateTime) &&
                Objects.equals(departureCity, flight.departureCity) &&
                Objects.equals(destination, flight.destination) &&
                Objects.equals(id, flight.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, departureCity, destination, id);
    }

}
