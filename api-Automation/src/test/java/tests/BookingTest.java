package tests;


import clients.BookingClient;

import com.fasterxml.jackson.databind.JsonNode;

import io.restassured.response.Response;

import models.BookingRequest;

import database.BookingRepository;

import org.junit.jupiter.api.Test;

import utils.JsonReader;
import utils.TokenManager;


import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


public class BookingTest {


    @Test
    void bookingTest() throws Exception {


        JsonNode data =
                JsonReader.getData("booking");


        BookingRequest request =
                new BookingRequest(
                        data.get("journeyType").asText(),
                        data.get("inventoryId").asText(),
                        List.of(
                                data.get("seatIds")
                                        .get(0)
                                        .asText()
                        ),
                        data.get("refundable").asBoolean(),
                        data.get("holdTtlSec").asInt()
                );



        Response response =
                new BookingClient()
                        .createBooking(
                                TokenManager.getToken(),
                                request
                        );


        // API validation

        assertEquals(
                201,
                response.statusCode()
        );


        String bookingId =
                response.jsonPath()
                        .getString("id");


        assertNotNull(bookingId);


        assertEquals(
                "HELD",
                response.jsonPath()
                        .getString("state")
        );


        assertEquals(
                "flight",
                response.jsonPath()
                        .getString("journeyType")
        );


        assertNull(
                response.jsonPath()
                        .getString("pnr")
        );



        // DATABASE VALIDATION

        BookingRepository repository =
                new BookingRepository();


        boolean exists =
                repository.bookingExists(bookingId);


        assertTrue(
                exists,
                "Booking record should exist in database"
        );


    }

}