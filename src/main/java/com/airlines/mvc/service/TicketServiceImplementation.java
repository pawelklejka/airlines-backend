package com.airlines.mvc.service;

import com.airlines.mvc.DTO.BuyTicketDTO;
import com.airlines.mvc.DTO.TouristDTO;
import com.airlines.mvc.exception.AirlinesError;
import com.airlines.mvc.exception.AirlinesException;
import com.airlines.mvc.model.Flight;
import com.airlines.mvc.model.Ticket;
import com.airlines.mvc.model.Tourist;
import com.airlines.mvc.repository.FlightRepository;
import com.airlines.mvc.repository.TicketRepository;
import com.airlines.mvc.repository.TouristRepository;
import com.airlines.mvc.utils.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
public class TicketServiceImplementation implements TicketService {
    private final TouristRepository touristRepository;
    private final FlightRepository flightRepository;
    private final TicketRepository ticketRepository;
    @Autowired
    private TicketPDFGeneratorService ticketPDFGeneratorService;

    private final MailService mailService;

    public TicketServiceImplementation(TouristRepository touristRepository, FlightRepository flightRepository, TicketRepository ticketRepository, MailService mailService) {
        this.touristRepository = touristRepository;
        this.flightRepository = flightRepository;
        this.ticketRepository = ticketRepository;
        this.mailService = mailService;
    }

    @Override
    public Ticket findById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new AirlinesException(AirlinesError.TICKET_NOT_FOUND));
    }

    @Override
    public Optional<ResponseEntity<byte[]>> buyTicket(BuyTicketDTO buyTicketDTO, HttpServletRequest request, HttpServletResponse response){
        Flight flight = flightRepository.findById(buyTicketDTO.getFlightId())
                .orElseThrow(() -> new AirlinesException(AirlinesError.FLIGHT_NOT_FOUND));
        Tourist tourist = DtoConverter.convertDtoToEntity(buyTicketDTO.getTouristDTO());
        Ticket ticket = new Ticket();
        ticket.setFlightThatTouristIsIn(flight);
        ticket.setTouristInFlight(tourist);
        ticket.setBoardingTime(flight.getFlightStartingTime());
        ticket.setGate("34");
        ticket.setSeat(5L);
        tourist.getTickets().add(ticket);
        ticketRepository.save(ticket);
        touristRepository.save(tourist);
        //TODO ZMIENIC NAZWE TEJ METODY (CZYLI DE FACTO NAZWY ZMIENNYCH W TICKET)
        Long ticketId = ticketRepository.findByTouristInFlightEmailAndFlightBoundWithTourist_flightId(tourist.getEmail(), buyTicketDTO.getFlightId()).getTicketId();
        byte[] ticketPDF = ticketPDFGeneratorService
                .generatePDF(ticketId, request, response)
                .orElseThrow(() -> new AirlinesException(AirlinesError.CAN_NOT_GENERATE_PDF));

        if(buyTicketDTO.isSendMailFlag()){
            try {
                mailService.sendMail(tourist.getEmail(),
                        "Airlines ticket",
                        "<h1> Your ticket for flight: " + buyTicketDTO.getFlightId() + "</h1>",
                        true,
                        ticketPDF);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

        return Optional.of(ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ticket.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(ticketPDF));

    }


}
