package com.example.smart4aviation.controller

import com.example.smart4aviation.entity.Flight
import com.example.smart4aviation.service.AirportService
import com.example.smart4aviation.vm.AirportVM
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.time.ZonedDateTime

@RestController
@RequestMapping("api/airports")
class AirportController(
    val airportService: AirportService
) {
    @GetMapping("/{IATA}/date/{date}")
    fun getAirportInfo(
        @PathVariable("IATA") IATACode: String,
        @PathVariable("date") date: ZonedDateTime
    ): AirportVM {
        return airportService.getAirportInfo(IATACode, date) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND, "Airport not found"
        )
    }

}