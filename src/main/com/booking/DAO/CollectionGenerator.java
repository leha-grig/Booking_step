package com.booking.DAO;

import com.booking.Exceptions.FileReadingException;
import com.booking.interfaces.Constants;
import com.booking.objects.Flight;
import com.booking.utils.ObjectToFileReaderWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Evoking to generate new flightsDAO collection if it does not exist or wrong
 */
public class CollectionGenerator {

    private List<String> cities;
    private ObjectToFileReaderWriter<Map<String, Flight>> objectToFileReaderWriter = new ObjectToFileReaderWriter();

    public CollectionGenerator() throws FileReadingException{
        try {
            cities = Files.readAllLines(Paths.get(Constants.cities));
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileReadingException("The cities.txt file can not be read properly.");

        }
    }


    public Map<String, Flight> generateNewFlightsCollection(int number, int interval, int capasity) {
        Map<String, Flight> flights = new HashMap<>();
        cities.forEach(city->{
            LocalDateTime[] ldt = {
                    LocalDate.now().atStartOfDay()
            };
        for (int i = 0; i <= number; i++) {
            int index = (int)(Math.random()*cities.size());
            while (city.equals(cities.get(index))){
                index = (int)(Math.random()*cities.size());
            }
            Flight flight = new Flight(ldt[0],city, cities.get(index), capasity);
            ldt[0] = ldt[0].plusMinutes(interval);
            flights.put(flight.id(), flight);
        }
        });
        objectToFileReaderWriter.writeObjectToFile(Constants.flightsPath, flights);
        return flights;
    }
}
