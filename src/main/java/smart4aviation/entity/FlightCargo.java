package smart4aviation.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * I renamed the entity into FlightCargo to avoid misunderstanding when considering the Cargo elements
 */
@JsonSerialize
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FlightCargo {

    private final Double LBS_TO_KG = 1 / 2.20462;

    private Long flightId;
    private List<TransportUnit> baggage;
    private List<TransportUnit> cargo;

    public Integer getBaggageTotalPieces() {
        return baggage.stream().map(TransportUnit::getPieces).reduce(0, Integer::sum);
    }

    public Double getBaggageTotalWeight() {
        return baggage.stream().map(baggage -> baggage.getWeight() * getCOMConversionRatio(baggage.getWeightUnit())).reduce(.0, Double::sum);
    }

    public Integer getCargoTotalPieces() {
        return cargo.stream().map(TransportUnit::getPieces).reduce(0, Integer::sum);
    }

    public Double getCargoTotalWeight() {
        return cargo.stream().map(cargo -> cargo.getWeight() * getCOMConversionRatio(cargo.getWeightUnit())).reduce(.0, Double::sum);
    }

    private Double getCOMConversionRatio(String UOM) {
        return switch (UOM) {
            case "kg" -> 1.0;
            case "lb" -> LBS_TO_KG;
            default -> .0;
        };
    }
}


@JsonSerialize
@AllArgsConstructor
@NoArgsConstructor
@Getter
class TransportUnit {
    private Long id;
    private Integer weight;
    private String weightUnit;
    private Integer pieces;
}