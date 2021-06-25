package com.example.airlines.model;

import com.example.airlines.DTO.TouristDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.annotation.Profile;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Profile("flightControllerWebTest")
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FLIGHT_ID")
    private Long flightId;

    @Column(name = "STARTING_DESTINATION")
    @NotNull
    private String startingDestination;

    @Column(name = "FLIGHT_STARTING_TIME")
    @DateTimeFormat(pattern = "yyy-MM-dd hh:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @NotNull
    private LocalDateTime flightStartingTime;

    @Column(name = "FLIGHT_ARRIVAL_TIME")
    @DateTimeFormat(pattern = "yyy-MM-dd hh:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @NotNull
    private LocalDateTime flightArrivalTime;

    @Column(name = "FLIGHT_CAPACITY")
    @NotNull
    @Min(10)
    @Max(20)
    private Integer capacity;

    @Column(name = "FLIGHT_TOURIST_AMOUNT")
    private Integer touristAmount;

    @Column(name = "FLIGHT_PRICE")
    @NotNull
    @DecimalMin(value = "100.00")
    private BigDecimal price;

    public Flight(){

    }

    public Flight(String startingDestination, LocalDateTime flightStartingTime, LocalDateTime flightArrivalTime,
                  Integer capacity, BigDecimal price){
        this.startingDestination = startingDestination;
        this.flightStartingTime = flightStartingTime;
        this.flightArrivalTime = flightArrivalTime;
        this.capacity = capacity;
        this.tourists = new HashMap<>();
        this.touristAmount = tourists.size();
        this.price = price;
    }

    public Long getId() {
        return flightId;
    }

    public void setId(Long id) {
        this.flightId = id;
    }

    public String getStartingDestination() {
        return startingDestination;
    }

    public void setStartingDestination(String startingDestination) {
        this.startingDestination = startingDestination;
    }

    public LocalDateTime getFlightStartingTime() {
        return flightStartingTime;
    }

    public void setFlightStartingTime(LocalDateTime flightStartingTime) {
        this.flightStartingTime = flightStartingTime;
    }

    public LocalDateTime getFlightArrivalTime() {
        return flightArrivalTime;
    }

    public void setFlightArrivalTime(LocalDateTime flightArrivalTime) {
        this.flightArrivalTime = flightArrivalTime;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;

    }

    public void setTouristAmount() {
        this.touristAmount = tourists.size();
    }

    public Integer getTouristAmount() {
        return touristAmount;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List getTourists() {
        List<TouristDTO> touristsDTO = new ArrayList<>();
        for(Map.Entry<Long, Tourist> entry : tourists.entrySet()){
            TouristDTO touristDTO = new TouristDTO();
            touristDTO.setId(entry.getKey());
            touristDTO.setName(entry.getValue().getName());
            touristDTO.setSurname(entry.getValue().getSurname());
            touristDTO.setCountry(entry.getValue().getCountry());
            touristDTO.setSex(entry.getValue().getSex());
            touristDTO.setDateOfBirth(entry.getValue().getDateOfBirth().toString());
            touristDTO.setNotes(entry.getValue().getNotes());
            touristsDTO.add(touristDTO);

        }
        return touristsDTO;
    }

    public void setTourists(Map<Long, Tourist> tourists) {
        this.tourists = tourists;
        this.setTouristAmount();
    }

    public void add(Tourist tourist){
        if(tourists == null){
            tourists = new HashMap<>();
        }
        tourists.put(tourist.getId(),tourist);
        this.setTouristAmount();


    }

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH}, fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "reservation",
            joinColumns = @JoinColumn(name = "FLIGHT_ID"),
            inverseJoinColumns = @JoinColumn(name = "TOURIST_ID")
    )
    @MapKey(name = "touristId")
//    @JsonIgnore
    private Map<Long, Tourist> tourists;

}
