package com.example.demo.services.booking_ref;

import com.example.demo.domain.BookingDetailsWithTimestamp;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingsRepository extends ReactiveCassandraRepository<BookingDetailsWithTimestamp, String> {
}