package com.example.demo;

import com.example.airlines.controller.FlightController;
import com.example.airlines.model.Flight;
import com.example.airlines.model.Tourist;
import com.example.airlines.repository.FlightRepository;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MocikitoFlightControllerTest {
    @InjectMocks
    private FlightController flightController;

    @Mock
    private FlightRepository flightRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
//                    "[0-2][0-9]-0[0-9]-[0-2] [0-24]:[0-60]"

    @Test
    public void testRepo() {
        Faker faker = new Faker(new Locale("pl"));
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("pl"),
                new RandomService());
        for(int c = 0; c < 30; c++){
            Flight flight= new Flight();

            int capacity = (int)(Math.random() * 11) + 10;
            int touristAmount = (int)(Math.random() * (capacity - 5)) + 1;
            int year = ThreadLocalRandom.current().nextInt(5, 21);
            int month = ThreadLocalRandom.current().nextInt(5, 21);
            int day = ThreadLocalRandom.current().nextInt(5, 21);
            int hour = ThreadLocalRandom.current().nextInt(5, 21);
            int minute = ThreadLocalRandom.current().nextInt(0, 60);;


            flight.setStartingDestination(faker.country().capital());
            flight.setCapacity(capacity);
            flight.setFlightStartingTime(LocalDateTime.of(year, month, day, hour, minute));
            flight.setFlightArrivalTime(LocalDateTime.of(year, month, day, hour + 3, minute + 15));
            flight.setPrice(new BigDecimal(BigInteger.valueOf(new Random().nextInt(100001)), 2));
            for(int counter = touristAmount; counter < capacity; counter++){
                Tourist tourist = new Tourist();
                tourist.setName(faker.name().firstName());
                tourist.setSurname(faker.name().lastName());
                tourist.setDateOfBirth(LocalDate.of((int)(Math.random() * 35) + 1970, (int)(Math.random() * 11) + 1, (int)(Math.random() * 29) + 1));
                tourist.setSex("not specified");
                tourist.setCountry(faker.country().name());
                tourist.setNotes(faker.shakespeare().hamletQuote());
                flight.add(tourist);
            }
            flightRepository.save(flight);

        }
    }
}
