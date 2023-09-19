package com.example.smart4aviation.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.ZonedDateTime

@SpringBootTest
class AirportServiceTest {

    @Autowired
    lateinit var airportService: AirportService

    @Test
    fun `test getAirportInfo`() {
        val airportInfo = airportService.getAirportInfo("YYT", ZonedDateTime.parse("2021-06-18T12:36:18-02:00"));
        assertEquals(0, airportInfo!!.arrivingFlights)
        assertEquals(1, airportInfo.departingFlights)
        assertEquals(0, airportInfo.totalArrivingBaggagePieces)
        assertEquals(2550, airportInfo.totalDepartingBaggagePieces)
    }
}