package com.example.demo;

import com.example.demo.custom_validators.BookingDetailsValidatorException;
import com.example.demo.services.booking_ref.BookingRefCounterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.codec.DecodingException;
import org.springframework.data.cassandra.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class BookingsControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<String> handleResourceAccessException(ResourceAccessException ex) {
        return ResponseEntity.internalServerError()
                .body("We have problem with our booking system. Please try one more time or contact with our support team . Error: " + ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleBookingDetailsValidatorException(BookingDetailsValidatorException ex) {
        return ResponseEntity.badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity handleDecodingException(DecodingException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity handleBookingRefCounterException(BookingRefCounterException exception) {
        return ResponseEntity.internalServerError().body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity handleCassandraException(CassandraUncategorizedException exception) {
        log.warn("Problem with Cassandra occurred: " + exception.getMessage());
        return ResponseEntity.internalServerError().body("Sorry there was a problem processing your request");
    }

    @ExceptionHandler
    public ResponseEntity handleCassandraException(CassandraAuthenticationException exception) {
        log.warn("Problem with Cassandra occurred: " + exception.getMessage());
        return ResponseEntity.internalServerError().body("Sorry there was a problem processing your request");
    }

    @ExceptionHandler
    public ResponseEntity handleCassandraException(CassandraConnectionFailureException exception) {
        log.warn("Problem with Cassandra occurred: " + exception.getMessage());
        return ResponseEntity.internalServerError().body("Sorry there was a problem processing your request");
    }

    @ExceptionHandler
    public ResponseEntity handleCassandraException(CassandraInsufficientReplicasAvailableException exception) {
        log.warn("Problem with Cassandra occurred: " + exception.getMessage());
        return ResponseEntity.internalServerError().body("Sorry there was a problem processing your request");
    }
    @ExceptionHandler
    public ResponseEntity handleCassandraException(CassandraInternalException exception) {
        log.warn("Problem with Cassandra occurred: " + exception.getMessage());
        return ResponseEntity.internalServerError().body("Sorry there was a problem processing your request");
    }
    @ExceptionHandler
    public ResponseEntity handleCassandraException(CassandraInvalidConfigurationInQueryException exception) {
        log.warn("Problem with Cassandra occurred: " + exception.getMessage());
        return ResponseEntity.internalServerError().body("Sorry there was a problem processing your request");
    }
    @ExceptionHandler
    public ResponseEntity handleCassandraException(CassandraInvalidQueryException exception) {
        log.warn("Problem with Cassandra occurred: " + exception.getMessage());
        return ResponseEntity.internalServerError().body("Sorry there was a problem processing your request");
    }
    @ExceptionHandler
    public ResponseEntity handleCassandraException(CassandraKeyspaceExistsException exception) {
        log.warn("Problem with Cassandra occurred: " + exception.getMessage());
        return ResponseEntity.internalServerError().body("Sorry there was a problem processing your request");
    }
    @ExceptionHandler
    public ResponseEntity handleCassandraException(CassandraQuerySyntaxException exception) {
        log.warn("Problem with Cassandra occurred: " + exception.getMessage());
        return ResponseEntity.internalServerError().body("Sorry there was a problem processing your request");
    }
    @ExceptionHandler
    public ResponseEntity handleCassandraException(CassandraReadTimeoutException exception) {
        log.warn("Problem with Cassandra occurred: " + exception.getMessage());
        return ResponseEntity.internalServerError().body("Sorry there was a problem processing your request");
    }
    @ExceptionHandler
    public ResponseEntity handleCassandraException(CassandraSchemaElementExistsException exception) {
        log.warn("Problem with Cassandra occurred: " + exception.getMessage());
        return ResponseEntity.internalServerError().body("Sorry there was a problem processing your request");
    }
    @ExceptionHandler
    public ResponseEntity handleCassandraException(CassandraTableExistsException exception) {
        log.warn("Problem with Cassandra occurred: " + exception.getMessage());
        return ResponseEntity.internalServerError().body("Sorry there was a problem processing your request");
    }
    @ExceptionHandler
    public ResponseEntity handleCassandraException(CassandraTraceRetrievalException exception) {
        log.warn("Problem with Cassandra occurred: " + exception.getMessage());
        return ResponseEntity.internalServerError().body("Sorry there was a problem processing your request");
    }
    @ExceptionHandler
    public ResponseEntity handleCassandraException(CassandraTruncateException exception) {
        log.warn("Problem with Cassandra occurred: " + exception.getMessage());
        return ResponseEntity.internalServerError().body("Sorry there was a problem processing your request");
    }
    @ExceptionHandler
    public ResponseEntity handleCassandraException(CassandraTypeMismatchException exception) {
        log.warn("Problem with Cassandra occurred: " + exception.getMessage());
        return ResponseEntity.internalServerError().body("Sorry there was a problem processing your request");
    }
    @ExceptionHandler
    public ResponseEntity handleCassandraException(CassandraUnauthorizedException exception) {
        log.warn("Problem with Cassandra occurred: " + exception.getMessage());
        return ResponseEntity.internalServerError().body("Sorry there was a problem processing your request");
    }
    @ExceptionHandler
    public ResponseEntity handleCassandraException(CassandraWriteTimeoutException exception) {
        log.warn("Problem with Cassandra occurred: " + exception.getMessage());
        return ResponseEntity.internalServerError().body("Sorry there was a problem processing your request");
    }

}
