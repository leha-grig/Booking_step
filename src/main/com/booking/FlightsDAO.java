package com.booking;

import java.io.*;
import java.util.Map;

public class FlightsDAO {
    private Map<String, Flight> flights;

    public FlightsDAO() {

        if (isCollectionExist()) {

            ObjectInputStream in = null;
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


    public Flight getFlightByID(String ID){
        return flights.get(ID);
    };

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
