package smart4aviation.vm;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

@JsonSerialize
@Getter
public class FlightVM {
    private final Double cargoWeightKg;
    private final Double baggageWeightKg;
    private final Double totalWeightKg;


    public FlightVM(
            Double cargoWeightKg,
            Double baggageWeightKg
    ) {
        this.cargoWeightKg = cargoWeightKg;
        this.baggageWeightKg = baggageWeightKg;
        this.totalWeightKg = cargoWeightKg + baggageWeightKg;
    }

}