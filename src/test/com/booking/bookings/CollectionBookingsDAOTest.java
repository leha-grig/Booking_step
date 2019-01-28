package com.booking.bookings;

import com.booking.flights.Flight;
import com.booking.flights.FlightsDAO;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;


public class CollectionBookingsDAOTest {
    private static FlightsDAO flightsDAO;
    private static LocalDateTime ldt1;
    private static LocalDateTime ldt2;
    private static Flight flight1;
    private static Flight flight2;
    private static Booking booking1;
    private static Booking booking2;
    private static CollectionBookingsDAO bookingsDAO;


    @Before
    public void setUp() {
        ldt1 = LocalDateTime.of(2019, 1, 30, 15, 30, 0, 0);
        ldt2 = LocalDateTime.of(2019, 2, 5, 9, 15, 0, 0);
        flight1 = new Flight(ldt1, "Kiev", "Lvov", 100);
        flight2 = new Flight(ldt2, "Kiev", "Odessa", 90);
        flightsDAO = new FlightsDAO(flight1, flight2);
        booking1 = new Booking(flight1, "Euha", "Saturn");
        booking2 = new Booking(flight2, "Kot", "Vaska");
        bookingsDAO = new CollectionBookingsDAO(booking1, booking2);
    }

    @Test
    public void getAll_ShouldReturnBookingsList() {
        //given

        //when
        List<Booking> result = new ArrayList<>(bookingsDAO.getAll());
        ArrayList<Booking> expected = new ArrayList<>(Arrays.asList(booking2, booking1));
        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void getBookingByID_ShouldReturnBooking() {
        //given

        //when
        Booking expected = booking1;
        Booking result = bookingsDAO.getById(booking1.id());

        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void should_remove_ById_removeObject() {
        //given

        //when
        List<Booking> before = new ArrayList<>(bookingsDAO.getAll());
        before.remove(booking1);
        bookingsDAO.remove(booking1.id());
        List<Booking> after = new ArrayList<>(bookingsDAO.getAll());
        //then
        assertThat(after).isEqualTo(before);
    }


    @Test
    public void should_remove_ByObject_removeObject() {
        //given

        //when
        List<Booking> before = new ArrayList<>(bookingsDAO.getAll());
        before.remove(booking1);
        bookingsDAO.remove(booking1);
        List<Booking> after = new ArrayList<>(bookingsDAO.getAll());
        //then
        assertThat(after).isEqualTo(before);
    }


    @Test
    public void should_SaveBooking_AddInBookingCollection() {
        //given
        Booking booking3 = new Booking(flight2, "Kot", "Murzik");
        //when
        bookingsDAO.save(booking3);
        List<Booking> result = new ArrayList<>(bookingsDAO.getAll());
        //then
        assertThat(result).contains(booking3);
    }
}
