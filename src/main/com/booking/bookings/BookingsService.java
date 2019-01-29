package com.booking.bookings;

import com.booking.Exceptions.BookingAlreadyExist;
import com.booking.flights.Flight;
import com.booking.flights.FlightController;

import java.util.List;
import java.util.stream.Collectors;

public class BookingsService {

    private CollectionBookingsDAO bookingDAO;

    public BookingsService(CollectionBookingsDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }


    public Booking createBooking(Flight flight, String name, String surname, FlightController flightController) throws BookingAlreadyExist {

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
            flightController.saveFlight(flight);

            bookingDAO.save(booking);
            return booking;
        }
    }

    public void deleteBookingByID(int ID, FlightController flightController) {
        final boolean[] check = {false};
        final Flight[] flight = {null};
        bookingDAO.getAll().forEach((booking) -> {
            if (booking.id().equals(ID)) {
                check[0] = true;
                flight[0] = booking.getFlight();
            }
        });
        if (check[0]) {
            int flightSeats = flight[0].getBookedSits();
            flight[0].setBookedSits(--flightSeats);
            flightController.saveFlight(flight[0]);
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
