package com.example.airlines.controller;

import com.example.airlines.DTO.FlightDTO;
import com.example.airlines.DTO.TouristDTO;
import com.example.airlines.model.Flight;
import com.example.airlines.model.Tourist;
import com.example.airlines.service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tourist")
public class TouristController {
    @Autowired
    @Qualifier("touristService")
    TouristService touristService;

    @GetMapping("/all")
    public Page<Tourist> findAll(@RequestParam Optional<Integer> page){
        return touristService.findAll(PageRequest.of(page.orElse(0), 21, Sort.by("surname").and(Sort.by("name"))));
    }

    @GetMapping("/{id}")
    public Tourist findById(@PathVariable("id") Long id){
        return touristService.findById(id).orElseThrow(() -> new IllegalArgumentException("No tourist with id: " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        touristService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void addFlightToTourist(@PathVariable("id") Long id, @Valid @RequestBody FlightDTO flightDTO){
        touristService.addFlightToTourist(id, flightDTO);
    }

    @PatchMapping("/delete/{id}")
    public void deleteFlightFromTourist(@PathVariable("id") Long touristId, @RequestParam Long flightId){

        touristService.deleteFlightFromTourist(touristId, flightId);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @Valid @RequestBody TouristDTO touristDTO){
        touristService.updateTourist(id, touristDTO);

    }

    @PostMapping("/")
    public void save(@Valid @RequestBody TouristDTO touristDTO){
        touristService.save(touristDTO);
    }
}
