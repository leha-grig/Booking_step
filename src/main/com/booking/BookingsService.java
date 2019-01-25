package com.booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingsService {
   
    private BookingsDAO bookingDAO;

    //TODO: сделать проверку имени фамилии и не создавать, если уже есть на данный самолет.
    // TODO: Переделать коллекцию букинг в Мапу, гле клюси это рейсы, значения - листы букингов на рейсы
    public Booking createBooking(Flight flight, String name, String surname, FlightController flightController) {
        int flightSeats = flight.getBookedSits();
        Booking booking = new Booking(flight, name, surname);

        flight.setBookedSits(flightSeats++);
        flightController.saveFlight(flight);

        bookingDAO.saveBooking(booking);
        return bookingDAO.getBookingByID(booking.getID());
    }

    public boolean deleteBookingByID(int ID) {
        return bookingDAO.deleteBooking(ID);
    }

    public List<Booking> showSelectedBookings(String name, String surname) {
        List<Booking> bookings = new ArrayList<>(bookingDAO.getAllBookings());
        List<Booking> selectedBookings = bookings.stream()
                .filter(booking -> booking.getName().matches(name))
                .filter(booking -> booking.getSurname().matches(surname))
                .collect(Collectors.toList());
        selectedBookings.forEach(booking ->
                System.out.printf("%3d%-3s%s%n",(selectedBookings.indexOf(booking)+1), ". ", booking.toString()));
        return bookings;
    }

}
