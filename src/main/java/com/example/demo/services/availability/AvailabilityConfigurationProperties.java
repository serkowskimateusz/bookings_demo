package com.example.demo.services.availability;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Configuration
@ConfigurationProperties(prefix="bookings")
@Data
@Validated
public class AvailabilityConfigurationProperties {
    @NotNull
    private String externalServiceHost;
}