package com.booking.controller;

import com.booking.common.Mapper;
import com.booking.dto.BookingDto;
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
import java.util.List;

@Slf4j
@Path("/")
public class BookingController {

    private final BookingService bookingService;

    private final Mapper mapper;

    private final Gson getGson;

    public BookingController(BookingService bookingService, Mapper mapper, Gson getGson) {
        this.bookingService = bookingService;
        this.mapper = mapper;
        this.getGson = getGson;
    }

    @GET
    @Path("list/date/{date}")
    @Produces("application/json")
    public String getBookings(@PathParam("date") String date) {
    log.debug("getBookings " + date);
					LocalDate localDate = LocalDate.parse(date);
        List<BookingDto> bookings = bookingService.getBookings(localDate);
        log.debug("Booking size " + bookings.size());
        return getGson.toJson(bookings);
    }

    @POST
    @Path("bookings/create")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String createBooking(@FormParam("customerName") String name,
                                    @FormParam("tableSize") String tableSize,
                                    @FormParam("date") String date,
                                    @FormParam("from") String from) {

        return getGson.toJson(bookingService.saveBooking(mapper.toBookingCreateRequest(name, tableSize, date, from)));
    }
}
