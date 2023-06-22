package com.booking.controller;

import com.booking.dto.BookingCreateRequest;
import com.booking.dto.BookingDto;
import com.booking.dto.TableSize;
import com.booking.service.BookingService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Path("/")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GET
    @Path("list/date/{date}")
    @Produces("application/json")
    public String getBookings(@PathParam("date") String date) {
    log.debug("getBookings " + date);
					LocalDate localDate = LocalDate.parse(date);
        List<BookingDto> bookings = bookingService.getBookings(localDate);
        log.debug("Booking size " + bookings.size());
        Gson gson = new Gson();
        return gson.toJson(bookings);
    }

    @POST
    @Path("bookings/create")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String createBooking(@FormParam("customerName") String name,
                                    @FormParam("tableSize") String tableSize,
                                    @FormParam("date") String date,
                                    @FormParam("from") String from) {
        BookingDto bookingDto = bookingService.saveBooking(BookingCreateRequest.builder()
                .customerName(name)
                .tableSize(TableSize.valueOf(tableSize))
                .date(LocalDate.parse(date))
                .from(LocalTime.parse(from))
                .build());
        Gson gson = new Gson();
        return gson.toJson(bookingDto);
    }
}
