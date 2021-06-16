package com.serkowski.bookings.services.booking_ref;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
public class BookingRefNumberFunction implements Function<Mono<Long>, String> {

    public static final int MAXIMUM_BOOKING_REF = 999999;
    public static final String BOOKING_REF_PREFIX = "957";

    @SneakyThrows
    @Override
    public String apply(Mono<Long> lastCounterValue) {
        lastCounterValue.subscribe();
        Long lastValue = lastCounterValue.toFuture().get();
        if (lastValue >= MAXIMUM_BOOKING_REF) {
            throw new BookingRefCounterException("We have problem with bookings. Please contact with our support team.!");
        }
        lastValue++;
        return BOOKING_REF_PREFIX + StringUtils.leftPad(lastValue.toString(), 6, '0');
    }
}