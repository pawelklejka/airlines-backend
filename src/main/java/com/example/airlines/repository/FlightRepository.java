package com.example.airlines.repository;

import com.example.airlines.model.Flight;
import com.example.airlines.model.Tourist;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
//    @Query("select f from Flight f where startingDestination like %?1%")
//    Page<Flight> findByStartingDestination(String startingDestination, Pageable pageable);
//
    Page<Flight> findFlightByStartingDestinationStartsWith(String startingDestination, Pageable pageable);

}
