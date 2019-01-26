package com.booking;

import java.io.*;
import java.util.Map;

public class FlightsDAO {
    private Map<String, Flight> flights;

    public FlightsDAO() {

        if (isCollectionExist("./flights.txt")) {

            readFlightsCollectionFromFile("flights.txt");

        } else {
            CollectionGenerator collectionGenerator = new CollectionGenerator();
            this.flights = collectionGenerator.generateNewFlightsCollection(500, 47);
        }
    }

    private void readFlightsCollectionFromFile(String path) {
        ObjectInputStream in;
        try {
            in = new ObjectInputStream(new BufferedInputStream(
                    new FileInputStream(path)));
            this.flights = (Map<String, Flight>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public Flight getFlightByID(String ID) {
        return flights.get(ID);
    }

    public void saveFlight(Flight flight) {
        flights.replace(flight.getId(), flight);
        writeObjectToFile("./flights.txt", flights);
    }

    private void writeObjectToFile(String path, Map<String, Flight> flights) {
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
    }

    private boolean isCollectionExist(String path) {
        return new File(path).isFile();
    }

    public Map<String, Flight> getFlights() {
        return flights;
    }
/*boolean deleteFlight(Booking booking);
    boolean deleteFlight(int index);
    void saveFlight(Booking booking);*/
}
