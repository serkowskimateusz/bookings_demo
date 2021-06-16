package com.example.demo;

import com.example.demo.domain.*;
import com.example.demo.services.availability.BookingDetailsToContainerAvailabilityFunction;
import com.example.demo.services.booking_ref.BookingDetailsToReferenceNumberFunction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = BookingsController.class)
public class BookingsControllerIT {
    @MockBean
    private BookingDetailsToContainerAvailabilityFunction bookingDetailsToContainerAvailabilityFunction;
    @MockBean
    private BookingDetailsToReferenceNumberFunction bookingDetailsToReferenceNumberFunction;
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldReturnHttpStatus200AndContainerAvailabilityGivenBookingDetails() {
        //given
        var bookingDetails = new BookingDetails();
        bookingDetails.setContainerSize(20);
        bookingDetails.setContainerType(ContainerType.DRY);
        bookingDetails.setDestination("Kartuzy");
        bookingDetails.setOrigin("Sitno");
        bookingDetails.setQuantity(50);

        given(bookingDetailsToContainerAvailabilityFunction.apply(bookingDetails))
                .willReturn(Mono.just(new ContainerAvailability(true)));
        //when
        webTestClient.post()
                .uri("/api/bookings/containerAvailability")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(bookingDetails))
                .exchange()
                .expectStatus().isOk();


    }

    @Test
    void shouldReturnHttpStatus200AndBookingRefGivenBookingDetails() {
        //given
        BookingDetailsDTO bookingDetailsDTO = new BookingDetailsDTO();
        bookingDetailsDTO.setTimeStamp("2020-10-12T13:53:09Z");
        bookingDetailsDTO.setContainerSize(20);
        bookingDetailsDTO.setContainerType(ContainerType.DRY);
        bookingDetailsDTO.setDestination("Kartuzy");
        bookingDetailsDTO.setOrigin("Sitno");
        bookingDetailsDTO.setQuantity(50);

        given(bookingDetailsToReferenceNumberFunction.apply(bookingDetailsDTO))
                .willReturn(Mono.just(new BookingReference("9780000011")));
        //when
        webTestClient.post()
                .uri("/api/bookings/newBooking")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(bookingDetailsDTO))
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    void shouldReturnBadRequestHttpStatusGivenEmptyBookingDetails() {
        //given
        BookingDetailsDTO bookingDetailsDTO = new BookingDetailsDTO();

        given(bookingDetailsToReferenceNumberFunction.apply(bookingDetailsDTO))
                .willReturn(Mono.just(new BookingReference("9780000011")));
        //when
        webTestClient.post()
                .uri("/api/bookings/newBooking")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(bookingDetailsDTO))
                .exchange()
                .expectStatus().isBadRequest();
    }
}