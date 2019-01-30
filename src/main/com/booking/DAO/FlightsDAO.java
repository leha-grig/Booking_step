package com.booking.DAO;

import com.booking.interfaces.Constants;
import com.booking.Exceptions.FileReadingException;
import com.booking.utils.ObjectToFileReaderWriter;
import com.booking.objects.Flight;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FlightsDAO implements DAO<String, Flight> {
    private Map<String, Flight> flights;
    private ObjectToFileReaderWriter<Map<String, Flight>> objectToFileReaderWriter = new ObjectToFileReaderWriter();

    public FlightsDAO() {

        if (isCollectionExist(Constants.flightsPath)) {

            try {
                flights = objectToFileReaderWriter.readObjectFromFile(Constants.flightsPath, flights);
            } catch (FileReadingException e) {
                System.out.println(e.getMessage());
                System.out.println("The source file will be renewed!");
                CollectionGenerator collectionGenerator = new CollectionGenerator();
                this.flights = collectionGenerator.generateNewFlightsCollection(1000, 47);
            }

        } else {
            CollectionGenerator collectionGenerator = new CollectionGenerator();
            this.flights = collectionGenerator.generateNewFlightsCollection(1000, 47);
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
        return flights.get(ID);
    }

    @Override
    public void save(Flight flight) {
        if (flight != null) {
            flights.put(flight.id(), flight);
            objectToFileReaderWriter.writeObjectToFile(Constants.flightsPath, flights);
        }
    }

    @Override
    public Collection<Flight> getAll() {
        return flights.values();
    }

    @Override
    public void remove(String id) {
        if (id == null) {
            return;
        }

        flights.remove(id);
        objectToFileReaderWriter.writeObjectToFile(Constants.flightsPath, flights);
    }


    public Map<String, Flight> getFlights() {
        return flights;
    }

}
