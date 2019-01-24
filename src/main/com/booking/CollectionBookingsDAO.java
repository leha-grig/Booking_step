package main.com.booking;

import java.util.List;

public class CollectionBookingsDAO implements BookingsDAO {

    private List<Booking> bookings;

    public CollectionBookingsDAO(List<Booking> bookings) {
        this.bookings = bookings;
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
        int index = 0;
        for (Booking booking : bookings) {
            if (booking.getID() == ID) {
                index = bookings.indexOf(booking);
            }
        }
        return bookings.get(index);
    }

    @Override
    public boolean deleteBooking(Booking booking) {
        return bookings.remove(booking);
    }

    @Override
    public boolean deleteBooking(int ID) {
        for (Booking booking : bookings) {
            if (booking.getID() != ID) {
                return false;
            } else {
                bookings.removeIf(bookingItem -> bookingItem.getID() == ID);
            }
        }
        return true;
    }

    @Override
    public void saveBooking(Booking booking) {
        if (bookings.indexOf(booking) == -1) {
            bookings.add(booking);
        } else {
            bookings.set(bookings.indexOf(booking), booking);
        }
    }
}
