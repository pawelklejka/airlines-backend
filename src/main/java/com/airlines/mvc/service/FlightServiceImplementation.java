package com.airlines.mvc.service;

import com.airlines.mvc.DTO.FlightDTO;
import com.airlines.mvc.DTO.TouristDTO;
import com.airlines.mvc.exception.AirlinesError;
import com.airlines.mvc.exception.AirlinesException;
import com.airlines.mvc.model.Flight;
import com.airlines.mvc.model.Ticket;
import com.airlines.mvc.model.Tourist;
import com.airlines.mvc.repository.FlightRepository;
import com.airlines.mvc.repository.TouristRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service("flightService")
public class FlightServiceImplementation implements FlightService {
    private final FlightRepository flightRepository;
    private final TouristRepository touristRepository;
    private final DateParserService dateParserService;

    public FlightServiceImplementation(FlightRepository flightRepository, TouristRepository touristRepository, DateParserService dateParserService) {
        this.flightRepository = flightRepository;
        this.touristRepository = touristRepository;
        this.dateParserService = dateParserService;
    }

    @Override
    public Page<Flight> findByStartingDestination(String startingDestination, Pageable pageable) {
        return flightRepository.findFlightByStartingDestinationStartsWith(startingDestination, pageable);
    }

    @Override
    public Page<Flight> findAll(Pageable pageable) {
        return flightRepository.findAll(pageable);
    }

    @Override
    public Flight findById(Long id) {
        return flightRepository.findById(id).orElseThrow(() -> new AirlinesException(AirlinesError.FLIGHT_NOT_FOUND));
    }

    @Override
    public void save(FlightDTO flightDTO) {
        Flight flight = convertDtoToEntity(new Flight(), flightDTO);
        flightRepository.save(flight);
    }

    @Override
    public void updateFlight(Long id, FlightDTO flightDTO) {
        Flight currentFlight = flightRepository.findById(id)
                .map(cf -> convertDtoToEntity(cf, flightDTO))
                .orElseThrow(() -> new AirlinesException(AirlinesError.FLIGHT_NOT_FOUND));
        flightRepository.save(currentFlight);
    }

    @Override
    public void addTouristToFlight(Long id, TouristDTO touristDTO) {
        Flight currentFlight = flightRepository.findById(id).orElseThrow(() -> new AirlinesException(AirlinesError.FLIGHT_NOT_FOUND));
        Tourist tourist = new Tourist();
        tourist.setName(touristDTO.getName());
        tourist.setSurname(touristDTO.getSurname());
        tourist.setEmail(touristDTO.getEmail());
        tourist.setSex(touristDTO.getSex());
        tourist.setCountry(touristDTO.getCountry());
        tourist.setDateOfBirth(dateParserService.parseDateFromString(touristDTO.getDateOfBirth()));
        tourist.setNotes(touristDTO.getNotes());
        tourist.add(currentFlight);
        touristRepository.save(tourist);
        try {
            currentFlight.add(tourist);
        } catch (AirlinesException e) {
            e.printStackTrace();
        }
        flightRepository.save(currentFlight);
    }

    @Override
    public void deleteTouristFromFlight(Long flightId, Long touristId) {
        Flight currentFlight = flightRepository.findById(flightId)
                .orElseThrow(() -> new AirlinesException(AirlinesError.FLIGHT_NOT_FOUND));
        Ticket ticket = touristRepository.findById(touristId).get().getTickets().stream()
                .filter(ticketToBeRemoved -> ticketToBeRemoved.getFlightThatTouristIsIn().getId().equals(currentFlight.getId()))
                .findFirst()
                .orElseThrow(() -> new AirlinesException(AirlinesError.TICKET_NOT_FOUND));
        ticket.setTouristInFlight(null);
        ticket.setFlightThatTouristIsIn(null);
        currentFlight.removeTouristFromFlight(ticket);

        flightRepository.save(currentFlight);
    }


    @Override
    public void deleteById(long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public void fillWithData(Integer amountOfFlight) {
        Faker faker = new Faker(new Locale("pl"));
        for(int c = 0; c < amountOfFlight; c++){
            Flight flight= new Flight();

            Integer capacity = ThreadLocalRandom.current().nextInt(10, 21);
            int touristAmount = ThreadLocalRandom.current().nextInt(1, capacity - 5);
            int year = ThreadLocalRandom.current().nextInt(2020, 2023);
            int month = ThreadLocalRandom.current().nextInt(1, 12);
            int day = ThreadLocalRandom.current().nextInt(1, 28);
            int hour = ThreadLocalRandom.current().nextInt(0, 20);
            int minute = ThreadLocalRandom.current().nextInt(0, 40);


            flight.setCapacity(capacity);
            flight.setStartingDestination(faker.country().capital());
            flight.setFinalDestination("EARTH ORBIT");
            flight.setFlightStartingTime(LocalDateTime.of(year, month, day, hour, minute));
            flight.setFlightArrivalTime(LocalDateTime.of(year, month, day, hour + 3, minute + 15));
            flight.setPrice(new BigDecimal(BigInteger.valueOf(new Random().nextInt(100001)), 2));

            for(int counter = touristAmount; counter < capacity; counter++){
                Faker faker2 = new Faker(new Locale("pl"));

                Tourist tourist = new Tourist();

                tourist.setName(faker2.name().firstName());
                tourist.setSurname(faker2.name().lastName());
                tourist.setEmail(tourist.getSurname().substring(0, 2) + tourist.getName().substring(0, 2) + Integer.valueOf((int)(Math.random() * 11) + 1).toString() + "@mail.com");
                tourist.setDateOfBirth(LocalDate.of((int)(Math.random() * 35) + 1970, (int)(Math.random() * 11) + 1, (int)(Math.random() * 28) + 1));
                tourist.setSex("not specified");
                tourist.setCountry(faker2.country().name());
                tourist.setNotes(faker2.shakespeare().hamletQuote());
                touristRepository.save(tourist);
                flight.add(tourist);


            }
            flight.setTouristAmount();
            flightRepository.save(flight);
        }
    }


    @Override
    public Set<TouristDTO> findTouristsInFlight(Long flightId) {
        return flightRepository.findById(flightId).get().getTourists();
    }

    public Flight convertDtoToEntity(Flight flight, FlightDTO flightDTO){
        flight.setCapacity(flightDTO.getCapacity());
        flight.setStartingDestination(flightDTO.getStartingDestination());
        flight.setFinalDestination(flightDTO.getFinalDestination());
        flight.setFlightStartingTime(dateParserService.parseDateTimeFromString(flightDTO.getFlightStartingTime()));
        flight.setFlightArrivalTime(dateParserService.parseDateTimeFromString(flightDTO.getFlightArrivalTime()));
        flight.setPrice(flightDTO.getPrice());
        return flight;
    }
}
