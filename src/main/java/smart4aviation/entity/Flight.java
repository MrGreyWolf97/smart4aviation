package smart4aviation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@JsonSerialize
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Flight {
    private Long flightId;
    private Integer flightNumber;
    private String departureAirportIATACode;
    private String arrivalAirportIATACode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private ZonedDateTime departureDate;
}