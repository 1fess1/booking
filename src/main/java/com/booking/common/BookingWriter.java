//package com.booking.common;
//
//import com.booking.dto.BookingDto;
//import com.booking.model.Booking;
//
//import javax.ws.rs.Produces;
//import javax.ws.rs.WebApplicationException;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.MultivaluedMap;
//import javax.ws.rs.ext.MessageBodyWriter;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Type;
//
//@Produces("application/json")
//public class BookingWriter implements MessageBodyWriter<BookingDto> {
//
//    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
//        return BookingDto.class.isAssignableFrom(type);
//    }
//
//    public void writeTo(BookingDto booking, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
//        booking.toJSON().write(new OutputStreamWriter(entityStream));
//    }
//}
