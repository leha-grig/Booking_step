package com.booking.Flights;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class FlightsServiceTest {
    private static FlightsDAO flightsDAO;
    private static LocalDateTime ldt1;
    private static LocalDateTime ldt2;
    private static Flight flight1;
    private static Flight flight2;
    private static FlightsService flightsService;

    @Before
    public void setUp(){
        ldt1 = LocalDateTime.now().plusHours(5);
        ldt2 = ldt1.plusHours(50);
        flight1 = new Flight(ldt1, "Kiev", "Lvov", 100);
        flight2 = new Flight(ldt2, "Kiev", "Odessa", 90);
        flightsDAO = new FlightsDAO(flight1, flight2);
        flightsService = new FlightsService(flightsDAO);
    }

    @Test
    public void does_showFlightsFor24hours_returnEmptyListWhenNoFlightsFor24hours() {
        //given
        flightsDAO.remove(flight1);
        //when
        List<Flight> result = flightsService.showFlightsFor24hours();
        //then
        assertThat(result).isEmpty();
    }

    @Test
    public void doea_showFlightsFor24hours_returnCorrectList() {
        //given
        //when
        List<Flight> result = flightsService.showFlightsFor24hours();
        List<Flight> expected = new ArrayList<>(Arrays.asList(flight1));
        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void does_showSelectedFlights_returnNullWhenDateFormatIsIncorrect() {
        //given
        //when
        List<Flight> result = flightsService.showSelectedFlights("Lvov", "29-01-2019", 2);
        //then
        assertThat(result).isNull();
    }

    @Test
    public void does_showSelectedFlights_returnEmptyListWhenNoSuchDestination() {
        //given
        String date = ldt1.toLocalDate().toString();
        //when
        List<Flight> result = flightsService.showSelectedFlights("Rom", date, 2);
        //then
        assertThat(result).isEmpty();
    }

    @Test
    public void does_showSelectedFlights_returnEmptyListWhenNoSuchDate() {
        //given
        String date = ldt1.plusHours(96).toLocalDate().toString();
        //when
        List<Flight> result = flightsService.showSelectedFlights("Lvov", date, 2);
        //then
        assertThat(result).isEmpty();
    }

    @Test
    public void does_showSelectedFlights_returnEmptyListWhenNoEnoughSits() {
        //given
        String date = ldt1.toLocalDate().toString();
        //when
        List<Flight> result = flightsService.showSelectedFlights("Lvov", date, 152);
        //then
        assertThat(result).isEmpty();
    }

    @Test
    public void does_showSelectedFlights_returnCorrectList() {
        //given
        String date = ldt1.toLocalDate().toString();
        //when
        List<Flight> result = flightsService.showSelectedFlights("Lvov", date, 2);
        ArrayList<Flight> expected = new ArrayList<>(Arrays.asList(flight1));
        //then
        assertThat(result).isEqualTo(expected);
    }
}