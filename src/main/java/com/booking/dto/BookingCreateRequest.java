package com.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookingCreateRequest {

    private String customerName;

    private TableSize tableSize;

    private LocalDate date;

    private LocalTime from;

}
