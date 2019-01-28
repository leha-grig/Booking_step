package com.booking;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingsServiceTest {

    @Test
    public void shouldCreateNewBookingAndReturnBookingObject() {
        //given
        Booking booking = new Booking();
        Flight flight = new Flight();
        BookingsDAO bookingsDAO = new CollectionBookingsDAO();
        BookingsService bookingsService = new BookingsService(bookingsDAO);
        FlightsDAO flightsDAO = new FlightsDAO();
        FlightsService flightsService = new FlightsService(flightsDAO);
        FlightController flightController = new FlightController(flightsService);
        //when
        int flightSeats = flight.getBookedSits();
        flight.setBookedSits(flightSeats++);
        int expectedFlightSeatsResult = flight.getBookedSits() + 1;
        int unexpectedFlightSeatsResult = flight.getBookedSits() - 1;
        Booking expectedResult = null;
        try {
            expectedResult = bookingsService.createBooking(flight, null, null, flightController);
        } catch (BookingAlreadyExist e) {
            e.getMessage();
        }
        Booking resultTrue = bookingsDAO.getBookingByID(booking.getID());
        //then
        assertEquals(expectedFlightSeatsResult, flightSeats);
        assertNotEquals(unexpectedFlightSeatsResult, flightSeats);
        assertEquals(expectedResult, resultTrue);
    }

    @Test
    public void shouldShowSelectedBookingsAndReturnListOfBookings() {
        //given
        LocalDateTime localDate = LocalDateTime.now();
        Flight flight = new Flight(localDate, "Odessa", "Paris", 10);
        Booking booking = new Booking(flight, "V", "L");
        List<Booking> bookings = new ArrayList<>();
        BookingsDAO bookingsDAO = new CollectionBookingsDAO();
        BookingsService bookingsService = new BookingsService(bookingsDAO);
        bookingsDAO.saveBooking(booking);
        //when
        List<Booking> expectedResult = bookingsService.showSelectedBookings("V", "L");
        List<Booking> resultTrue = bookings;
        //then
        assertEquals(expectedResult, resultTrue);
    }

}
