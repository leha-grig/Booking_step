package com.booking.Flights;

import com.booking.ObjectToFileReaderWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CollectionGenerator {

    private List<String> cities;
    private ObjectToFileReaderWriter<Map<String, Flight>> objectToFileReaderWriter = new ObjectToFileReaderWriter();

    {
        try {
            cities = Files.readAllLines(Paths.get("./cities.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Map<String, Flight> generateNewFlightsCollection(int number, int cityNumbers) {
        LocalDateTime ldt = LocalDate.now().atStartOfDay();
        Map<String, Flight> flights = new HashMap<>();
        int cityCount = 0;
        for (int i = 0; i<=number; i++){
            if (cityCount >= cities.size() || cityCount > cityNumbers){
                cityCount = 0;
            }
            Flight flight = new Flight(ldt, "Kyiv", cities.get(cityCount), 150);
            cityCount += 1;
            ldt = ldt.plusMinutes(15);
            flights.put(flight.id(), flight);
        }

        objectToFileReaderWriter.writeObjectToFile("./flights.txt", flights);

        return flights;
    }
}
