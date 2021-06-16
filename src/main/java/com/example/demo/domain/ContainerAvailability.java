package com.example.demo.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ContainerAvailability {
    private final boolean available;
}