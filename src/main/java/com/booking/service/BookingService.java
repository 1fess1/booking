package com.booking.service;

import com.booking.dto.BookingCreateRequest;
import com.booking.dto.BookingDto;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    List<BookingDto> getBookings(LocalDate date);

    BookingDto save(BookingCreateRequest request);
}
