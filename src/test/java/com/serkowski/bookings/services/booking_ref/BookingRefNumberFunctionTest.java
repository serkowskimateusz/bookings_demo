package com.serkowski.bookings.services.booking_ref;

import lombok.SneakyThrows;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@ExtendWith(MockitoExtension.class)
class BookingRefNumberFunctionTest {
    @InjectMocks
    private BookingRefNumberFunction objectUnderTest;
    @SneakyThrows
    @Test
    public void shouldReturnNextBookingRefGivenExpectedCounterValue(){
        //given
        var lastCounterValue = Mono.just(2L);
        //when
        var actual = objectUnderTest.apply(lastCounterValue);
        //then
        then(actual).isEqualTo("957000003");
    }
    @Test
    public void shouldThrowBookingRefCounterExceptionGivenExceededCounterValue(){
        //given
        var lastCounterValue = Mono.just(1000000L);
        //when
        Throwable actual = catchThrowable(() -> objectUnderTest.apply(lastCounterValue));
        //then
        BDDAssertions.then(actual).isInstanceOf(BookingRefCounterException.class);
        BDDAssertions.then(actual).hasMessage("We have problem with bookings. Please contact with our support team.!");
    }
}