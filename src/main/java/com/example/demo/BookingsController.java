package com.example.demo;

import com.example.demo.domain.BookingDetails;
import com.example.demo.domain.BookingDetailsDTO;
import com.example.demo.domain.BookingReference;
import com.example.demo.domain.ContainerAvailability;
import com.example.demo.services.availability.BookingDetailsToContainerAvailabilityFunction;
import com.example.demo.services.booking_ref.BookingDetailsToReferenceNumberFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bookings")
public class BookingsController {

    private final BookingDetailsToContainerAvailabilityFunction bookingDetailsToContainerAvailabilityFunction;
    private final BookingDetailsToReferenceNumberFunction bookingDetailsToReferenceNumberFunction;

    @PostMapping("/containerAvailability")
    public Mono<ContainerAvailability> containerAvailability(@Validated @RequestBody BookingDetails bookingDetails) {
        return bookingDetailsToContainerAvailabilityFunction.apply(bookingDetails);
    }

    @PostMapping("/newBooking")
    public Mono<BookingReference> newBooking(@Validated @RequestBody BookingDetailsDTO bookingDetailsDTO) {
        return bookingDetailsToReferenceNumberFunction.apply(bookingDetailsDTO);
    }
}