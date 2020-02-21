package com.example.airlines.service;

import com.example.airlines.DTO.FlightDTO;
import com.example.airlines.DTO.TouristDTO;
import com.example.airlines.model.Flight;
import com.example.airlines.model.Tourist;
import com.example.airlines.repository.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("touristService")
public class TouristServiceImplementation implements TouristService {
    @Autowired
    TouristRepository touristRepository;



    @Autowired
    DateParserService dateParserService;

    @Override
    public Page<Tourist> findAll(Pageable pageable) {
        return touristRepository.findAll(pageable);
    }

    @Override
    public Optional<Tourist> findById(Long id) {
        return touristRepository.findById(id);
    }

    @Override
    public void save(TouristDTO touristDTO) {
        Tourist tourist = new Tourist();
        tourist.setName(touristDTO.getName());
        tourist.setSurname(touristDTO.getSurname());
        tourist.setSex(touristDTO.getSex());
        tourist.setCountry(touristDTO.getCountry());
        tourist.setDateOfBirth(dateParserService.parseDateFromString(touristDTO.getDateOfBirth()));
        tourist.setNotes(touristDTO.getNotes());
        touristRepository.save(tourist);
    }

    @Override
    public void updateTourist(Long id, TouristDTO touristDTO) {
        Tourist currentTourist = touristRepository.findById(id).get();
        currentTourist.setName(touristDTO.getName());
        currentTourist.setSurname(touristDTO.getSurname());
        currentTourist.setSex(touristDTO.getSex());
        currentTourist.setCountry(touristDTO.getCountry());
        currentTourist.setDateOfBirth(dateParserService.parseDateFromString(touristDTO.getDateOfBirth()));
        currentTourist.setNotes(touristDTO.getNotes());
        touristRepository.save(currentTourist);
    }

    @Override
    public void addFlightToTourist(Long id, FlightDTO flightDTO) {
        Tourist currentTourist = touristRepository.findById(id).get();
        Flight flight = new Flight();
        flight.setStartingDestination(flightDTO.getStartingDestination());
        flight.setFlightStartingTime(dateParserService.parseDateTimeFromString(flightDTO.getFlightStartingTime()));
        flight.setFlightArrivalTime(dateParserService.parseDateTimeFromString(flightDTO.getFlightArrivalTime()));
        flight.setCapacity(flightDTO.getCapacity());
        flight.setPrice(flightDTO.getPrice());
        currentTourist.add(flight);
        touristRepository.save(currentTourist);
    }

    @Override
    public void deleteFlightFromTourist(Long touristId, Long flightId) {
        Tourist currentTourist = touristRepository.findById(touristId).get();
        currentTourist.getFligths().remove(flightId);
        touristRepository.save(currentTourist);
    }


    @Override
    public void deleteById(Long id) {
        touristRepository.deleteById(id);
    }

}
