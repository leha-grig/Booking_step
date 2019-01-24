package com.booking;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

public class CollectionGenerator {

    private List<String> cities;
    {
        try {
            cities = Files.readAllLines(Paths.get("./cities.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Map<String, Flight> generateNewFlightsCollection(int number, int cityNumbers) {
        LocalDateTime ldt = LocalDate.now().atStartOfDay();
        Map<String, Flight> flights = new TreeMap<>();
        int cityCount = 0;
        for (int i = 0; i<=number; i++){
            if (cityCount >= cities.size() || cityCount > cityNumbers){
                cityCount = 0;
            }
            Flight flight = new Flight(ldt, "Kyiv", cities.get(cityCount), 150);
            cityCount += 1;
            ldt = ldt.plusMinutes(15);
            flights.put(flight.getId(), flight);
        }
//        Files.write("./flights.txt", flights)
        FileOutputStream file = null;
        try {
            file = new FileOutputStream("./flights.txt");
            ObjectOutputStream data = new ObjectOutputStream(file);
            data.writeObject(flights);
            data.flush();
            data.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flights;
    }
}
