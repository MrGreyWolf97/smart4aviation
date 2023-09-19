package com.example.smart4aviation.service

import com.example.smart4aviation.repository.FlightCargoRepository
import com.example.smart4aviation.repository.FlightRepository
import com.example.smart4aviation.vm.AirportVM
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class AirportService(
    val flightRepository: FlightRepository,
    val flightCargoRepository: FlightCargoRepository
) {

    fun getAirportInfo(IATACode: String, date: ZonedDateTime): AirportVM? {
        val flightsFromAirport = flightRepository.findFlightDepartingFrom(IATACode).filter { it.departureDate.isEqual(date) }
        val flightsToAirport = flightRepository.findFlightArrivingTo(IATACode).filter { it.departureDate.isEqual(date) }

        if (flightsFromAirport.isEmpty() && flightsToAirport.isEmpty()) return null

        val flightCargoFromAirport = flightCargoRepository.findAllWithIdIn(flightsFromAirport.map { it.flightId })
        val flightCargoToAirport = flightCargoRepository.findAllWithIdIn(flightsToAirport.map { it.flightId })

        return AirportVM(
            departingFlights = flightsFromAirport.size,
            arrivingFlights = flightsToAirport.size,
            totalDepartingBaggagePieces = flightCargoFromAirport.sumOf { it.baggage.getTotalPieces() },
            totalArrivingBaggagePieces = flightCargoToAirport.sumOf { it.baggage.getTotalPieces() }
        )
    }

}