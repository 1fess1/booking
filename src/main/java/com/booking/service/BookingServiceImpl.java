package com.booking.service;

import com.booking.common.Mapper;
import com.booking.dto.BookingCreateRequest;
import com.booking.dto.BookingDto;
import com.booking.model.BookingFactory;
import com.booking.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final BookingFactory bookingFactory;

    private final Mapper mapper;

    public BookingServiceImpl(BookingRepository bookingRepository, BookingFactory bookingFactory, Mapper mapper) {
        this.bookingRepository = bookingRepository;
        this.bookingFactory = bookingFactory;
        this.mapper = mapper;
    }

    @Override
    public List<BookingDto> getBookings(LocalDate date){
        log.trace("Try collect bookings for date " + date);
        List<BookingDto> bookingDtos = bookingRepository.getAllByActiveTrueAndDate(date).stream()
                .map(mapper::toDto).toList();
        log.info("Collected " + bookingDtos.size() + " bookings");
        return bookingDtos;
    }

    @Override
    public BookingDto saveBooking(BookingCreateRequest request){
        log.trace("Try save booking");
        BookingDto bookingDto = mapper.toDto(bookingRepository.save(bookingFactory.toBooking(request)));
        log.info("Booking saved for date: " + bookingDto.getDate());
        return bookingDto;
    }
}
