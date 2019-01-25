package com.booking;

import java.io.IOException;
import java.util.List;
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
            choose = sc.nextInt();
            switch(choose){
                case 1: flightsService.showFlightsFor24hours();
                    continue outerLoop;

                case 2:
                    System.out.println("Enter ID of flight!");
                    String number = scanner.nextLine();
                    flightsService.showFlightByID(number);
                    continue outerLoop;
                case 3:
                    System.out.println("Enter desination");
                    String dest = scanner.nextLine();
                    System.out.println("Enter date");
                    String date = scanner.nextLine();
                    System.out.println("Enter number of people!");
                    String numberOfPeople =scanner.nextLine();
                    List<Flight> licts = flightsService.showSelectedFlights(dest,date,Integer.parseInt(numberOfPeople));
                    System.out.println(licts);
                    System.out.println("If you chose something enter it number of flight or press zero to exit!");
                    int numberOfFlight = sc.nextInt();
                    for (int i = 0; i < licts.size()-1; i++) {
                        if(licts.get(i).equals(numberOfFlight - 1)){
                            System.out.println("Enter your name!");
                            String name = scanner.nextLine();
                            System.out.println("Enter your surname!");
                            String surname = scanner.nextLine();
                            bookingsServise.createBooking(licts.get(i), name, surname);
                            System.out.println(bookingsServise.createBooking(licts.get(i), name, surname));
                        } else if(numberOfFlight == 0){
                            System.out.println("You back in main menu!");
                        } else {
                            System.out.println("I did not find such number!");
                            numberOfFlight = sc.nextInt();
                            continue;
                        }

                    }

                    continue outerLoop;
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
