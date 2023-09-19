package com.example.smart4aviation.controller

import com.example.smart4aviation.entity.Flight
import com.example.smart4aviation.entity.FlightCargo
import com.example.smart4aviation.repository.FlightCargoRepository
import com.example.smart4aviation.repository.FlightRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("test")
class TestController(
    val flightRepository: FlightRepository,
    val flightCargoRepository: FlightCargoRepository
) {

    @GetMapping("/flights")
    fun getFlights(): List<Flight> {
        return flightRepository.findAll()
    }

    @GetMapping("/flightCargos")
    fun getFlightCargos(): List<FlightCargo> {
        return flightCargoRepository.findAll()
    }
}