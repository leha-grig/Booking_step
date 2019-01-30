package com.booking.services;

import com.booking.DAO.CollectionBookingsDAO;
import com.booking.DAO.FlightsDAO;
import com.booking.Exceptions.BookingAlreadyExist;
import com.booking.objects.Booking;
import com.booking.objects.Flight;
import org.junit.Before;
import org.junit.Test;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BookingsServiceTest {
    private static FlightsDAO flightsDAO;
    private static FlightsService flightsService;
    private static LocalDateTime ldt1;
    private static LocalDateTime ldt2;
    private static Flight flight1;
    private static Flight flight2;
    private static Booking booking1;
    private static Booking booking2;
    private static CollectionBookingsDAO bookingsDAO;
    private static BookingsService bookingsService;


    @Before
    public void setUp() {
        ldt1 = LocalDateTime.of(2019, 1, 30, 15, 30, 0, 0);
        ldt2 = LocalDateTime.of(2019, 2, 5, 9, 15, 0, 0);
        flight1 = new Flight(ldt1, "Kiev", "Lvov", 100);
        flight2 = new Flight(ldt2, "Kiev", "Odessa", 90);
        flightsDAO = new FlightsDAO(flight1, flight2);
        flightsService = new FlightsService(flightsDAO);
        booking1 = new Booking(flight1, "Euha", "Saturn");
        booking2 = new Booking(flight2, "Kot", "Vaska");
        bookingsDAO = new CollectionBookingsDAO(booking1, booking2);
        bookingsService = new BookingsService(bookingsDAO, flightsService);
    }

    @Test
    public void shouldCreateNewBooking_ReturnBookingObject() {
        //given
        FlightsDAO flightsDAO = new FlightsDAO(flight1, flight2);
        FlightsService flightsService = new FlightsService(flightsDAO);
        FlightController flightController = new FlightController(flightsService);
        //when
        Booking result = null;
        try {
            result = bookingsService.createBooking(flight2, "Kot", "Murzik");
        } catch (BookingAlreadyExist bookingAlreadyExist) {
            bookingAlreadyExist.printStackTrace();
        }
        Booking expected = new Booking(flight2, "Kot", "Murzik");
        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldCreateNewBooking_addNewBookingToTheMap() {
        //given
        FlightsDAO flightsDAO = new FlightsDAO(flight1, flight2);
        FlightsService flightsService = new FlightsService(flightsDAO);
        FlightController flightController = new FlightController(flightsService);
        //when
        Collection<Booking> result = null;
        try {
            bookingsService.createBooking(flight2, "Kot", "Murzik");
            result = bookingsDAO.getAll();
        } catch (BookingAlreadyExist bookingAlreadyExist) {
            bookingAlreadyExist.printStackTrace();
        }
        Booking booking3 = new Booking(flight2, "Kot", "Murzik");
        //then
        assertThat(result).contains(booking3);
    }

    @Test
    public void shouldCreateNewBooking_DoNotaddNewBookingToTheMap_whenTheBookingExist() {
        //given
        FlightsDAO flightsDAO = new FlightsDAO(flight1, flight2);
        FlightsService flightsService = new FlightsService(flightsDAO);
        FlightController flightController = new FlightController(flightsService);
        //when
        List<Booking> expected = null;
        try {
            bookingsService.createBooking(flight2, "Kot", "Murzik");
            expected = new ArrayList<>(bookingsDAO.getAll());
            bookingsService.createBooking(flight2, "Kot", "Murzik");

        } catch (BookingAlreadyExist bookingAlreadyExist) {
            bookingAlreadyExist.printStackTrace();
        }
        List<Booking> result = new ArrayList<>(bookingsDAO.getAll());
        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldShowSelectedBookingsAndReturnListOfBookings() {
        //given

        //when
        List<Booking> result = bookingsService.showSelectedBookings("Kot", "Vaska");
        List<Booking> expected = new ArrayList<>(Arrays.asList(booking2));
        //then
        assertThat(result).isEqualTo(expected);
    }
}
