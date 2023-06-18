package com.booking.common;

import com.booking.dto.BookingDto;
import com.booking.model.Booking;
import org.springframework.stereotype.Component;

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

}
