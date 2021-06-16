package com.serkowski.bookings;

import com.datastax.oss.driver.api.core.metadata.EndPoint;
import com.datastax.oss.driver.api.core.metadata.Node;
import com.serkowski.bookings.custom_validators.BookingDetailsValidatorException;
import com.serkowski.bookings.services.booking_ref.BookingRefCounterException;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.codec.DecodingException;
import org.springframework.data.cassandra.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
class BookingsControllerAdviceTest {
    @Mock
    private EndPoint endPoint;
    @Mock
    private Map<Node, Throwable> map;

    @InjectMocks
    private BookingsControllerAdvice objectUnderTest;

    @Test
    void shouldHandleResourceAccessException() {
        // given
        var e = new ResourceAccessException("testMessage");
        // when
        var actual = objectUnderTest.handleResourceAccessException(e);
        // then
        BDDAssertions.then(actual)
                .extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
                .containsExactly(HttpStatus.INTERNAL_SERVER_ERROR,
                        "We have problem with our booking system. Please try one more time or contact with our support team . Error: testMessage");
    }

    @Test
    void shouldHandleBookingDetailsValidatorException() {
        // given
        var e = new BookingDetailsValidatorException("testMessage");
        // when
        var actual = objectUnderTest.handleBookingDetailsValidatorException(e);
        // then
        BDDAssertions.then(actual)
                .extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
                .containsExactly(HttpStatus.BAD_REQUEST, "testMessage");
    }

    @Test
    void shouldHandleDecodingException() {
        // given
        var e = new DecodingException("testMessage");
        // when
        var actual = objectUnderTest.handleDecodingException(e);
        // then
        BDDAssertions.then(actual)
                .extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
                .containsExactly(HttpStatus.BAD_REQUEST, "testMessage");
    }

    @Test
    void shouldHandleBookingRefCounterException() {
        // given
        var e = new BookingRefCounterException("testMessage");
        // when
        var actual = objectUnderTest.handleBookingRefCounterException(e);
        // then
        BDDAssertions.then(actual)
                .extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
                .containsExactly(HttpStatus.INTERNAL_SERVER_ERROR, "testMessage");
    }

