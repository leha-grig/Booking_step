package main.com.booking;
import java.time.LocalDate;
import java.util.List;

public class BookingController {

    private BookingsService bookingsService;

    public Booking createBooking(String flight, LocalDate date, String name, String surname) {
        return bookingsService.createBooking(flight, date, name, surname);
    }

    public Booking updateBooking(LocalDate date, int ID) {
        return bookingsService.updateBooking(date, ID);
    }

    public boolean deleteBookingByID(int ID) {
        return bookingsService.deleteBookingByID(ID);
    }

    public List<Booking> showSelectedBookings(String name, String surname, int ID) {
        return bookingsService.showSelectedBookings(name, surname, ID);
    }

}