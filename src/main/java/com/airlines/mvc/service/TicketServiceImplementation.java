package com.airlines.mvc.service;

import com.airlines.mvc.model.Ticket;
import com.airlines.mvc.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketServiceImplementation implements TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Override
    public Optional<Ticket> getTicket(Long ticketId) {
        return ticketRepository.findById(ticketId);
    }


}
