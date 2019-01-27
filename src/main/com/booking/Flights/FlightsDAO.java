package com.booking.Flights;

import com.booking.DAO;
import com.booking.Identifiable;
import com.booking.ObjectToFileReaderWriter;

import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class FlightsDAO implements DAO<String, Flight> {
    private Map<String, Flight> flights;
    private ObjectToFileReaderWriter <Map<String, Flight>> objectToFileReaderWriter = new ObjectToFileReaderWriter();

    public FlightsDAO() {

        if (isCollectionExist("./flights.txt")) {

            flights = objectToFileReaderWriter.readObjectFromFile("flights.txt", flights);

        } else {
            CollectionGenerator collectionGenerator = new CollectionGenerator();
            this.flights = collectionGenerator.generateNewFlightsCollection(500, 47);
        }
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

@Override
    public Flight getById(String ID) {
        return flights.get(ID);
    }

    @Override
    public void save(Flight flight) {
        flights.put(flight.id(), flight);
        objectToFileReaderWriter.writeObjectToFile("./flights.txt", flights);
    }

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

    private boolean isCollectionExist(String path) {
        return new File(path).isFile();
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
}
