package com.serkowski.bookings.custom_validators;

import com.serkowski.bookings.domain.BookingDetailsDTO;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TimestampValidatorTest {

    @InjectMocks
    private TimestampValidator objectUnderTest;
    @Mock
    private BookingDetailsDTO bookingDetailsDTO;
    @Test
    public void shouldAcceptBookingDetailsAndNotThrowExceptionGivenEmptyTimestamp(){
        //given
        given(bookingDetailsDTO.getTimeStamp()).willReturn(StringUtils.EMPTY);
        //when
        objectUnderTest.accept(bookingDetailsDTO);
        //then
        // no exception
    }
    @Test
    public void shouldAcceptBookingDetailsAndNotThrowExceptionGivenExpectedTimestamp(){
        //given
        given(bookingDetailsDTO.getTimeStamp()).willReturn("2020-10-12T13:53:09Z");
        //when
        objectUnderTest.accept(bookingDetailsDTO);
        //then
        // no exception
    }
    @Test
    public void shouldThrowBookingDetailsValidatorExceptionGivenNotExpectedTimestamp(){
        //given
        given(bookingDetailsDTO.getTimeStamp()).willReturn("2020.10.12T13:53:09Z");
        //when
        Throwable actual = catchThrowable(() -> objectUnderTest.accept(bookingDetailsDTO));
        //then
        BDDAssertions.then(actual).isInstanceOf(BookingDetailsValidatorException.class);
        BDDAssertions.then(actual).hasMessage("Timestamp should match pattern: yyyy-MM-dd'T'HH:mm:ssXXX like 2021-06-11T12:11:12Z");
    }

}