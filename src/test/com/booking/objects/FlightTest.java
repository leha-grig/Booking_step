package com.booking.objects;


import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

public class FlightTest {
    private static LocalDateTime localDateTime;
    private static Flight flight;

    @Before
    public void beforeEach() {
        localDateTime = LocalDateTime.of(2019, 1, 25, 15, 30, 0, 0);
        flight = new Flight(localDateTime, "Kiev", "Lvov", 150);
    }

    @Test
    public void is_getDate_returnsCorrectDate() {
        //given
        //when
        LocalDate result = flight.getDate();
        LocalDate expected = LocalDate.of(2019, 1, 25);
        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void is_getTime_returnsCorrectTime() {
        //given
        //when
        String result = flight.getTime();
        String expected = LocalTime.of(15, 30, 0, 0).format(DateTimeFormatter.ofPattern("HH:mm"));
        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void is_getFreeSits_returnsCorrectNumber() {
        //given
        //when
        int result = flight.getFreeSits();
        //then
        assertThat(result).isEqualTo(150);
    }

    @Test
    public void is_setId_returnsCorrectString() {
        //given
        //when
        String result = flight.id();
        //then
        assertThat(result).isEqualTo("KL1251530");
    }
}