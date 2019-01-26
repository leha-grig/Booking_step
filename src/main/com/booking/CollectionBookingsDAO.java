package com.booking;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CollectionBookingsDAO implements BookingsDAO {

    private Map<Flight, List<Booking>> bookings;

    public CollectionBookingsDAO() {

        if (isCollectionExist("./bookings.txt")) {

            readBookingsCollectionFromFile("./bookings.txt");

        } else {
            this.bookings = new HashMap<>();
            writeObjectToFile("./bookings.txt", this.bookings);
        }
    }

    private boolean isCollectionExist(String path) {
        return new File(path).isFile();
    }

    private void readBookingsCollectionFromFile(String path) {
        ObjectInputStream in;
        try {
            in = new ObjectInputStream(new BufferedInputStream(
                    new FileInputStream(path)));
            this.bookings = (Map<Flight, List<Booking>>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void writeObjectToFile(String path, Map<Flight, List<Booking>> bookings) {
        FileOutputStream file;
        try {
            file = new FileOutputStream(path);
            ObjectOutputStream data = new ObjectOutputStream(file);
            data.writeObject(bookings);
            data.flush();
            data.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<Flight, List<Booking>> getAllBookings() {
        return bookings;
    }


    @Override
    public Booking getBookingByID(int ID) {
        List<List<Booking>> bookingsList = new ArrayList<>(bookings.values());
        List<Booking> flattenBookings = bookingsList.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        for (Booking booking : flattenBookings) {
            if (booking.getID() == ID) {
                return booking;
            }
        }
        return null;
    }

    @Override
    public boolean deleteBooking(Booking booking) {
        if (!bookings.containsKey(booking.getFlight())) {
            return false;
        }
        if (bookings.get(booking.getFlight()) == null) {
            return false;
        }
        if (bookings.get(booking.getFlight()).indexOf(booking) < 0) {
            return false;
        }
        bookings.get(booking.getFlight()).remove(booking);
        writeObjectToFile("./bookings.txt", bookings);
        return true;
    }

    @Override
    public boolean deleteBooking(int ID) {
        final boolean[] check = {false};
        bookings.forEach((flight, list) -> {
            final int[] index = {0};
            list.forEach(booking -> {
                if (booking.getID() == ID) {
                    check[0] = true;
                    index[0] = list.indexOf(booking);
                }
            });
            if (check[0]) {
                list.remove(index[0]);
            }
        });
        if (check[0]) {
            writeObjectToFile("./bookings.txt", bookings);
        }
        return check[0];
    }

    @Override
    public void saveBooking(Booking booking) {
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
        writeObjectToFile("./bookings.txt", bookings);
    }
}
