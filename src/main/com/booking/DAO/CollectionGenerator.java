package com.booking.DAO;

import com.booking.interfaces.Constants;
import com.booking.utils.ObjectToFileReaderWriter;
import com.booking.objects.Flight;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**Evoking to generate new flightsDAO collection if it does not exist or wrong*/
public class CollectionGenerator {

    private List<String> cities;
    private ObjectToFileReaderWriter<Map<String, Flight>> objectToFileReaderWriter = new ObjectToFileReaderWriter();

    {
        try {
            cities = Files.readAllLines(Paths.get(Constants.cities));
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

        objectToFileReaderWriter.writeObjectToFile(Constants.flightsPath, flights);

        return flights;
    }
}
