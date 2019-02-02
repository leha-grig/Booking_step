package com.booking.Exceptions;

public class IncorrectLoginPasswordException extends Exception{
    public IncorrectLoginPasswordException(String s){
        super(s);
    }
}
