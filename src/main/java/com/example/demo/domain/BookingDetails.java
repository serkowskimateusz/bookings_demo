package com.example.demo.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@SuperBuilder
@NoArgsConstructor
public class BookingDetails {

    @NotNull
    private Integer containerSize;
    @NotNull
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
}