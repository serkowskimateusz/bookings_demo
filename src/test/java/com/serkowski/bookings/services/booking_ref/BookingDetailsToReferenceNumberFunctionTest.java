package com.serkowski.bookings.services.booking_ref;

import com.serkowski.bookings.custom_validators.ContainerSizeValidator;
import com.serkowski.bookings.custom_validators.TimestampValidator;
import com.serkowski.bookings.domain.BookingDetailsDTO;
import com.serkowski.bookings.domain.BookingDetailsWithTimestamp;
import com.serkowski.bookings.domain.BookingReference;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BookingDetailsToReferenceNumberFunctionTest {

    @Mock
    private BookingsRepository bookingsRepository;
    @Mock
    private BookingRefNumberFunction bookingRefNumberFunction;
    @Mock
    private ContainerSizeValidator containerSizeValidator;
    @Mock
    private TimestampValidator timestampValidator;
    @Mock
    private BookingDetailsDTO bookingDetailsDTO;
    @Mock
    private BookingDetailsWithTimestamp bookingDetailsWithTimeStamp;
    @InjectMocks
    private BookingDetailsToReferenceNumberFunction objectUnderTest;
    @SneakyThrows
    @Test
    public void shouldReturnBookingReferenceGivenExpectedBookingDetails(){
        //given
        given(bookingsRepository.count()).willReturn(Mono.just(1L));
        given(bookingRefNumberFunction.apply(bookingsRepository.count())).willReturn("957000001");
        given(bookingsRepository.save(any())).willReturn(Mono.just(bookingDetailsWithTimeStamp));
        //when
        var actual = objectUnderTest.apply(bookingDetailsDTO);
        //then
        then(actual.toFuture().get()).extracting(BookingReference::getBookingRef).isEqualTo("957000001");
    }
}