package com.example.smart4aviation.entities

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.time.ZonedDateTime

@JsonSerialize
class Flight(
    val flightId: Long,
    val flightNumber: Int,
    val departureAirportIATACode: String,
    val arrivalAirportIATACode: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING,  pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    val departureDate: ZonedDateTime
) {
}