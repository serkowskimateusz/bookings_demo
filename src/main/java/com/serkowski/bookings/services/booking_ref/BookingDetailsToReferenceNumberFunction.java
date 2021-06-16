package com.serkowski.bookings.services.booking_ref;

import com.serkowski.bookings.custom_validators.ContainerSizeValidator;
import com.serkowski.bookings.custom_validators.TimestampValidator;
import com.serkowski.bookings.domain.BookingDetailsDTO;
import com.serkowski.bookings.domain.BookingDetailsWithTimestamp;
import com.serkowski.bookings.domain.BookingReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingDetailsToReferenceNumberFunction implements Function<BookingDetailsDTO, Mono<BookingReference>> {

    private final BookingsRepository bookingsRepository;
    private final BookingRefNumberFunction bookingRefNumberFunction;
    private final ContainerSizeValidator containerSizeValidator;
    private final TimestampValidator timestampValidator;

    @Override
    public Mono<BookingReference> apply(BookingDetailsDTO bookingDetailsDTO) {
        containerSizeValidator.accept(bookingDetailsDTO);
        timestampValidator.accept(bookingDetailsDTO);
        String nextId = bookingRefNumberFunction.apply(bookingsRepository.count());
        var bookingDetails = BookingDetailsWithTimestamp.builder()
                .containerSize(bookingDetailsDTO.getContainerSize())
                .containerType(bookingDetailsDTO.getContainerType())
                .origin(bookingDetailsDTO.getOrigin())
                .destination(bookingDetailsDTO.getDestination())
                .quantity(bookingDetailsDTO.getQuantity())
                .timeStamp(bookingDetailsDTO.getTimeStamp())
                .id(nextId)
                .build();
        Mono<BookingDetailsWithTimestamp> save = bookingsRepository.save(bookingDetails);
        save.subscribe(x-> log.info("Saved new booking in database: " + bookingDetails) );
        return Mono.justOrEmpty(new BookingReference(nextId));
    }
}