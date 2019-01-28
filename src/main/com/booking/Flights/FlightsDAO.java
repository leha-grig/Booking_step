package com.booking.Flights;

import com.booking.DAO;
import com.booking.Identifiable;
import com.booking.ObjectToFileReaderWriter;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightsDAO implements DAO<String, Flight> {
    private Map<String, Flight> flights;
    private ObjectToFileReaderWriter<Map<String, Flight>> objectToFileReaderWriter = new ObjectToFileReaderWriter();

    public FlightsDAO() {

        if (isCollectionExist("./flights.txt")) {

            flights = objectToFileReaderWriter.readObjectFromFile("flights.txt", flights);

        } else {
            CollectionGenerator collectionGenerator = new CollectionGenerator();
            this.flights = collectionGenerator.generateNewFlightsCollection(500, 47);
        }
    }

    //constractor for testing
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
            objectToFileReaderWriter.writeObjectToFile("./flights.txt", flights);
        }
    }

    @Override
    public Collection<Flight> getAll() {
        return flights.values();
    }

    @Override
    public void remove(String id) {
        flights.remove(id);
        objectToFileReaderWriter.writeObjectToFile("./flights.txt", flights);
    }

    public Map<String, Flight> getFlights() {
        return flights;
    }
    /*private void readFlightsCollectionFromFile(String path) {
        ObjectInputStream in;
        try {
            in = new ObjectInputStream(new BufferedInputStream(
                    new FileInputStream(path)));
            this.flights = (Map<String, Flight>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    /*private void writeObjectToFile(String path, Map<String, Flight> flights) {
        FileOutputStream file;
        try {
            file = new FileOutputStream(path);
            ObjectOutputStream data = new ObjectOutputStream(file);
            data.writeObject(flights);
            data.flush();
            data.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}
