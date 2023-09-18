package com.example.smart4aviation.repositories

import com.example.smart4aviation.entities.Flight
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class FlightRepository(
    @Value("\${mock.flights}")
    mockPath: String,
    objectMapper: ObjectMapper
) : JsonRepository<Flight>(mockPath, object : TypeReference<List<Flight>>() {}, objectMapper) {
}