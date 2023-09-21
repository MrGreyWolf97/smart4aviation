package smart4aviation.vm;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@JsonSerialize
@Getter
public class AirportVM {
    private Integer departingFlights;
    private Integer arrivingFlights;
    private Integer totalDepartingBaggagePieces;
    private Integer totalArrivingBaggagePieces;
}