package com.booking.DAO;

import com.booking.interfaces.Constants;
import com.booking.Exceptions.FileReadingException;
import com.booking.logger.BookingServiceLogger;
import com.booking.utils.ObjectToFileReaderWriter;
import com.booking.objects.Flight;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FlightsDAO implements DAO<String, Flight> {
    private Map<String, Flight> flights;
    private final ObjectToFileReaderWriter<Map<String, Flight>> objectToFileReaderWriter = new ObjectToFileReaderWriter();

    public FlightsDAO() throws FileReadingException {

        if (isCollectionExist(Constants.flightsPath)) {

            try {
                flights = objectToFileReaderWriter.readObjectFromFile(Constants.flightsPath, flights);
            } catch (FileReadingException e) {
                System.out.println(e.getMessage());
                System.out.println("The source file will be renewed!");
                CollectionGenerator collectionGenerator = new CollectionGenerator();
                this.flights = collectionGenerator.generateNewFlightsCollection(500, 15, 150);
            }

        } else {
            CollectionGenerator collectionGenerator = new CollectionGenerator();
            this.flights = collectionGenerator.generateNewFlightsCollection(500, 15, 150);
        }
    }

    //constructor for testing
    public FlightsDAO(Flight flight1, Flight flight2) {
        flights = new HashMap<>();
        flights.put(flight1.id(), flight1);
        flights.put(flight2.id(), flight2);
    }


    private boolean isCollectionExist(String path) {
        return new File(path).isFile();
    }

    @Override
    public Flight getById(String ID) {
        //logger.info("User got Flight By ID");
        return flights.get(ID);
    }

    @Override
    public void save(Flight flight) {
        if (flight != null) {
            flights.put(flight.id(), flight);
            //logger.info("Flight was saved in flights database");
            objectToFileReaderWriter.writeObjectToFile(Constants.flightsPath, flights);
            //logger.info("Flights database was rewritten to ./flights.txt path");
        }
    }

    @Override
    public Collection<Flight> getAll() {
        //logger.info("Get all information about Flights");
        return flights.values();
    }

    @Override
    public void remove(String id) {
        if (id == null) {
            //logger.error("Error information: flight wasn't remove");
            return;
        }
        flights.remove(id);
        //logger.info("Flight was removed from flights database");
        objectToFileReaderWriter.writeObjectToFile(Constants.flightsPath, flights);
        //logger.info("Information was update in ./flights.txt path");
    }


    public Map<String, Flight> getFlights() {
        //logger.info("Get all information about Flights");
        return flights;
    }

}
