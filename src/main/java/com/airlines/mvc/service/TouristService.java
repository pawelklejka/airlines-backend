package com.airlines.mvc.service;

import com.airlines.mvc.DTO.FlightDTO;
import com.airlines.mvc.DTO.TouristDTO;
import com.airlines.mvc.model.Tourist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface TouristService {
    Page<Tourist> findAll(Pageable pageable);
    Optional<Tourist> findById(Long id);
    void save(TouristDTO touristDTO);
    void updateTourist(Long id, TouristDTO touristDTO);
    void addFlightToTourist(Long id, FlightDTO flightDTO);
    void deleteFlightFromTourist(Long touristId, Long flightId);
    void deleteById(Long id);
}
