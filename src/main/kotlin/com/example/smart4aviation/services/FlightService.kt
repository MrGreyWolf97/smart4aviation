package com.example.smart4aviation.services

import com.example.smart4aviation.entities.Flight
import com.example.smart4aviation.repositories.FlightRepository
import org.springframework.stereotype.Service

@Service
class FlightService(
    val flightRepository: FlightRepository
) {

    fun getAllFlights(): List<Flight> {
        return flightRepository.findAll()
    }
}