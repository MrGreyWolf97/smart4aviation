package smart4aviation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import smart4aviation.entity.Flight;
import smart4aviation.entity.FlightCargo;
import smart4aviation.repository.FlightCargoRepository;
import smart4aviation.repository.FlightRepository;
import smart4aviation.vm.FlightVM;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FlightService {

    private FlightRepository flightRepository;
    private FlightCargoRepository flightCargoRepository;

    public FlightVM getFlightInfo(
            Integer flightNumber,
            ZonedDateTime date
    ) throws IOException {
        List<Flight> flights = getFlightsByFlightNumber(flightNumber);
        if (flights != null && !flights.isEmpty()) {
            List<FlightCargo> flightCargoList = flights.stream()
                    .filter(flight -> flight.getDepartureDate().isEqual(date))
                    .map(Flight::getFlightId)
                    .map(flightId -> {
                        try {
                            return flightCargoRepository.findById(flightId);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .toList();
            return new FlightVM(
                    flightCargoList.stream().map(FlightCargo::getCargoTotalWeight).reduce(Double::sum).orElse(.0),
                    flightCargoList.stream().map(FlightCargo::getBaggageTotalWeight).reduce(Double::sum).orElse(.0)
            );
        }
        return new FlightVM(.0, .0);
    }

    private List<Flight> getFlightsByFlightNumber(Integer flightNumber) throws IOException {
        return flightRepository.findAll().stream()
                .filter(flight -> Objects.equals(flight.getFlightNumber(), flightNumber))
                .collect(Collectors.toList());
    }

}