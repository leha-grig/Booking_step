package com.booking.bookings;

import com.booking.Constants;
import com.booking.DAO;
import com.booking.Exceptions.FileReadingException;
import com.booking.flights.Flight;
import com.booking.ObjectToFileReaderWriter;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CollectionBookingsDAO implements DAO<Integer, Booking> {

    private Map<Flight, List<Booking>> bookings;
    private ObjectToFileReaderWriter<Map<Flight, List<Booking>>> objectToFileReaderWriter = new ObjectToFileReaderWriter();

    public CollectionBookingsDAO() {

        if (isCollectionExist(Constants.bookingsPath)) {

            try {
                bookings = objectToFileReaderWriter.readObjectFromFile(Constants.bookingsPath, bookings);
            } catch (FileReadingException e) {
                System.out.println(e.getMessage());
                this.bookings = new HashMap<>();
                objectToFileReaderWriter.writeObjectToFile(Constants.bookingsPath, this.bookings);
            }

        } else {
            this.bookings = new HashMap<>();
            objectToFileReaderWriter.writeObjectToFile(Constants.bookingsPath, this.bookings);
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
            final int[] bookingIndexInList = {0};
            list.forEach(booking -> {
                if (booking.id().equals(ID)) {
                    check[0] = true;
                    bookingIndexInList[0] = list.indexOf(booking);
                }
            });
            if (check[0]) {
                list.remove(bookingIndexInList[0]);
            }
        });
        if (check[0]) {
            objectToFileReaderWriter.writeObjectToFile(Constants.bookingsPath, bookings);
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
        objectToFileReaderWriter.writeObjectToFile(Constants.bookingsPath, bookings);
    }

}
