package com.booking;

import com.booking.Exceptions.BookingAlreadyExist;
import com.booking.Flights.Flight;
import com.booking.Flights.FlightController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BookingsService {

    private BookingsDAO bookingDAO;

    public BookingsService(BookingsDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    //TODO: сделать проверку имени фамилии и не создавать, если уже есть на данный самолет.
    // TODO: Переделать коллекцию букинг в Мапу, гле клюси это рейсы, значения - листы букингов на рейсы
    public Booking createBooking(Flight flight, String name, String surname, FlightController flightController) throws BookingAlreadyExist {

        List<Booking> flightBookings = bookingDAO.getAllBookings().get(flight);

        // проверка на то, есть ли уже такой букинг на данном рейсе
        final boolean[] check = {false};
        if (flightBookings != null) {
            flightBookings.forEach(bkng -> {
                if (bkng.getName().equals(name) && bkng.getSurname().equals(surname)) {
                    check[0] = true;
                }
            });
        }
        if (check[0]) {
            throw new BookingAlreadyExist("Booking with this passenger is already exist on this flight");
        } else {

            int flightSeats = flight.getBookedSits();
            Booking booking = new Booking(flight, name, surname);


            flight.setBookedSits(++flightSeats);
            flightController.saveFlight(flight);

            bookingDAO.saveBooking(booking);
            return booking;
        }
    }

    public boolean deleteBookingByID(int ID) {
        return bookingDAO.deleteBooking(ID);
    }

    public List<Booking> showSelectedBookings(String name, String surname) {
        List<List<Booking>> bookingsList = new ArrayList<>(bookingDAO.getAllBookings().values());
        List<Booking> flattenBookings = bookingsList.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        List<Booking> selectedBookings = flattenBookings.stream()
                .filter(booking -> booking.getName().matches(name))
                .filter(booking -> booking.getSurname().matches(surname))
                .collect(Collectors.toList());
        selectedBookings.forEach(booking ->

                System.out.printf("%3d%-3s%s%n", (selectedBookings.indexOf(booking) + 1), ". ", booking.toString()));

        return selectedBookings;
    }

}
