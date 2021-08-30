package com.airlines.mvc.repository;

import com.airlines.mvc.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    Page<Flight> findFlightByStartingDestinationStartsWith(String startingDestination, Pageable pageable);

}
//    @Query("select f from Flight f where f.startingDestination like %?1%")
//    Page<Flight> findByStartingDestination(String startingDestination, Pageable pageable);
