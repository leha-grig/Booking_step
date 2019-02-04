package com.booking.services;

import com.booking.DAO.FlightsDAO;
import com.booking.objects.Flight;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FlightsService {

    private FlightsDAO flightsDAO;

    public FlightsService(FlightsDAO flightsDAO) {
        this.flightsDAO = flightsDAO;
    }

    public List<Flight> showFlightsFor24hours() {

        List<Flight> flightsList = new ArrayList<>(flightsDAO.getAll());
        List<Flight> selectedFlights = flightsList.stream()
                .filter(flight -> flight.getDepartureCity().equals("Kyiv"))
                .filter(flight -> flight.getDateTime().isBefore(LocalDateTime.now().plusHours(24)) && flight.getDateTime().isAfter(LocalDateTime.now()))
                .sorted((f1, f2) -> (int) Duration.between(f2.getDateTime(), f1.getDateTime()).getSeconds())
                .collect(Collectors.toList());
        if (selectedFlights.size() == 0) {
            System.out.println("No DAO for 24 hours found");
        } else {
            System.out.printf("%-16s%-12s%-10s%-10s%-15s%-15s%n", "FlightID", "Date", "DepTime", "ArrTime", "From", "Destination");
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
        System.out.printf("%-16s%-12s%-10s%-10s%-15s%-15s%-10s%n", "FlightID", "Date", "DepTime", "ArrTime", "From", "Destination", "Free sits");
        System.out.printf("%s%4d%n", flight.toString(), flight.getFreeSits());

        return flight;
    }

    // выводит сначала прямые рейсы из Киева, потом рейсы с одной пересадкой, если разница между временем прибытия из Киева в пересадочный
    // город и временем отправления в город назначения больше 1 часа и меньше 12 часов
    public List<Flight> showSelectedFlights(String destination, String date, int passengers) {
        if (!date.matches("\\d{4}[-./]\\d{2}[-./]\\d{2}")) {
            return null;
        }
        date = correctDateFormat(date);
        LocalDate parsedDate = LocalDate.parse(date);
        List<Flight> flightsList = new ArrayList(flightsDAO.getAll());
        List<Flight> selectedFlights = flightsList.stream()
                .filter(f -> f.getDestination().equals(destination))
                .filter(f -> f.getDepartureCity().equals("Kyiv"))
                .filter(f -> f.getDate().isEqual(parsedDate))
                .filter(f -> f.getFreeSits() >= passengers)
                .collect(Collectors.toList());
        List<Flight> connectionFlights = flightsList.stream()
                .filter(f -> f.getDepartureCity().equals("Kyiv"))
                .filter(f -> f.getDate().isEqual(parsedDate))
                .filter(f -> f.getFreeSits() >= passengers)
                .filter(f -> f.getFreeSits() >= passengers)
                .collect(Collectors.toList());
        connectionFlights.forEach(flight -> flightsList.stream()
                .filter(f -> f.getDepartureCity().equals(flight.getDestination()))
                .filter(f -> f.getDestination().equals(destination))
                .filter(f -> f.getDepDateTime().isBefore(flight.getArrDateTime().plusHours(12)))
                .filter(f -> f.getDepDateTime().isAfter(flight.getArrDateTime().plusHours(1)))
                .filter(f -> f.getFreeSits() >= passengers)
                .forEach(f -> {
                    selectedFlights.add(flight);
                    selectedFlights.add(f);
                }));
        if (selectedFlights.size() == 0) {
            System.out.println("No DAO for your request found");
        } else {
            System.out.printf("%-6s%-16s%-12s%-10s%-10s%-15s%-15s%n", "Num", "FlightID", "Date", "DepTime", "ArrTime", "From", "Destination");
            selectedFlights.forEach(f -> System.out.printf("%3d%-3s%s%n", (selectedFlights.indexOf(f) + 1), ". ", f.toString()));
        }
        return selectedFlights;
    }

    // приведение формата даты. Принимается строка в формате yyyy.MM.dd или yyyy/MM/dd или yyyy-MM-dd
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
