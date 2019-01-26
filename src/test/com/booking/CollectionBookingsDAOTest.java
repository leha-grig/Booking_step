package com.booking;

/*import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CollectionBookingsDAOTest {

    @Test
    public void shouldReturnTrueIfBookingCollectionExist() {
        //given
        //when
        //then
    }

    @Test
    public void shouldReadBookingsCollectionFromFile() {
        //given
        //when
        //then
    }

    @Test
    public void shouldWriteBookingsObjectsToFile() {
        //given
        //when
        //then
    }*/

    //@Test
    /*public void getAllBookingsShouldReturnBookingsList() {
        //given
        Booking booking = new Booking();
        List<Booking> bookings = new ArrayList<>();
        BookingsDAO bookingsDAO = new CollectionBookingsDAO(bookings);
        bookingsDAO.saveBooking(booking);
        //when
        List<Booking> expectedResult = bookings;
        //List<Booking> resultTrue = bookingsDAO.getAllBookings();
        List<Booking> resultFalse = new ArrayList<>();
        //then
        assertEquals(expectedResult, resultTrue);
        assertNotEquals(expectedResult, resultFalse);
    }

    @Test
    public void getBookingByIndexShouldReturnBooking() {
        //given
        Booking booking = new Booking();
        List<Booking> bookings = new ArrayList<>();
        BookingsDAO bookingsDAO = new CollectionBookingsDAO(bookings);
        bookingsDAO.saveBooking(booking);
        //when
        Booking expectedResult1 = booking;
        Booking expectedResult2 = null;
        Booking resultTrue = bookingsDAO.getBookingByIndex(0);
        Booking resultFalse1 = bookingsDAO.getBookingByIndex(1);
        Booking resultFalse2 = bookingsDAO.getBookingByIndex(-1);
        //then
        assertEquals(expectedResult1, resultTrue);
        assertEquals(expectedResult2, resultFalse1);
        assertEquals(expectedResult2, resultFalse2);
        assertNotEquals(expectedResult2, resultTrue);
    }

    @Test
    public void getBookingByIDShouldReturnBooking() {
        //given
        Booking booking = new Booking();
        List<Booking> bookings = new ArrayList<>();
        BookingsDAO bookingsDAO = new CollectionBookingsDAO(bookings);
        bookingsDAO.saveBooking(booking);
        //when
        Booking expectedResult1 = booking;
        String expectedResult2 = String.format("%-12s%-15s%-15s%s", 0, null, null, null);
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
        List<Booking> bookings = new ArrayList<>();
        BookingsDAO bookingsDAO = new CollectionBookingsDAO(bookings);
        bookingsDAO.saveBooking(booking);
        //when
        int expectedResult = bookings.size() - 1;
        boolean resultTrue = bookingsDAO.deleteBooking(booking);
        int result = bookings.size();
        boolean resultFalse = bookingsDAO.deleteBooking(booking2);
        //then
        assertThat(resultTrue, is(true));
        assertThat(resultFalse, is(false));
        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldDeleteBookingByBookingIDAndReturnBooleanValue() {
        //given
        Booking booking = new Booking();
        List<Booking> bookings = new ArrayList<>();
        BookingsDAO bookingsDAO = new CollectionBookingsDAO(bookings);
        bookingsDAO.saveBooking(booking);
        //when
        int expectedResult = bookings.size() - 1;
        boolean resultTrue = bookingsDAO.deleteBooking(booking.getID());
        int result = bookings.size();
        boolean resultFalse = bookingsDAO.deleteBooking(0);
        //then
        assertThat(resultTrue, is(true));
        assertThat(resultFalse, is(false));
        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldSaveBookingAndInBookingCollection() {
        //given
        Booking booking = new Booking();
        List<Booking> bookings = new ArrayList<>();
        BookingsDAO bookingsDAO = new CollectionBookingsDAO(bookings);
        bookingsDAO.saveBooking(booking);
        //when
        bookingsDAO.saveBooking(booking);
        int expectedResult = bookings.size();
        int resultTrue = 1;
        int resultFalse = 0;
        //then
        assertEquals(expectedResult, resultTrue);
        assertNotEquals(expectedResult, resultFalse);
    }


*/
//}
