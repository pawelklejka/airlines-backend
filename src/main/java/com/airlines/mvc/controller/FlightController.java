package com.airlines.mvc.controller;

import com.airlines.mvc.DTO.FlightDTO;
import com.airlines.mvc.DTO.TouristDTO;
import com.airlines.mvc.model.Flight;
import com.airlines.mvc.model.Tourist;
import com.airlines.mvc.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    @Qualifier("flightService")
    private FlightService flightService;

    @GetMapping("/searchByDestination")
    public Page<Flight> findByStartingDestination(@RequestParam Optional<String> name,
                                                  @RequestParam Optional<Integer> page){
        return flightService.findByStartingDestination(
                name.orElse("_"),
                PageRequest.of(page.orElse(0), 2, Sort.by("startingDestination").and(Sort.by("flightStartingTime"))));
    }

    @GetMapping("/all")
    public Page<Flight> findAll(@RequestParam Optional<Integer> page){

        return flightService.findAll(PageRequest.of(page.orElse(0), 21, Sort.by("startingDestination").and(Sort.by("flightStartingTime"))));
    }

    @GetMapping("/{id}")
    public Flight findById(@PathVariable("id") Long id){
        return flightService.findById(id).orElseThrow(() -> new IllegalArgumentException("No flight with id: " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        flightService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void addTouristToFlight(@PathVariable("id") Long id,  @RequestBody TouristDTO touristDTO){
        flightService.addTouristToFlight(id, touristDTO);
    }

    @PatchMapping("/{id}/delete")
    public void deleteTouristFromFlight(@PathVariable("id") Long flightId, @RequestParam Long touristId){
        //TODO NAPRAWIC TEN ENDPOINT
        flightService.deleteTouristFromFlight(flightId, touristId);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @Valid @RequestBody FlightDTO flightDTO){

        flightService.updateFlight(id, flightDTO);
    }

    @GetMapping("/findTouristsInFlight/{id}")
    public Set<TouristDTO> findTouristsInFlights(@PathVariable Long id){
        return flightService.findTouristsInFlight(id);
    }

    @PostMapping("/")
    public void save(@Valid @RequestBody FlightDTO flightDTO){
        flightService.save(flightDTO);
    }

    @GetMapping("/fillWithData")
    public void fillWithData(@RequestParam Integer amountOfFlight){
        flightService.fillWithData(amountOfFlight);
    }
}
