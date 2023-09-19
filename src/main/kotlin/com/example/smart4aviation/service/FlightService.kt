package com.example.smart4aviation.service

import com.example.smart4aviation.entity.Flight
import com.example.smart4aviation.repository.FlightCargoRepository
import com.example.smart4aviation.repository.FlightRepository
import com.example.smart4aviation.vm.FlightVM
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class FlightService(
    val flightRepository: FlightRepository,
    val flightCargoRepository: FlightCargoRepository
) {

    fun getFlightInfo(
        flightNumber: Int,
        date: ZonedDateTime
    ): FlightVM? {
        return getFlightsByFlightNumber(flightNumber).takeUnless { it.isEmpty() }
            ?.filter { flight -> flight.departureDate.isEqual(date) }
            ?.map { flight -> flight.flightId }
            ?.map { flightId -> flightCargoRepository.findById(flightId) }
            ?.let { flightCargoList ->
                FlightVM(
                    flightCargoList.sumOf { it.cargo.getTotalWeight() },
                    flightCargoList.sumOf { it.baggage.getTotalWeight() }
                )
            }
    }

    fun getFlightsByFlightNumber(flightNumber: Int): List<Flight> {
        return flightRepository.findAll().filter { it.flightNumber == flightNumber }
    }

}