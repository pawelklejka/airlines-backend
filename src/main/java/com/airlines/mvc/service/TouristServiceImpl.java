package com.airlines.mvc.service;

import com.airlines.mvc.DTO.FlightDTO;
import com.airlines.mvc.DTO.TouristDTO;
import com.airlines.mvc.exception.AirlinesError;
import com.airlines.mvc.exception.AirlinesException;
import com.airlines.mvc.model.Flight;
import com.airlines.mvc.model.Ticket;
import com.airlines.mvc.model.Tourist;
import com.airlines.mvc.repository.FlightRepository;
import com.airlines.mvc.repository.TicketRepository;
import com.airlines.mvc.repository.TouristRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("touristService")
public class TouristServiceImpl implements TouristService {

    private final TouristRepository touristRepository;
    private final DateParserService dateParserService;
    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;

    public TouristServiceImpl(TouristRepository touristRepository, DateParserService dateParserService, TicketRepository ticketRepository, FlightRepository flightRepository) {
        this.touristRepository = touristRepository;
        this.dateParserService = dateParserService;
        this.ticketRepository = ticketRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    public Page<Tourist> findAll(Pageable pageable) {
        return touristRepository.findAll(pageable);
    }

    @Override
    public Tourist findById(Long id) {
        return touristRepository.findById(id).orElseThrow(() -> new AirlinesException(AirlinesError.TOURIST_NOT_FOUND));
    }

    @Override
    public void save(TouristDTO touristDTO) {
        Tourist tourist = convertDtoToEntity(touristDTO);
        touristRepository.save(tourist);
    }

    @Override
    public void updateTourist(Long id, TouristDTO touristDTO) {
        Tourist currentTourist = touristRepository.findById(id).
                map(tourist -> convertDtoToEntity(touristDTO))
                .orElseThrow(() -> new AirlinesException(AirlinesError.TOURIST_NOT_FOUND));
        touristRepository.save(currentTourist);
    }

    @Override
    public void addTicketToTourist(Long id, FlightDTO flightDTO) {
//        Tourist currentTourist = touristRepository.findById(id).get();
//        Flight flight = new Flight();
//        flight.setStartingDestination(flightDTO.getStartingDestination());
//        flight.setFlightStartingTime(dateParserService.parseDateTimeFromString(flightDTO.getFlightStartingTime()));
//        flight.setFlightArrivalTime(dateParserService.parseDateTimeFromString(flightDTO.getFlightArrivalTime()));
//        flight.setCapacity(flightDTO.getCapacity());
//        flight.setPrice(flightDTO.getPrice());
//        currentTourist.add(flight);
//        touristRepository.save(currentTourist);
    }

    @Override
    public void deleteTicketFromTourist(Long touristId, Long flightId) {
        Tourist tourist = touristRepository.findById(touristId)
                .orElseThrow(() -> new AirlinesException(AirlinesError.TOURIST_NOT_FOUND));

        Ticket ticket = tourist.getTickets().stream()
                .filter(t -> t.getFlightThatTouristIsIn().getId().equals(flightId))
                .findFirst()
                .orElseThrow(() -> new AirlinesException(AirlinesError.TICKET_NOT_FOUND));
        tourist.getTickets().remove(ticket);
        ticketRepository.deleteById(ticket.getTicketId());
        touristRepository.save(tourist);
    }


    @Override
    public void deleteById(Long id) {

        touristRepository.deleteById(id);
    }

    private Tourist convertDtoToEntity(TouristDTO touristDTO) {
        Tourist tourist = new Tourist();
        tourist.setName(touristDTO.getName());
        tourist.setSurname(touristDTO.getSurname());
        tourist.setEmail(touristDTO.getEmail());
        tourist.setSex(touristDTO.getSex());
        tourist.setCountry(touristDTO.getCountry());
        tourist.setDateOfBirth(this.dateParserService.parseDateFromString(touristDTO.getDateOfBirth()));
        tourist.setNotes(touristDTO.getNotes());
        return tourist;
    }

}
