package com.booking;

import com.booking.common.Mapper;
import com.booking.controller.BookingController;
import com.booking.service.BookingService;
import com.google.gson.Gson;
import io.muserver.MuServer;
import io.muserver.MuServerBuilder;
import io.muserver.rest.CORSConfigBuilder;
import io.muserver.rest.RestHandlerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Slf4j
@EnableJpaRepositories
@EnableTransactionManagement
@SpringBootApplication
public class BookingApplication {

    private static BookingService bookingService;
    private static Mapper mapper;
    private static Gson getGson;

    @Autowired
    public BookingApplication(BookingService bookingService, Mapper mapper, Gson getGson) {
        BookingApplication.getGson = getGson;
        BookingApplication.mapper = mapper;
        BookingApplication.bookingService = bookingService;
    }

    public static void main(String[] args) {
        startServer();
        SpringApplication.run(BookingApplication.class, args);
    }

    private static void startServer() {
        MuServer server = MuServerBuilder.httpServer()
                .addHandler(
                        RestHandlerBuilder.restHandler(new BookingController(bookingService, mapper, getGson))
                                .withOpenApiHtmlUrl("/api.html")
                                .withOpenApiJsonUrl("/openapi.json")
                                .withCORS(
                                        CORSConfigBuilder.corsConfig()
                                                .withAllowedOrigins("https://petstore.swagger.io")
                                                .withAllowedOriginRegex("http(s)?://localhost:[0-9]+")
                                ))

                .withHttpPort(8998)
                .start();
    }

}

