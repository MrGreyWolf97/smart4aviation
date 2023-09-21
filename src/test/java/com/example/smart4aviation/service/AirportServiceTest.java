package com.example.smart4aviation.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import smart4aviation.repository.FlightCargoRepository;
import smart4aviation.repository.FlightRepository;
import smart4aviation.service.AirportService;
import smart4aviation.vm.AirportVM;

import java.io.IOException;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest()
public class AirportServiceTest {


    private AirportService airportService;

    @Before
    public void init() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        FlightRepository flightRepository = new FlightRepository("mock/flights.json", objectMapper);
        FlightCargoRepository flightCargoRepository = new FlightCargoRepository("mock/cargos.json", objectMapper);
        airportService = new AirportService(flightRepository, flightCargoRepository);
    }


    @Test
    public void testGetAirportInfo() throws IOException {
        AirportVM airportInfo = airportService.getAirportInfo("YYT", ZonedDateTime.parse("2021-06-18T12:36:18-02:00"));
        assertEquals(0, airportInfo.getArrivingFlights());
        assertEquals(1, airportInfo.getDepartingFlights());
        assertEquals(0, airportInfo.getTotalArrivingBaggagePieces());
        assertEquals(2550, airportInfo.getTotalDepartingBaggagePieces());
    }
}