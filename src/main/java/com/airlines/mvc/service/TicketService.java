package com.airlines.mvc.service;

import com.airlines.mvc.DTO.BuyTicketDTO;
import com.airlines.mvc.DTO.TouristDTO;
import com.airlines.mvc.model.Ticket;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public interface TicketService {
    Ticket findById(Long ticketId);
    Optional<ResponseEntity<byte[]>> buyTicket(BuyTicketDTO buyTicketDTO, HttpServletRequest request, HttpServletResponse response);
}
