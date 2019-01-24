package com.booking;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.stream.Collectors;


public class FlightsService {

    private FlightsDAO flightsDAO;

    public FlightsService(FlightsDAO flightsDAO) {
        this.flightsDAO = flightsDAO;
    }

    List<Flight> showFlightsFor24hours() {

        List<Flight> flightsList = new ArrayList<Flight>(flightsDAO.getFlights().values());
        List<Flight> selectedFlights = flightsList.stream()
                .filter(flight -> flight.getDateTime().isBefore(LocalDateTime.now().plusHours(24)) && flight.getDateTime().isAfter(LocalDateTime.now()))
                .sorted((f1, f2) -> (int) Duration.between(f2.getDateTime(), f1.getDateTime()).getSeconds())
                .collect(Collectors.toList());

        selectedFlights.forEach(System.out::println); // пока так, потом нужно будет переделать красивее с классом PrintAllLines

        return selectedFlights;
    }

    Flight showFlightByID(String id) {
        return flightsDAO.getFlightByID(id);
    }

    List<Flight> showSelectedFlights(String destination, String date, int passangers) {
        date = correctDateFormat(date);
        LocalDate parsedDate = LocalDate.parse(date);
        List<Flight> flightsList = new ArrayList<Flight>(flightsDAO.getFlights().values());
        List<Flight> selectedFlights = flightsList.stream()
                .filter(f -> f.getDestination().matches(destination))
                .filter(f -> f.getDate().isEqual(parsedDate))
                .filter(f-> f.getFreeSits() >= passangers)
                .collect(Collectors.toList());
        selectedFlights.forEach(f->System.out.println((selectedFlights.indexOf(f)+1) + ". " + f.toString()));
        return selectedFlights;
    }

    // исправление формата даты. Пользователь имеет право ввести в формате yyyy.MM.dd или yyyy/MM/dd или yyyy-MM-dd
    private String correctDateFormat (String date) {
        if (date.matches("\\d{4}[-./]\\d{2}[-./]\\d{2}")) {
            date = date.replaceAll("[/.]", "-");
        }
        return date;
    }

}
