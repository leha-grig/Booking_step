package com.booking;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Console {
// List<Flight> generateFlights; // при первом запуске программы сгенерировать рейсы
    /*{
        FlightsDAO flightsDAO = new FlightsDAO();


    }*/

    private String[] options = {
            "Show all flights for 24hours", "Show flith by ID",
            "Fyight search and booking", "Cancel reservation",
            "My flights", "EXIT"};
    private FlightsDAO dao = new FlightsDAO();
    private FlightsService flightsService = new FlightsService(dao);
    private BookingsService bookingsServise;
    private Scanner scanner =new Scanner(System.in);

    public void chooseCommand(Scanner sc) {
        //displayChooseItem(options);
        System.out.println("Enter number of command!");
        //int choose;
        outerLoop:
        while(true){
            displayChooseItem(options);
            int choose;
            String number;

            choose = sc.nextInt();
            switch(choose){
                case 1: flightsService.showFlightsFor24hours();
                    continue outerLoop;

                case 2:
                    System.out.println("Enter ID of flight!");
                    number = scanner.nextLine();
                    flightsService.showFlightByID(number);
                    continue outerLoop;
                case 3:
                    System.out.println("Enter desination");
                    String dest = scanner.nextLine();
                    System.out.println("Enter date");
                    String date = scanner.nextLine();
                    System.out.println("Enter number of people!");
                    String numberOfPeople =scanner.nextLine();
                    flightsService.showSelectedFlights(dest,date,Integer.parseInt(numberOfPeople));
                    System.out.println("If you chose something enter ID of flight or press zero to exit!");
                    String numberOfFlight = sc.nextLine();
                    if(numberOfFlight.equals(flightsService.showFlightByID(numberOfFlight).getId())){
                        //bookingsServise.createBooking(createReservation(),"SAcsa","Petrov");

                        System.out.println("Bax");
                    } else{

                    System.out.println("3");
                    continue outerLoop;}
                case 4:
                    System.out.println("Enter reservation number!");
                    number = scanner.nextLine();
                    bookingsServise.deleteBookingByID(Integer.parseInt(number)-1);
                    continue outerLoop;
                case 5:
                    System.out.println("Enter your name!");
                    String name = scanner.nextLine();
                    System.out.println("Enter your surname!");
                    String surname = scanner.nextLine();
                    bookingsServise.showSelectedBookings(name,surname);
                    continue outerLoop;
                case 6:
                    System.out.println("EXIT"); break outerLoop;
                    //return;
                    default:
                        System.out.println("Enter number from 1 to 7!");
                        //continue;
        }
    }
}

    private void createReservation() {
        System.out.println("ax");
    }


    private void displayChooseItem(String[] options){
        int number = 0;
        for (String option: options) {
            System.out.println((number+1)+":"+option);
            ++number;
        }
    }

}
