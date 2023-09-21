package smart4aviation.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import smart4aviation.service.AirportService;
import smart4aviation.vm.AirportVM;

import java.io.IOException;
import java.time.ZonedDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("api/airports")
public class AirportController {

    private AirportService airportService;

    @GetMapping("/{IATA}/date/{date}")
    public AirportVM getAirportInfo(
            @PathVariable("IATA") String IATACode,
            @PathVariable("date") ZonedDateTime date) throws IOException {
        AirportVM airportVM = airportService.getAirportInfo(IATACode, date);
        if (airportVM == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport not found");
        return airportVM;
    }

}