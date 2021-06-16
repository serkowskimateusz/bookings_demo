package com.serkowski.bookings.custom_validators;

import com.serkowski.bookings.domain.BookingDetailsDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.function.Consumer;

@Component
public class TimestampValidator implements Consumer<BookingDetailsDTO> {

    public static final String ISO_8601_UTC_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ssXXX";

    @Override
    public void accept(BookingDetailsDTO bookingDetails) {
        if(StringUtils.isEmpty(bookingDetails.getTimeStamp())) {
            return;
        }
        try {
            new SimpleDateFormat(ISO_8601_UTC_DATE_PATTERN).parse(bookingDetails.getTimeStamp());
        } catch (ParseException e) {
            throw new BookingDetailsValidatorException("Timestamp should match pattern: " + ISO_8601_UTC_DATE_PATTERN + " like 2021-06-11T12:11:12Z");
        }
    }
}