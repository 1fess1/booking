package com.booking.common;

import com.booking.dto.BookingCreateRequest;
import com.booking.dto.BookingDto;
import com.booking.dto.TableSize;
import com.booking.model.Booking;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class Mapper {

    public BookingDto toDto(Booking booking) {
        return BookingDto.builder()
                .customerName(booking.getCustomerName())
                .date(booking.getDate())
                .from(booking.getFrom())
                .to(booking.getTo())
                .tableSize(booking.getTableSize())
                .build();
    }

    public BookingCreateRequest toBookingCreateRequest(String name, String tableSize, String date, String from) {
        return BookingCreateRequest.builder()
                .customerName(name)
                .tableSize(TableSize.valueOf(tableSize))
                .date(LocalDate.parse(date))
                .from(LocalTime.parse(from))
                .build();
    }

}
