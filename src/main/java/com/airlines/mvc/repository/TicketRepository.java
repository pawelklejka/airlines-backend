package com.airlines.mvc.repository;

import com.airlines.mvc.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findByTouristInFlightEmailAndFlightBoundWithTourist_flightId(String email, Long flightId);
}
