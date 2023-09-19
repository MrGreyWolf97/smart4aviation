package com.example.smart4aviation.repository

import com.example.smart4aviation.entity.FlightCargo
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class FlightCargoRepository(
    @Value("\${mock.cargos}")
    mockPath: String,
    objectMapper: ObjectMapper
) : JsonRepository<FlightCargo>(mockPath, object : TypeReference<List<FlightCargo>>() {}, objectMapper) {

    fun findById(flightId: Long): FlightCargo {
        return findAll().filter { it.flightId == flightId }[0]
    }

    fun findAllWithIdIn(flightIds: List<Long>): List<FlightCargo> {
        return findAll().filter { flightIds.contains(it.flightId) }
    }
}