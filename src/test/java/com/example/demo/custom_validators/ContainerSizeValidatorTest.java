package com.example.demo.custom_validators;

import com.example.demo.domain.BookingDetails;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ContainerSizeValidatorTest {

    @InjectMocks
    private ContainerSizeValidator objectUnderTest;

    @Mock
    private BookingDetails bookingDetails;

    @Test
    public void shouldAcceptBookingDetailsAndNotThrowValidatorExceptionGivenExpectedContainerSize() {
        //given
        given(bookingDetails.getContainerSize()).willReturn(20);
        //when
        objectUnderTest.accept(bookingDetails);
        //then
        // no exception
    }
    @Test
    public void shouldThrowValidatorExceptionGivenUnexpectedContainerSize() {
        //given
        given(bookingDetails.getContainerSize()).willReturn(21);
        //when
        Throwable actual = catchThrowable(() -> objectUnderTest.accept(bookingDetails));

        //then
        BDDAssertions.then(actual).isInstanceOf(BookingDetailsValidatorException.class);
        BDDAssertions.then(actual).hasMessage("Container Size should be 20 or 40!");
    }
}