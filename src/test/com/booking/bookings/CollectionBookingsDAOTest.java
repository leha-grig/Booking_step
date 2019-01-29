package com.booking.bookings;
import com.booking.Booking;
import com.booking.BookingsDAO;
import com.booking.Flight;
import org.junit.Before;
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
        LocalDateTime localDateTime = LocalDateTime.now();
        Flight flight = new Flight(localDateTime, "Odessa", "Paris", 10);
        Booking booking = new Booking(flight, "V", "L");
        Booking booking2 = new Booking();
        BookingsDAO bookingsDAO = new CollectionBookingsDAO();
        bookingsDAO.saveBooking(booking);
        //when
        boolean resultTrue = bookingsDAO.deleteBooking(booking.getID());
        boolean resultFalse = bookingsDAO.deleteBooking(booking2.getID());
        //then
        assertThat(resultTrue, is(true));
        assertThat(resultFalse, is(false));
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
