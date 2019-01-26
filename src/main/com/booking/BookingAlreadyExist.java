package com.booking;

import java.io.IOException;

public class BookingAlreadyExist extends IOException {
    public BookingAlreadyExist(String s) {
        super(s);
    }
}
