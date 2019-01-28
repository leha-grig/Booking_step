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
        LocalDateTime localDate = LocalDateTime.now();
        Flight flight = new Flight(localDate, "Odessa", "Paris", 10);
        Booking booking = new Booking(flight, "V", "L");
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
            expectedResult = bookingsService.createBooking(flight, "V", "L", flightController);
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
        BookingsDAO bookingsDAO = new CollectionBookingsDAO();
        BookingsService bookingsService = new BookingsService(bookingsDAO);
        FlightsDAO flightsDAO = new FlightsDAO();
        FlightsService flightsService = new FlightsService(flightsDAO);
        FlightController flightController = new FlightController(flightsService);
      /*  try {
            bookingsService.createBooking(flight, "V", "L", flightController);
        } catch (BookingAlreadyExist e) {
            e.getMessage();
        }*/
        //when
        List<Booking> expectedResult = bookingsService.showSelectedBookings("V", "L");
        List<Booking> resultTrue = null;
        //then
        assertEquals(expectedResult, resultTrue);
    }

}
