package com.booking.flights;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class FlightsDAOTest {
    private static FlightsDAO flightsDAO;
    private static LocalDateTime ldt1;
    private static LocalDateTime ldt2;
    private static Flight flight1;
    private static Flight flight2;

    @Before
    public void setUp() {
        ldt1 = LocalDateTime.of(2019, 1, 30, 15, 30, 0, 0);
        ldt2 = LocalDateTime.of(2019, 2, 5, 9, 15, 0, 0);
        flight1 = new Flight(ldt1, "Kiev", "Lvov", 100);
        flight2 = new Flight(ldt2, "Kiev", "Odessa", 90);
        flightsDAO = new FlightsDAO(flight1, flight2);
    }

    @Test
    public void does_getById_returnNullWhenIDisNull() {
        //given
        //when
        Flight result = flightsDAO.getById("VL13015");
        //then
        assertThat(result).isNull();
    }

    @Test
    public void does_getById_returnNullWhenIDisIncorrect() {
        //given
        //when
        Flight result = flightsDAO.getById("VL13015");
        //then
        assertThat(result).isNull();
    }

    @Test
    public void does_getById_returnCorrectFlight() {
        //given
        //when
        Flight result = flightsDAO.getById("KL1301530");
        Flight expected = flight1;
        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void does_save_doNothingWhenFlightIsNull() {
        //given
        //when
        Map<String, Flight> before = new HashMap<>(flightsDAO.getFlights());
        flightsDAO.save(null);
        Map<String, Flight> after = new HashMap<>(flightsDAO.getFlights());
        //then
        assertThat(after).isEqualTo(before);
    }

    @Test
    public void does_save_saveTheFlightToTheMap() {
        //given
        LocalDateTime ldt3 = LocalDateTime.of(2019, 02, 10, 12, 0, 0, 0);
        Flight flight3 = new Flight(ldt3, "Kiev", "Rom", 150);
        //when
        Map<String, Flight> expected = new HashMap<>(flightsDAO.getFlights());
        expected.put("KR210120", flight3);
        flightsDAO.save(flight3);
        Map<String, Flight> result = new HashMap<>(flightsDAO.getFlights());
        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void does_getAll_returnCorrectCollection() {
        //given
        //when
        List<Flight> result = new ArrayList<>(flightsDAO.getAll());
        Collection<Flight> expected = new ArrayList<>(Arrays.asList(flight1,flight2));
        //then
        assertThat(result).isEqualTo(expected);
    }

    /*
    // как избежать ошибки при введении null пользователем, если null перекрывает две сигнатуры метода?
    @Test
    public void does_remove_notChangeTheMapWhenFlightIDisNull() {
        //given
        //when
        Map<String, Flight> before = new HashMap<>(flightsDAO.getFlights());
        flightsDAO.remove(null);
        Map<String, Flight> after = flightsDAO.getFlights();
        //then
        assertThat(after).isEqualTo(before);
    }*/

    @Test
    public void does_remove_notChangeTheMapWhenFlightIDisNotCorrect() {
        //given
        //when
        Map<String, Flight> before = new HashMap<>(flightsDAO.getFlights());
        flightsDAO.remove("KL13015");
        Map<String, Flight> after = flightsDAO.getFlights();
        //then
        assertThat(after).isEqualTo(before);
    }

    @Test
    public void does_remove_removeCorrectFlightFromTheMapById() {
        //given
        //when
        Map<String, Flight> before = new HashMap<>(flightsDAO.getFlights());
        before.remove("KL1301530");
        flightsDAO.remove("KL1301530");
        Map<String, Flight> after = flightsDAO.getFlights();
        //then
        assertThat(after).isEqualTo(before);
    }

    @Test
    public void does_remove_removeCorrectFlightFromTheMapByObject() {
        //given
        //when
        Map<String, Flight> before = new HashMap<>(flightsDAO.getFlights());
        before.remove("KL1301530", flight1);
        flightsDAO.remove(flight1);
        Map<String, Flight> after = flightsDAO.getFlights();
        //then
        assertThat(after).isEqualTo(before);
    }
}