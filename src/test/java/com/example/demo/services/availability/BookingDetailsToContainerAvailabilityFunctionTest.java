package com.example.demo.services.availability;

import com.example.demo.custom_validators.ContainerSizeValidator;
import com.example.demo.domain.AvailableSpace;
import com.example.demo.domain.BookingDetails;
import com.example.demo.domain.ContainerAvailability;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BookingDetailsToContainerAvailabilityFunctionTest {
    @InjectMocks
    private BookingDetailsToContainerAvailabilityFunction objectUnderTest;
    @Mock
    private BookingDetails bookingDetails;
    @Mock
    private AvailabilityConfigurationProperties availabilityConfigurationProperties;
    @Mock
    private ContainerSizeValidator containerSizeValidator;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private AvailableSpace availableSpace;
    @SneakyThrows
    @Test
    public void shouldReturnContainerAvailabilityTrueGivenAvailableSpaceFromExternalService() {
        //given
        given(availabilityConfigurationProperties.getExternalServiceHost()).willReturn("testHost");
        given(restTemplate.postForObject("testHost", bookingDetails, AvailableSpace.class)).willReturn(availableSpace);
        given(availableSpace.getAvailableSpace()).willReturn(5);
        //when
        var actual = objectUnderTest.apply(bookingDetails);
        //then
        then(actual).isInstanceOf(Mono.class);
        then(actual.toFuture().get()).extracting(ContainerAvailability::isAvailable).isEqualTo(true);
    }
    @SneakyThrows
    @Test
    public void shouldReturnContainerAvailabilityFalseGivenAvailableSpace0FromExternalService() {
        //given
        given(availabilityConfigurationProperties.getExternalServiceHost()).willReturn("testHost");
        given(restTemplate.postForObject("testHost", bookingDetails, AvailableSpace.class)).willReturn(availableSpace);
        given(availableSpace.getAvailableSpace()).willReturn(0);
        //when
        var actual = objectUnderTest.apply(bookingDetails);
        //then
        then(actual).isInstanceOf(Mono.class);
        then(actual.toFuture().get()).extracting(ContainerAvailability::isAvailable).isEqualTo(false);
    }
}