    @Test
    void shouldHandleCassandraCassandraUncategorizedException() {
        // given
        var e = new CassandraUncategorizedException("testMessage", new Throwable());
        // when
        var actual = objectUnderTest.handleCassandraException(e);
        // then
        BDDAssertions.then(actual)
                .extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
                .containsExactly(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry there was a problem processing your request");
    }

    @Test
    void shouldHandleCassandraAuthenticationException() {
        // given
        var e = new CassandraAuthenticationException(endPoint,"testMessage", new Throwable());
        // when
        var actual = objectUnderTest.handleCassandraException(e);
        // then
        BDDAssertions.then(actual)
                .extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
                .containsExactly(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry there was a problem processing your request");
    }

    @Test
    void shouldHandleCassandraConnectionFailureException() {
        // given
        var e = new CassandraConnectionFailureException(map,"testMessage", new Throwable());
        // when
        var actual = objectUnderTest.handleCassandraException(e);
        // then
        BDDAssertions.then(actual)
                .extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
                .containsExactly(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry there was a problem processing your request");
    }

    @Test
    void shouldHandleCassandraInsufficientReplicasAvailableException() {
        // given
        var e = new CassandraInsufficientReplicasAvailableException("testMessage");
        // when
        var actual = objectUnderTest.handleCassandraException(e);
        // then
        BDDAssertions.then(actual)
                .extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
                .containsExactly(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry there was a problem processing your request");
    }

    @Test
    void shouldHandleCassandraInternalException() {
        // given
        var e = new CassandraInternalException("testMessage");
        // when
        var actual = objectUnderTest.handleCassandraException(e);
        // then
        BDDAssertions.then(actual)
                .extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
                .containsExactly(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry there was a problem processing your request");
    }

    @Test
    void shouldHandleCassandraInvalidConfigurationInQueryException() {
        // given
        var e = new CassandraInvalidConfigurationInQueryException("testMessage");
        // when
        var actual = objectUnderTest.handleCassandraException(e);
        // then
        BDDAssertions.then(actual)
                .extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
                .containsExactly(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry there was a problem processing your request");
    }

    @Test
    void shouldHandleCassandraInvalidQueryException() {
        // given
        var e = new CassandraInvalidQueryException("testMessage");
        // when
        var actual = objectUnderTest.handleCassandraException(e);
        // then
        BDDAssertions.then(actual)
                .extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
                .containsExactly(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry there was a problem processing your request");
    }

    @Test
    void shouldHandleCassandraKeyspaceExistsException() {
        // given
        var e = new CassandraKeyspaceExistsException("testKeyspace", "testMessage", new Throwable());
        // when
        var actual = objectUnderTest.handleCassandraException(e);
        // then
        BDDAssertions.then(actual)
                .extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
                .containsExactly(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry there was a problem processing your request");
    }

    @Test
    void shouldHandleCassandraQuerySyntaxException() {
        // given
        var e = new CassandraQuerySyntaxException("testMessage", new Throwable());
        // when
        var actual = objectUnderTest.handleCassandraException(e);
        // then
        BDDAssertions.then(actual)
                .extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
                .containsExactly(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry there was a problem processing your request");
    }

    @Test
    void shouldCassandraReadTimeoutException() {
        // given
        var e = new CassandraReadTimeoutException(true, "testMessage", new Throwable());
        // when
        var actual = objectUnderTest.handleCassandraException(e);
        // then
        BDDAssertions.then(actual)
                .extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
                .containsExactly(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry there was a problem processing your request");
    }

    @Test
    void shouldCassandraSchemaElementExistsException() {
        // given
        var e = new CassandraSchemaElementExistsException("testMessage", new Throwable());
        // when
        var actual = objectUnderTest.handleCassandraException(e);
        // then
        BDDAssertions.then(actual)
                .extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
                .containsExactly(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry there was a problem processing your request");
    }

    @Test
    void shouldCassandraTableExistsException() {
        // given
        var e = new CassandraTableExistsException("testTableName","testMessage", new Throwable());
        // when
        var actual = objectUnderTest.handleCassandraException(e);
        // then
        BDDAssertions.then(actual)
                .extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
                .containsExactly(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry there was a problem processing your request");
    }

    @Test
    void shouldCassandraTraceRetrievalException() {
        // given
        var e = new CassandraTraceRetrievalException("testMessage", new Throwable());
        // when
        var actual = objectUnderTest.handleCassandraException(e);
        // then
        BDDAssertions.then(actual)
                .extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
                .containsExactly(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry there was a problem processing your request");
    }

    @Test
    void shouldCassandraTruncateException() {
        // given
        var e = new CassandraTruncateException("testMessage", new Throwable());
        // when
        var actual = objectUnderTest.handleCassandraException(e);
        // then
        BDDAssertions.then(actual)
                .extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
                .containsExactly(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry there was a problem processing your request");
    }


    @Test
    void shouldCassandraTypeMismatchException() {
        // given
        var e = new CassandraTypeMismatchException("testMessage", new Throwable());
        // when
        var actual = objectUnderTest.handleCassandraException(e);
        // then
        BDDAssertions.then(actual)
                .extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
                .containsExactly(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry there was a problem processing your request");
    }

    @Test
    void shouldCassandraUnauthorizedException() {
        // given
        var e = new CassandraUnauthorizedException("testMessage", new Throwable());
        // when
        var actual = objectUnderTest.handleCassandraException(e);
        // then
        BDDAssertions.then(actual)
                .extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
                .containsExactly(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry there was a problem processing your request");
    }

    @Test
    void shouldCassandraWriteTimeoutException() {
        // given
        var e = new CassandraWriteTimeoutException(null, "testMessage", new Throwable());
        // when
        var actual = objectUnderTest.handleCassandraException(e);
        // then
        BDDAssertions.then(actual)
                .extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
                .containsExactly(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry there was a problem processing your request");
    }

}