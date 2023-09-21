package smart4aviation.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import smart4aviation.entity.FlightCargo;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FlightCargoRepository extends JsonRepository<FlightCargo> {

    public FlightCargoRepository(
            @Value("${mock.cargos}")
            String mockPath,
            ObjectMapper objectMapper
    ) {
        super(mockPath, new TypeReference<>() {}, objectMapper);
    }

    public FlightCargo findById(Long flightId) throws IOException {
        return findAll().stream()
                .filter(flightCargo -> Objects.equals(flightCargo.getFlightId(), flightId))
                .toList()
                .get(0);
    }

    public List<FlightCargo> findAllWithIdIn(List<Long> flightIds) throws IOException {
        return findAll().stream()
                .filter(flightCargo -> flightIds.contains(flightCargo.getFlightId()))
                .collect(Collectors.toList());
    }
}