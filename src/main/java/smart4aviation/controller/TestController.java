package smart4aviation.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import smart4aviation.entity.Flight;
import smart4aviation.entity.FlightCargo;
import smart4aviation.repository.FlightCargoRepository;
import smart4aviation.repository.FlightRepository;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class TestController {
    private FlightRepository flightRepository;
    private FlightCargoRepository flightCargoRepository;

    @GetMapping("/flights")
    public List<Flight> getFlights() throws IOException {
        return flightRepository.findAll();
    }

    @GetMapping("/flightCargos")
    public List<FlightCargo> getFlightCargos() throws IOException {
        return flightCargoRepository.findAll();
    }
}
