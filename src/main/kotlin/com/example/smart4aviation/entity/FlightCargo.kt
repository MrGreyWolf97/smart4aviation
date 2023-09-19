package com.example.smart4aviation.entity

import com.fasterxml.jackson.databind.annotation.JsonSerialize

/**
 * I renamed the entity into FlightCargo to avoid misunderstanding when considering the Cargo elements
 */
@JsonSerialize
class FlightCargo(
    val flightId: Long,
    val baggage: TransportUnitList,
    val cargo: TransportUnitList
) {}

@JsonSerialize
class TransportUnitList() : MutableList<TransportUnit> by mutableListOf() {

    private val LBS_TO_KG = 1 / 2.20462

    fun getTotalPieces(): Int {
        return this.sumOf { it.pieces }
    }

    fun getTotalWeight(): Double {
        return this.sumOf { it.weight * getCOMConversionRatio(it.weightUnit) }
    }

    private fun getCOMConversionRatio(UOM: String): Double {
        return when (UOM) {
            "kg" -> 1.0
            "lb" -> LBS_TO_KG
            else -> .0
        }

    }
}

@JsonSerialize
class TransportUnit(
    val id: Long,
    val weight: Int,
    val weightUnit: String,
    val pieces: Int
) {}