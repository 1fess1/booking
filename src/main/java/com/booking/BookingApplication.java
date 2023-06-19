package com.booking;

import com.booking.dto.BookingCreateRequest;
import com.booking.dto.BookingDto;
import com.booking.dto.TableSize;
import com.booking.service.BookingService;
import com.google.gson.Gson;
import io.muserver.Http2ConfigBuilder;
import io.muserver.Method;
import io.muserver.MuHandler;
import io.muserver.MuRequest;
import io.muserver.MuResponse;
import io.muserver.MuServer;
import io.muserver.MuServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Slf4j
@EnableJpaRepositories
@EnableTransactionManagement
@SpringBootApplication
public class BookingApplication {

    private static BookingService bookingService;

    @Autowired
    public BookingApplication(BookingService bookingService) {
        BookingApplication.bookingService = bookingService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookingApplication.class, args);
        startServer();
    }

//    private static void startServer() {
//
//        MuServer server = MuServerBuilder.httpServer()
//                .addHandler(
//                        RestHandlerBuilder.restHandler(new BookingController(bookingService))
//                                .withOpenApiHtmlUrl("/api.html")
//                                .withOpenApiJsonUrl("/openapi.json"))
//                .start();
//    }


	private static void startServer() {
		MuServer server = MuServerBuilder.httpServer()
				.addHandler(Method.GET, "/booking/create", (request, response, pathParams) -> {
					BookingDto save = bookingService.save(BookingCreateRequest.builder()
							.customerName(pathParams.get("customerName"))
							.tableSize(TableSize.valueOf(pathParams.get("tableSize")))
							.date(LocalDate.parse(pathParams.get("date")))
							.from(LocalTime.parse(pathParams.get("from")))
							.build());
					response.write(new Gson().toJson(save));
					response.contentType("application/json");
				})
				.addHandler(Method.GET, "/bookings", (request, response, pathParams) -> {
					String date = pathParams.get("date");
					LocalDate parse = LocalDate.parse(date);
					List<BookingDto> bookings = bookingService.getBookings(parse);
					response.write(new Gson().toJson(bookings));
					response.contentType("application/json");
				})
				.addHandler(Method.POST, "/bookings/create", (request, response, pathParams) -> {
					BookingDto save = bookingService.save(BookingCreateRequest.builder()
									.customerName(request.form().get("customerName"))
									.tableSize(TableSize.valueOf(request.form().get("tableSize")))
									.date(LocalDate.parse(request.form().get("date")))
									.from(LocalTime.parse(request.form().get("from")))
							.build());
					response.write(new Gson().toJson(save));
					response.contentType("application/json");
				})
				.start();

	}

}

