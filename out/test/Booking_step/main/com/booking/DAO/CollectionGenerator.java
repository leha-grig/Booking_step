package com.booking.DAO;

import com.booking.Exceptions.*;
import com.booking.interfaces.Constants;
import com.booking.objects.User;
import com.booking.services.UserController;
import com.booking.services.UserService;
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

    private List<String> cities;
    private ObjectToFileReaderWriter<Map<String, Flight>> objectToFileReaderWriter = new ObjectToFileReaderWriter();

    {
        try {
            cities = Files.readAllLines(Paths.get(Constants.cities));
        } catch (IOException e) {
            e.printStackTrace();
            try {
                throw new FileReadingException("The cities.txt file can not be read properly.");
            } catch (FileReadingException e1) {
                System.out.println(e1.getMessage());
                System.out.println("Put the correct cities.txt file to the root directory!");
                cities = new ArrayList<>(Arrays.asList("incorrect city 1", "incorrect city 2"));
            }
        }
    }


    public Map<String, Flight> generateNewFlightsCollection(int number, int cityNumbers) {

        LocalDateTime ldt = LocalDate.now().atStartOfDay();
        Map<String, Flight> flights = new HashMap<>();
        int cityCount = 0;
        for (int i = 0; i <= number; i++) {
            if (cityCount >= cities.size() || cityCount > cityNumbers) {
                cityCount = 0;
            }
            Flight flight = new Flight(ldt, "Kyiv", cities.get(cityCount), 150);
            cityCount++;
            ldt = ldt.plusMinutes(15);
            flights.put(flight.id(), flight);
        }

        objectToFileReaderWriter.writeObjectToFile(Constants.flightsPath, flights);

        return flights;
    }
}
