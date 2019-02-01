package com.booking.consoleApp;


import com.booking.DAO.CollectionBookingsDAO;
import com.booking.DAO.FlightsDAO;
import com.booking.Exceptions.BookingAlreadyExist;
import com.booking.objects.Booking;
import com.booking.objects.Flight;
import com.booking.services.BookingController;
import com.booking.services.BookingsService;
import com.booking.services.FlightController;
import com.booking.services.FlightsService;

import java.util.List;

public class CmdCreateBooking implements Command {
    CheckingMethods cm =new CheckingMethods();
    private final FlightsService fs = new FlightsService(new FlightsDAO());
    private final FlightController fc = new FlightController(fs);
    private CollectionBookingsDAO bookingsDAO = new CollectionBookingsDAO();
    private BookingsService bookingsService = new BookingsService(bookingsDAO, fs);
    private BookingController bc = new BookingController(bookingsService);



    @Override
    public void doCommand() {
        String dest = cm.checkInputString("Enter destination!");
        String date = cm.checkDate();
        int numberOfPeople = cm.getCorrectNumber("Enter number of people!");
        List<Flight> list = fc.showSelectedFlights(dest, date, numberOfPeople);
        System.out.println("To proceed booking, please, enter the Num of flight from the list above or press zero to exit!");
        int numberOfFlight = cm.checkNumberOfFlight(list);

        if (numberOfFlight == 0) {
            System.out.println("You are back in the main menu!");
            return;
        }

        for (int i = 1; i <= numberOfPeople; i++) {

            String name = cm.checkInputString("Enter name of the " + i + " passenger");

            String surname = cm.checkInputString("Enter surname of the " + i + " passenger");
            try {
                Booking booking = bc.createBooking(list.get(numberOfFlight - 1), name, surname);
                System.out.println("The new booking was created: " + booking);
            } catch (BookingAlreadyExist bookingAlreadyExist) {
                System.out.println(bookingAlreadyExist.getMessage());
            }
        }

    }
    @Override
    public String text() {
        return "SEARCH FLIGHT AND BOOKING";
    }
}
