package com.booking;

import com.booking.Flights.Flight;

import java.util.Map;

public class PrintAllLine {
    private Map<Integer, String> cities;
    private Map<String, Flight> listOfsits;
    private int[] widths = {8,0,0};
    private String header;
    private String delimiter;

    /*public PrintAllLine(final Cities cities.txt, final Map<String, Flight> listOfsits) {
        this.cities.txt = cities.txt.data();
        this.listOfsits = listOfsits;
        this.widths[1] = cities.txt.maxLength();
        this.widths[2] = cities.txt.maxLength();
        this.header = generateHeader();
        final int totalWidth = this.header.length();
        System.out.println(totalWidth);
        this.delimiter = Stream.generate(()->"-")
                .limit(totalWidth)
                .reduce("", (a,b)->(a+b));
    }

    private String generateHeader() {
        return String.format(format(),"No","FROM","TO","TIME");
    }

    private String format() {
        return formatF(widths[0],widths[1],widths[2]);
    }

    private String formatF(int w1, int w2, int w3) {
        return "%-" + w1 + "s %-" + w2 + "s %-" + w3 + "s %s";
    }
    private void printLine(){
        System.out.println(delimiter);
    }
    private void printHeader() {
        System.out.println(this.header);
    }
    private void printBody(){
        listOfsits.forEach((s,tte)-> System.out.println(format()+"\n",
                tte.getId(),
                cities.txt.get(tte.getBookedSits()).getName(),
                cities.txt.get(tte.getCapasity()).getName()
        );
    }
    public void print(){
        printLine();
        printHeader();
        printLine();
        printBody();
        printLine();
    }*/
}
