package com.booking;

import java.util.List;
import java.util.Map;

public interface   FlightsDAO {
   /* private  Map<String, Flight> flights;

    public FlightsDAO() {
        if (isCollectionExist()){
            this.flights = вытащить коллекцию из файла
        } else {
            CollectionGenerator collectionGenerator = new CollectionGenerator();
            this.flights = collectionGenerator.generateNewFlightsCollection();
        }
    }
*/
    Map<String, Flight> getAllFlights();
    Flight getFlightByIndex(int index);
    Flight getFlightByID(String ID);
    boolean isCollectionExist(); // private


    /*boolean deleteFlight(Booking booking);
    boolean deleteFlight(int index);
    void saveFlight(Booking booking);*/
}
