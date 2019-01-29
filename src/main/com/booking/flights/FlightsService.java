package com.booking.flights;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class FlightsService {

    private FlightsDAO flightsDAO;

    public FlightsService(FlightsDAO flightsDAO) {
        this.flightsDAO = flightsDAO;
    }

    public List<Flight> showFlightsFor24hours() {

        List<Flight> flightsList = new ArrayList<>(flightsDAO.getAll());
        List<Flight> selectedFlights = flightsList.stream()
                .filter(flight -> flight.getDateTime().isBefore(LocalDateTime.now().plusHours(24)) && flight.getDateTime().isAfter(LocalDateTime.now()))
                .sorted((f1, f2) -> (int) Duration.between(f2.getDateTime(), f1.getDateTime()).getSeconds())
                .collect(Collectors.toList());
        if (selectedFlights.size() == 0) {
            System.out.println("No flights for 24 hours found");
        } else {
            selectedFlights.forEach(System.out::println);
        }

        return selectedFlights;
    }

    public Flight showFlightByID(String id) {
        Flight flight = flightsDAO.getById(id);
        if (flight == null) {
            System.out.println("flight is not found");
            return null;
        }
        System.out.printf("%s%4d%n", flight.toString(), flight.getFreeSits());

        return flight;
    }

    public List<Flight> showSelectedFlights(String destination, String date, int passengers) {
        if (!date.matches("\\d{4}[-./]\\d{2}[-./]\\d{2}")) {
            return null;
        }
        date = correctDateFormat(date);
        LocalDate parsedDate = LocalDate.parse(date);
        List<Flight> flightsList = new ArrayList(flightsDAO.getAll());
        List<Flight> selectedFlights = flightsList.stream()
                .filter(f -> f.getDestination().matches(destination))
                .filter(f -> f.getDate().isEqual(parsedDate))
                .filter(f -> f.getFreeSits() >= passengers)
                .collect(Collectors.toList());
        if (selectedFlights.size() == 0) {
            System.out.println("No flights for your request found");
        } else {
            selectedFlights.forEach(f -> System.out.printf("%3d%-3s%s%n", (selectedFlights.indexOf(f) + 1), ". ", f.toString()));
        }
        return selectedFlights;
    }

    // исправление формата даты. Пользователь имеет право ввести в формате yyyy.MM.dd или yyyy/MM/dd или yyyy-MM-dd
    private String correctDateFormat(String date) {
        if (date.matches("\\d{4}[-./]\\d{2}[-./]\\d{2}")) {
            date = date.replaceAll("[/.]", "-");
        }
        return date;
    }

    public void saveFlight(Flight flight) {
        flightsDAO.save(flight);
    }

}
