package smart4aviation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import smart4aviation.entity.Flight;
import smart4aviation.entity.FlightCargo;
import smart4aviation.repository.FlightCargoRepository;
import smart4aviation.repository.FlightRepository;
import smart4aviation.vm.AirportVM;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AirportService {

    private FlightRepository flightRepository;
    private FlightCargoRepository flightCargoRepository;

    public AirportVM getAirportInfo(String IATACode, ZonedDateTime date) throws IOException {
        List<Flight> flightsFromAirport = flightRepository.findFlightDepartingFrom(IATACode).stream()
                .filter(flight -> flight.getDepartureDate().isEqual(date))
                .toList();
        List<Flight> flightsToAirport = flightRepository.findFlightArrivingTo(IATACode).stream()
                .filter(flight -> flight.getDepartureDate().isEqual(date))
                .toList();

        if (flightsFromAirport.isEmpty() && flightsToAirport.isEmpty()) return null;

        List<FlightCargo> flightCargoFromAirport = flightCargoRepository.findAllWithIdIn(flightsFromAirport.stream()
                .map(Flight::getFlightId)
                .collect(Collectors.toList()));
        List<FlightCargo> flightCargoToAirport = flightCargoRepository.findAllWithIdIn(flightsToAirport.stream()
                .map(Flight::getFlightId)
                .collect(Collectors.toList()));

        return new AirportVM(
                flightsFromAirport.size(),
                flightsToAirport.size(),
                flightCargoFromAirport.stream().map(FlightCargo::getBaggageTotalPieces).reduce(Integer::sum).orElse(0),
                flightCargoToAirport.stream().map(FlightCargo::getBaggageTotalPieces).reduce(Integer::sum).orElse(0)
        );
    }

}