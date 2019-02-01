package com.booking.Exceptions;



public class BookingAlreadyExist extends RuntimeException {
    public BookingAlreadyExist(String s) {
        super(s);
    }
}
