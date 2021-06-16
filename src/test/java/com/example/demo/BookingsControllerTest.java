package com.example.demo;

import com.example.demo.domain.BookingDetails;
import com.example.demo.domain.BookingDetailsDTO;
import com.example.demo.domain.BookingReference;
import com.example.demo.domain.ContainerAvailability;
import com.example.demo.services.availability.BookingDetailsToContainerAvailabilityFunction;
import com.example.demo.services.booking_ref.BookingDetailsToReferenceNumberFunction;
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