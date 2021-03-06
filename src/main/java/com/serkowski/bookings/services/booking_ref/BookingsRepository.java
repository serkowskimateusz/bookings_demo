package com.serkowski.bookings.services.booking_ref;

import com.serkowski.bookings.domain.BookingDetailsWithTimestamp;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingsRepository extends ReactiveCassandraRepository<BookingDetailsWithTimestamp, String> {
}