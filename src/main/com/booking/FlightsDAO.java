package com.booking;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;

public class FlightsDAO {
    private Map<String, Flight> flights;

    public FlightsDAO() {

        if (isCollectionExist()) {

            ObjectInputStream in;
            try {
                in = new ObjectInputStream(new BufferedInputStream(
                        new FileInputStream("flights.txt")));
                this.flights = (Map<String, Flight>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            CollectionGenerator collectionGenerator = new CollectionGenerator();
            this.flights = collectionGenerator.generateNewFlightsCollection(500, 47);
        }
    }


    public Flight getFlightByID(String ID) {
        return flights.get(ID);
    }

    public void saveFlight(Flight flight) {
        flights.replace(flight.getId(), flight);
        FileOutputStream file;
        try {
            file = new FileOutputStream("./flights.txt");
            ObjectOutputStream data = new ObjectOutputStream(file);
            data.writeObject(flights);
            data.flush();
            data.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isCollectionExist() {
        return new File("./flights.txt").isFile();
    }

    public Map<String, Flight> getFlights() {
        return flights;
    }
/*boolean deleteFlight(Booking booking);
    boolean deleteFlight(int index);
    void saveFlight(Booking booking);*/
}
