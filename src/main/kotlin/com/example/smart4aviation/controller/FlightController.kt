package com.example.smart4aviation.controller

import com.example.smart4aviation.service.FlightService
import com.example.smart4aviation.vm.FlightVM
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.time.ZonedDateTime

@RestController
@RequestMapping("api/flights")
private class FlightController(
    val flightService: FlightService
) {


    /*
    * https://travel.stackexchange.com/questions/4912/can-two-different-planes-share-the-same-flight-number#:~:text=Yes%2C%20the%20same%20flight%20number,current%20days%20flight%20takes%20off.
    *  Two planes can share the same flightNumber -> in the same say multiple flights can be reached by this API,
    *  the result will be a sum of all the values for all the flights in the same day
    */
    @GetMapping("/{flightNumber}/date/{date}")
    fun getFlightInfo(
        @PathVariable("flightNumber") flightNumber: Int,
        @PathVariable("date") date: ZonedDateTime
    ): FlightVM {
        return flightService.getFlightInfo(flightNumber, date)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Flight not found")
    }
}