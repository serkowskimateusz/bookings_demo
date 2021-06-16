package com.serkowski.bookings.services.availability;

import com.serkowski.bookings.domain.ContainerAvailability;
import com.serkowski.bookings.domain.BookingDetails;
import com.serkowski.bookings.domain.AvailableSpace;
import com.serkowski.bookings.custom_validators.ContainerSizeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class BookingDetailsToContainerAvailabilityFunction implements Function<BookingDetails, Mono<ContainerAvailability>> {

    private final RestTemplate restTemplate;
    private final AvailabilityConfigurationProperties availabilityConfigurationProperties;
    private final ContainerSizeValidator containerSizeValidator;

    @Override
    public Mono<ContainerAvailability> apply(BookingDetails bookingDetails) {
        containerSizeValidator.accept(bookingDetails);
        var space = restTemplate.postForObject(availabilityConfigurationProperties.getExternalServiceHost(), bookingDetails, AvailableSpace.class);
        Assert.notNull(space, "External service should return available space");

        var availability =  space.getAvailableSpace() == 0 ?
                new ContainerAvailability(false) : new ContainerAvailability(true);

        return Mono.justOrEmpty(availability);
    }
}