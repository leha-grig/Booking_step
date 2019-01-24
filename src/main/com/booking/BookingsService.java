package main.com.booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingsService {
   
    private BookingsDAO bookingDAO;

    public Booking createBooking(String flight, LocalDate date, String name, String surname) {
        //ID должен автоматически генерироваться с помощью flight.hashCode()
        Booking booking = new Booking(flight, date, name, surname, flight.hashCode());
        bookingDAO.saveBooking(booking);
        return bookingDAO.getBookingByID(booking.getID());
    }

    public Booking updateBooking(LocalDate date, int ID) {
        Booking booking = bookingDAO.getBookingByID(ID);
        booking.setDate(date);
        bookingDAO.saveBooking(booking);
        return bookingDAO.getBookingByID(booking.getID());
    }

    public boolean deleteBookingByID(int ID) {
        return bookingDAO.deleteBooking(ID);
    }

    public List<Booking> showSelectedBookings(String name, String surname, int ID) {
        List<Booking> bookings = new ArrayList<>();
        for (Booking booking : bookingDAO.getAllBookings()) {
            if (booking.getName().equals(name)
                    && booking.getSurname().equals(surname)
                    && booking.getID() == ID) {
                bookings.add(booking);
            }
        }
        return bookings;
    }

}
