package com.example.demo.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table(value = "bookings")
@Data
@Builder
public class BookingDetailsWithTimestamp {
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private final String id;// = UUIDs.timeBased();
    @NotNull
    @Column(value = "container_size")
    private Integer containerSize;
    @NotNull
    @Column(value = "container_type")
    private ContainerType containerType;
    @NotNull
    @Size(min = 5, max = 20, message = "Origin field should contain between 5 to 20 chars")
    private String origin;
    @NotNull
    @Size(min = 5, max = 20, message = "Destination field should contain between 5 to 20 chars")
    private String destination;
    @NotNull
    @Min(value = 1, message = "Minimum value for quantity field is 1")
    @Max(value = 100, message = "Maximum value for quantity field is 100")
    private Integer quantity;
    private String timeStamp;
}