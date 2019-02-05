package com.booking.DAO;

import com.booking.Exceptions.*;
import com.booking.interfaces.Constants;
import com.booking.utils.ObjectToFileReaderWriter;
import com.booking.objects.Flight;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Evoking to generate new flightsDAO collection if it does not exist or wrong
 */
public class CollectionGenerator {

    private final List<String> cities;
    private final ObjectToFileReaderWriter<Map<String, Flight>> objectToFileReaderWriter = new ObjectToFileReaderWriter();


    public CollectionGenerator() throws FileReadingException {
        try {
            cities = Files.readAllLines(Paths.get(Constants.cities));
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileReadingException("The cities.txt file can not be read properly. Put the correct cities.txt file to the root directory!");
        }
    }


    public Map<String, Flight> generateNewFlightsCollection(int number, int interval, int capasity) {


        Map<String, Flight> flights = new HashMap<>();

        cities.forEach(city -> {
            LocalDateTime[] ldt = {LocalDate.now().atStartOfDay()};
            for (int i = 0; i < number; i++) {
                int index = (int) (Math.random() * cities.size());
                while (city.equals(cities.get(index))) {
                    index = (int) (Math.random() * cities.size());
                }
                Flight flight = new Flight(ldt[0], city, cities.get(index), capasity);
                ldt[0] = ldt[0].plusMinutes(interval);
                flights.put(flight.id(), flight);
            }
        });


        objectToFileReaderWriter.writeObjectToFile(Constants.flightsPath, flights);

        return flights;
    }

    /*private List<Flight> flightsDailyPerCityGenerator(){
        int flightsPerDay = 1440 / interval;
        List<Flight> flightsPerCity = new ArrayList<>();
        for (int i = 0; i <= flightsPerDay; i++) {
            int index = (int) (Math.random() * cities.size());
            while (city.equals(cities.get(index))) {
                index = (int) (Math.random() * cities.size());
            }
            Flight flight = new Flight(ldt[0], city, cities.get(index), 150);
            ldt[0] = ldt[0].plusMinutes(interval);
            flights.put(flight.id(), flight);
            flightsPerCity.add(flight);
        }
    }*/
}
