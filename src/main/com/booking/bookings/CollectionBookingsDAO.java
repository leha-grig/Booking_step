package com.booking.bookings;

import com.booking.DAO;
import com.booking.flights.Flight;
import com.booking.ObjectToFileReaderWriter;
import com.booking.logger.BookingServiceLogger;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CollectionBookingsDAO implements DAO<Integer, Booking> {

    private Map<Flight, List<Booking>> bookings;
    private ObjectToFileReaderWriter<Map<Flight, List<Booking>>> objectToFileReaderWriter = new ObjectToFileReaderWriter();
    BookingServiceLogger logger = new BookingServiceLogger();
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
        logger.info("Get all information about Bookings");
        return bookings.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }


    @Override
    public Booking getById(Integer ID) {

        for (Booking booking : getAll()) {
            if (booking.id().equals(ID)) {
                logger.info("Get Booking information by ID");
                return booking;
            }
        }
        logger.error("Booking isn't exist");
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
                logger.info("Remove booking");
            }
        });
        if (check[0]) {
            objectToFileReaderWriter.writeObjectToFile("./bookings.txt", bookings);
            logger.info("Booking was removed from ./bookings.txt path");
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
        logger.info("Booking was added to ./bookings.txt path");
    }

}
