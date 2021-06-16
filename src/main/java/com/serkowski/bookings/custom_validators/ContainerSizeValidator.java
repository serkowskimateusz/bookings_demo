package com.serkowski.bookings.custom_validators;


import com.serkowski.bookings.domain.BookingDetails;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class ContainerSizeValidator implements Consumer<BookingDetails> {
    @Override
    public void accept(BookingDetails bookingDetails) {
        if (bookingDetails.getContainerSize() != 20 && bookingDetails.getContainerSize() != 40) {
            throw new BookingDetailsValidatorException("Container Size should be 20 or 40!");
        }
    }
}
