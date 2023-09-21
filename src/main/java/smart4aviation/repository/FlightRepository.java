package smart4aviation.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import smart4aviation.entity.Flight;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FlightRepository extends JsonRepository<Flight> {

    public FlightRepository(
            @Value("${mock.flights}")
            String mockPath,
            ObjectMapper objectMapper
    ) {
        super(mockPath, new TypeReference<>() {}, objectMapper);
    }

    public List<Flight> findFlightDepartingFrom(String IATACode) throws IOException {
        return findAll().stream().filter(flight -> Objects.equals(flight.getDepartureAirportIATACode(), IATACode)).collect(Collectors.toList());
    }

    public List<Flight> findFlightArrivingTo(String IATACode) throws IOException {
        return findAll().stream().filter(flight -> Objects.equals(flight.getArrivalAirportIATACode(), IATACode)).collect(Collectors.toList());
    }

}