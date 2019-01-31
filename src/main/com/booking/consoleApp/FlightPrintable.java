package com.booking.consoleApp;

import com.booking.flights.Flight;

import java.util.Map;
import java.util.stream.Stream;

public class FlightPrintable {
    private Map<String, Flight> flights;
    private final int[] widths = {12, 0, 0};
    private final String header;
    private final String delimiter;

    public FlightPrintable( final Map<String, Flight> flights) {

        this.flights = flights;
        this.widths[1] = 20;
        this.widths[2] = 15;
        this.header = generateHeader();
        final int totalWidth = this.header.length();
        System.out.println(totalWidth);
        this.delimiter = Stream.generate(()->"-").limit(totalWidth).reduce("",(a, b)->(a+b));
    }

    private String format0(int w1, int w2, int w3) {
        return "%-" + w1 + "s %-" + w2 + "s %-" + w3 + "s-> %s";
    }

    private String format() {
        return format0(widths[0],widths[1],widths[2]);
    }

    private String generateHeader() {
        return String.format(format(), "FlightNo", "Date && Time", "From", "Destination");
    }

    private void printLine() {
        System.out.println(delimiter);
    }

    private void printHeader() {
        System.out.println(this.header);
    }

    private void printBody() {
        flights.forEach((s, tte) -> System.out.printf(format()+"\n",
                tte.id(),
                tte.getDateTime(),
                tte.getDepartureCity(),
                tte.getDestination()));
    }

    public void print() {
        printLine();
        printHeader();
        printLine();
        printBody();
        printLine();
    }

}
