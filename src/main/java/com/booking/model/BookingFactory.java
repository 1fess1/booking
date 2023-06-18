package com.booking.model;

import com.booking.dto.BookingCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class BookingFactory {

    public Booking toBooking(BookingCreateRequest request){
        return Booking.builder()
                .active(Boolean.TRUE)
                .customerName(request.getCustomerName())
                .date(request.getDate())
                .from(request.getFrom())
                .to(request.getFrom().plusHours(2))
                .tableSize(request.getTableSize())
                .build();

    }

}
