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
        LocalDateTime localDateTime = LocalDateTime.now();
        Booking booking = new Booking();
        Flight flight = new Flight(localDateTime, "Kyiv", "Helsinki", 150);
        List<Booking> bookings = new ArrayList<>();
        BookingsDAO bookingsDAO = new CollectionBookingsDAO(bookings);
        BookingsService bookingsService = new BookingsService();
        FlightsDAO flightsDAO = new FlightsDAO();
        FlightsService flightsService = new FlightsService(flightsDAO);
        FlightController flightController = new FlightController(flightsService);
        //when
        int flightSeats = flight.getBookedSits();
        flight.setBookedSits(flightSeats++);
        int expectedFlightSeatsResult = flight.getBookedSits() + 1;
        int unexpectedFlightSeatsResult = flight.getBookedSits() - 1;
        Booking expectedResult = bookingsService.createBooking(flight, "Valentyna", "Lysenok", flightController);;
        Booking resultTrue = bookingsDAO.getBookingByID(booking.getID());
        //then
        assertEquals(expectedFlightSeatsResult, flightSeats);
        assertNotEquals(unexpectedFlightSeatsResult, flightSeats);
        assertEquals(expectedResult, resultTrue);
    }

    @Test
    public void shouldShowSelectedBookingsAndReturnListOfBookings() {
        //given
        LocalDateTime localDateTime = LocalDateTime.now();
        Flight flight = new Flight(localDateTime, "Kyiv", "Helsinki", 150);
        Booking booking = new Booking(flight, "Valentyna", "Lysenok");
        List<Booking> bookings = new ArrayList<>();
        BookingsDAO bookingsDAO = new CollectionBookingsDAO(bookings);
        BookingsService bookingsService = new BookingsService();
        bookingsDAO.saveBooking(booking);
        //when
        List<Booking> expectedResult = bookingsService.showSelectedBookings("Valentyna", "Lysenok");
        List<Booking> resultTrue = bookings;
        //then
        assertEquals(expectedResult, resultTrue);
    }

}
