package com.booking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CollectionBookingsDAO implements BookingsDAO {

    private List<Booking> bookings;

    public CollectionBookingsDAO(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public CollectionBookingsDAO() {

        if (isCollectionExist("./bookings.txt")) {

            readBookingsCollectionFromFile("./bookings.txt");

        } else {
            this.bookings = new ArrayList<>();
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
            this.bookings = (List<Booking>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void writeObjectToFile(String path, List<Booking> bookings) {
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
    public List<Booking> getAllBookings() {
        return bookings;
    }

    @Override
    public Booking getBookingByIndex(int index) {
        if (index < 0 || index >= bookings.size()) return null;
        else return bookings.get(index);
    }

    @Override
    public Booking getBookingByID(int ID) {

        for (Booking booking : bookings) {
            if (booking.getID() == ID) {
                return booking;
            }
        }
        return null;
    }

    @Override
    public boolean deleteBooking(Booking booking) {
        if (bookings.indexOf(booking) < 0){
            return false;
        }
        bookings.remove(booking);
        writeObjectToFile("./bookings.txt", bookings);
        return true;
    }

    @Override
    public boolean deleteBooking(int ID) {
        boolean check = bookings.removeIf(bookingItem -> bookingItem.getID() == ID);
        if (check){
            writeObjectToFile("./bookings.txt", bookings);
            return true;
        }
        return false;
    }

    @Override
    public void saveBooking(Booking booking) {
        if (bookings.indexOf(booking) < 0) {
            bookings.add(booking);
        } else {
            bookings.set(bookings.indexOf(booking), booking);
        }
        writeObjectToFile("./bookings.txt", bookings);
    }
}
