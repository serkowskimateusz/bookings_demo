package com.serkowski.bookings;

import com.serkowski.bookings.domain.BookingDetails;
import com.serkowski.bookings.domain.BookingDetailsDTO;
import com.serkowski.bookings.domain.BookingReference;
import com.serkowski.bookings.domain.ContainerAvailability;
import com.serkowski.bookings.services.availability.BookingDetailsToContainerAvailabilityFunction;
import com.serkowski.bookings.services.booking_ref.BookingDetailsToReferenceNumberFunction;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BookingsControllerTest {
    @Mock
    private BookingDetailsToContainerAvailabilityFunction bookingDetailsToContainerAvailabilityFunction;
    @Mock
    private BookingDetailsToReferenceNumberFunction bookingDetailsToReferenceNumberFunction;
    @Mock
    private BookingDetails bookingDetails;
    @Mock
    private BookingDetailsDTO bookingDetailsDTO;
    @Mock
    private Mono<ContainerAvailability> containerAvailability;
    @Mock
    private Mono<BookingReference> bookingReferenceMono;
    @InjectMocks
    private BookingsController objectUnderTest;
    @SneakyThrows
    @Test
    public void shouldReturnContainerAvailabilityGivenBookingDetails(){
        //given
        given(bookingDetailsToContainerAvailabilityFunction.apply(bookingDetails)).willReturn(containerAvailability);
        //when
        var actual = objectUnderTest.containerAvailability(bookingDetails);
        //then
        then(actual).isInstanceOf(Mono.class);
    }
    @Test
    public void shouldReturnBookingReferenceGivenBookingDetailsDTO(){
        //given
        given(bookingDetailsToReferenceNumberFunction.apply(bookingDetailsDTO)).willReturn(bookingReferenceMono);
        //when
        var actual = objectUnderTest.newBooking(bookingDetailsDTO);
        //then
        then(actual).isInstanceOf(Mono.class);
    }
}