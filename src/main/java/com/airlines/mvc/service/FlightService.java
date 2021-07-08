package com.airlines.mvc.service;

import com.airlines.mvc.DTO.FlightDTO;
import com.airlines.mvc.DTO.TouristDTO;
import com.airlines.mvc.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FlightService {
    Page<Flight> findByStartingDestination(String startingDestination, Pageable pageable);
    Page<Flight> findAll(Pageable pageable);
    Optional<Flight> findById(Long id);
    void save(FlightDTO flightDTO);
    void updateFlight(Long id, FlightDTO flightDTO);
    void addTouristToFlight(Long id, TouristDTO touristDTO);
    void deleteTouristFromFlight(Long flightId, Long touristId);
    void deleteById(long id);
    void fillWithData(Integer amountOfFlight);
//    List<Tourist> findTouristsInFlight(Long id);
}
