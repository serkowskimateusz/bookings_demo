package com.example.demo.custom_validators;

public class BookingDetailsValidatorException extends RuntimeException {
    public BookingDetailsValidatorException(String message) {
        super(message);
    }
}