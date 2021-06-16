package com.serkowski.bookings.custom_validators;

public class BookingDetailsValidatorException extends RuntimeException {
    public BookingDetailsValidatorException(String message) {
        super(message);
    }
}