package com.booking.services;

import com.booking.DAO.CollectionBookingsDAO;
import com.booking.Exceptions.BookingAlreadyExist;

import com.booking.objects.Booking;
import com.booking.objects.Flight;

import java.util.List;
import java.util.stream.Collectors;

public class BookingsService {

    private CollectionBookingsDAO bookingDAO;
    private FlightsService flightsService;

    public BookingsService(CollectionBookingsDAO bookingDAO, FlightsService flightsService) {
        this.bookingDAO = bookingDAO;
        this.flightsService = flightsService;
    }


    public Booking createBooking(Flight flight, String name, String surname) throws BookingAlreadyExist {

        List<Booking> flightBookings = bookingDAO.getBookings().get(flight);

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
            flightsService.saveFlight(flight);

            bookingDAO.save(booking);
            return booking;
        }
    }

    public void deleteBookingByID(int ID) {
        Booking booking = bookingDAO.getById(ID);
        if (booking != null) {
            Flight flight = booking.getFlight();
            int flightSeats = flight.getBookedSits();
            flight.setBookedSits(--flightSeats);
            flightsService.saveFlight(flight);
        }
        bookingDAO.remove(ID);
    }

    public List<Booking> showSelectedBookings(String name, String surname) {

        List<Booking> selectedBookings = bookingDAO.getAll().stream()
                .filter(booking -> booking.getName().matches(name))
                .filter(booking -> booking.getSurname().matches(surname))
                .collect(Collectors.toList());
        selectedBookings.forEach(booking ->

                System.out.printf("%3d%-3s%s%n", (selectedBookings.indexOf(booking) + 1), ". ", booking.toString()));

        return selectedBookings;
    }

}
