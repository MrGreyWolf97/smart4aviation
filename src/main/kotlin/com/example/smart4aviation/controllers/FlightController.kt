package com.example.smart4aviation.controllers

import com.example.smart4aviation.entities.Flight
import com.example.smart4aviation.services.FlightService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/flights")
private class FlightController(
    val flightService: FlightService,

) {

//    @GetMapping("/{flightId}")
//    fun getFlight(
//        @PathVariable("flightId") flightId: Long
//    ) {
//        flightService.printShit();
//    }

    @GetMapping()
    fun getFlights(): List<Flight> {
        // TODO: handle error for file not found
        return flightService.getAllFlights()
    }
}