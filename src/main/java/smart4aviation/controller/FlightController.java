package smart4aviation.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import smart4aviation.service.FlightService;
import smart4aviation.vm.FlightVM;

import java.io.IOException;
import java.time.ZonedDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("api/flights")
public class FlightController {

    private FlightService flightService;

    /*
     * https://travel.stackexchange.com/questions/4912/can-two-different-planes-share-the-same-flight-number#:~:text=Yes%2C%20the%20same%20flight%20number,current%20days%20flight%20takes%20off.
     *  Two planes can share the same flightNumber -> in the same say multiple flights can be reached by this API,
     *  the result will be a sum of all the values for all the flights in the same day
     */
    @GetMapping("/{flightNumber}/date/{date}")
    public FlightVM getFlightInfo(
            @PathVariable("flightNumber") Integer flightNumber,
            @PathVariable("date") ZonedDateTime date
    ) throws IOException {
        FlightVM flightVM = flightService.getFlightInfo(flightNumber, date);
        if (flightVM == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight not found");
        return flightVM;
    }
}