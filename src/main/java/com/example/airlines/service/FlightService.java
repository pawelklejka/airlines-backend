package com.example.airlines.service;

import com.example.airlines.DTO.FlightDTO;
import com.example.airlines.DTO.TouristDTO;
import com.example.airlines.model.Flight;
import com.example.airlines.model.Tourist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
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
