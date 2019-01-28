package com.booking.bookings;

import com.booking.DAO;
import com.booking.flights.Flight;
import com.booking.ObjectToFileReaderWriter;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CollectionBookingsDAO implements DAO<Integer, Booking> {

    private Map<Flight, List<Booking>> bookings;
    private ObjectToFileReaderWriter<Map<Flight, List<Booking>>> objectToFileReaderWriter = new ObjectToFileReaderWriter();

    public CollectionBookingsDAO() {

        if (isCollectionExist("./bookings.txt")) {

            bookings = objectToFileReaderWriter.readObjectFromFile("./bookings.txt", bookings);

        } else {
            this.bookings = new HashMap<>();
            objectToFileReaderWriter.writeObjectToFile("./bookings.txt", this.bookings);
        }
    }

    // Constructor for tests
    public CollectionBookingsDAO(Booking booking1, Booking booking2) {
        Map <Flight, List<Booking>> bookings = new HashMap<>();
        bookings.put(booking1.getFlight(), new ArrayList<>(Arrays.asList(booking1)));
        bookings.put(booking2.getFlight(), new ArrayList<>(Arrays.asList(booking2)));
        this.bookings = bookings;
    }

    private boolean isCollectionExist(String path) {
        return new File(path).isFile();
    }

    public Map<Flight, List<Booking>> getBookings() {
        return bookings;
    }

    @Override
    public Collection<Booking> getAll() {
        return bookings.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }


    @Override
    public Booking getById(Integer ID) {
        /*List<List<Booking>> bookingsList = new ArrayList<>(bookings.values());
        List<Booking> flattenBookings = bookingsList.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());*/
        for (Booking booking : getAll()) {
            if (booking.id().equals(ID)) {
                return booking;
            }
        }
        return null;
    }

    @Override
    public void remove(Integer ID) {
        final boolean[] check = {false};
        bookings.forEach((flight, list) -> {
            final int[] index = {0};
            list.forEach(booking -> {
                if (booking.id().equals(ID)) {
                    check[0] = true;
                    index[0] = list.indexOf(booking);
                }
            });
            if (check[0]) {
                list.remove(index[0]);
//                objectToFileReaderWriter.writeObjectToFile("./bookings.txt", bookings);
            }
        });
        if (check[0]) {
            objectToFileReaderWriter.writeObjectToFile("./bookings.txt", bookings);
        }
    }

    @Override
    public void save(Booking booking) {
        if (bookings.get(booking.getFlight()) == null) {
            bookings.put(booking.getFlight(), new ArrayList<>(Arrays.asList(booking)));
        } else if (bookings.get(booking.getFlight()).indexOf(booking) < 0) {
            List<Booking> newList = bookings.get(booking.getFlight());
            newList.add(booking);
            bookings.replace(booking.getFlight(), newList);
        } else {
            List<Booking> newList = bookings.get(booking.getFlight());
            newList.set(newList.indexOf(booking), booking);
            bookings.replace(booking.getFlight(), newList);
        }
        objectToFileReaderWriter.writeObjectToFile("./bookings.txt", bookings);
    }

}
