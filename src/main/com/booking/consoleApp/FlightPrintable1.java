package com.booking.consoleApp;

import com.booking.flights.Flight;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class FlightPrintable1 {
    private DataBase dataBase;
    private final int[] widths = {12, 0, 0};
    private final String DELIMITER="\n";
    private final String header;
    private final String delimiter;

    public FlightPrintable1(DataBase dataBase) {
        this.dataBase =dataBase;

        this.widths[1] = 20;
        this.widths[2] = 15;
        this.header = generateHeader();
        final int totalWidth = this.header.length();

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(delimiter);
        sb.append(DELIMITER);
        sb.append(header);
        sb.append(DELIMITER);
        sb.append(delimiter);
        sb.append(DELIMITER);
        dataBase.items().forEach(new Consumer<Flight>() {
            @Override
            public void accept(Flight flight) {
                sb.append(String.format(
                        format(),
                        flight.id(), flight.getDateTime(), flight.getDepartureCity(), flight.getDestination()
                ));
                sb.append(DELIMITER);
            }
        });
        sb.append(delimiter);
        sb.append(DELIMITER);
        return sb.toString();
    }
}
