package com.booking;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CollectionBookingsDAOTest {

    @Test
    public void getAllBookingsShouldReturnBookingsList() {
        //given
        Booking booking = new Booking();
        BookingsDAO bookingsDAO = new CollectionBookingsDAO();
        bookingsDAO.saveBooking(booking);
        //when
        Map<Flight, List<Booking>> expectedResult = bookingsDAO.getAllBookings();
        Map<Flight, List<Booking>> resultTrue = new HashMap<>();
        List<Booking> bookingList = new ArrayList<>();
        bookingList.add(booking);
        resultTrue.put(null, bookingList);
        Map<Flight, List<Booking>> resultFalse = new HashMap<>();
        //then
        assertEquals(expectedResult, resultTrue);
        assertNotEquals(expectedResult, resultFalse);
    }

    @Test
    public void getBookingByIDShouldReturnBooking() {
        //given
        Booking booking = new Booking();
        BookingsDAO bookingsDAO = new CollectionBookingsDAO();
        bookingsDAO.saveBooking(booking);
        //when
        Booking expectedResult1 = booking;
        String expectedResult2 = String.format("%-12s%-15s%-15s%s%n", 0, null, null, null);
        Booking resultTrue = bookingsDAO.getBookingByID(booking.getID());
        String resultFalse = bookingsDAO.getBookingByID(0).toString();
        //then
        assertEquals(expectedResult1, resultTrue);
        assertEquals(expectedResult2, resultFalse);
        assertNotEquals(expectedResult2, resultTrue);
    }

    @Test
    public void shouldDeleteBookingByBookingObjectAndReturnBooleanValue() {
        //given
        Booking booking = new Booking();
        Booking booking2 = new Booking();
        BookingsDAO bookingsDAO = new CollectionBookingsDAO();
        bookingsDAO.saveBooking(booking);
        //when
        boolean resultTrue = bookingsDAO.deleteBooking(booking);
        boolean resultFalse = bookingsDAO.deleteBooking(booking2);
        //then
        assertThat(resultTrue, is(true));
        assertThat(resultFalse, is(false));
    }

    @Test
    public void shouldDeleteBookingByBookingIDAndReturnBooleanValue() {
        //given
        //LocalDateTime localDate = LocalDateTime.now();
        Flight flight = new Flight();
        Booking booking = new Booking();
        FlightsDAO flightsDAO = new FlightsDAO();
        BookingsDAO bookingsDAO = new CollectionBookingsDAO();
        flightsDAO.saveFlight(flight);
        bookingsDAO.saveBooking(booking);
        //when
        Map<Flight, List<Booking>> bookings = bookingsDAO.getAllBookings();
        boolean resultTrue = bookingsDAO.deleteBooking(booking.getID());
        int expectedResult = bookings.size();
        System.out.println(expectedResult);
        //then
        assertThat(resultTrue, is(true));
        assertEquals(expectedResult, 0);
        assertNotEquals(expectedResult, 1);
    }

    @Test
    public void shouldSaveBookingAndInBookingCollection() {
        //given
        Booking booking = new Booking();
        BookingsDAO bookingsDAO = new CollectionBookingsDAO();
        bookingsDAO.saveBooking(booking);
        //when
        Map<Flight, List<Booking>> bookings = bookingsDAO.getAllBookings();
        int expectedResult = bookings.size();
        //then
        assertEquals(expectedResult, 1);
        assertNotEquals(expectedResult, 0);
    }
}
