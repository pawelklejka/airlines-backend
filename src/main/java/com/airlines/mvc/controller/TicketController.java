package com.airlines.mvc.controller;

import com.airlines.mvc.DTO.BuyTicketDTO;
import com.airlines.mvc.DTO.TouristDTO;
import com.airlines.mvc.exception.AirlinesError;
import com.airlines.mvc.exception.AirlinesException;
import com.airlines.mvc.model.Ticket;
import com.airlines.mvc.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "http://localhost:3000")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/{id}")
    public Ticket findById(@PathVariable("id") Long id){
        return ticketService.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<?> buyTicket(@RequestBody BuyTicketDTO buyTicketDTO,
                                    HttpServletRequest request, HttpServletResponse response){

        return ticketService.buyTicket(buyTicketDTO, request, response)
                .orElseThrow(() -> new AirlinesException(AirlinesError.CAN_NOT_GENERATE_PDF));
    }
}
