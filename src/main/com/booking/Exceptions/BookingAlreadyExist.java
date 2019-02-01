package com.booking.Exceptions;

import java.io.IOException;

public class BookingAlreadyExist extends RuntimeException {
    public BookingAlreadyExist(String s) {
        super(s);
    }
}
