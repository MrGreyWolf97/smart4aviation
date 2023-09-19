package com.example.smart4aviation.vm

import com.fasterxml.jackson.databind.annotation.JsonSerialize

@JsonSerialize
class FlightVM(
    val cargoWeightKg: Double,
    val baggageWeightKg: Double,
    val totalWeightKg: Double = cargoWeightKg + baggageWeightKg
) {
}