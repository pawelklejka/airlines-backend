package com.airlines.mvc.service;

import com.airlines.mvc.model.Ticket;

import java.util.Optional;

interface TicketService {
    Optional<Ticket> getTicket(Long ticketId);
}
