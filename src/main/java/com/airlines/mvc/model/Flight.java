package com.airlines.mvc.model;

import com.airlines.mvc.DTO.TouristDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

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

    @Column
    @NotNull
    private String finalDestination;


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

    @Transient
    private Set<Tourist> tourists;

    public Flight(){

    }

    public Flight(String startingDestination, LocalDateTime flightStartingTime, LocalDateTime flightArrivalTime,
                  Integer capacity, BigDecimal price){
        this.startingDestination = startingDestination;
        this.flightStartingTime = flightStartingTime;
        this.flightArrivalTime = flightArrivalTime;
        this.capacity = capacity;
        this.tickets = new HashSet<>();
        this.touristAmount = tickets.size();
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
        TouristDTO touristDTO = new TouristDTO();
//        List<TouristDTO> touristsDTO = tickets.stream()
//                .map(ticket -> touristDTO.setName(ticket.));
//
//
//
//        for(Ticket ticket : tickets){
//            TouristDTO touristDTO = new TouristDTO();
//            touristDTO.setId(tourist.getId());
//            touristDTO.setName(tourist.getName());
//            touristDTO.setSurname(tourist.getSurname());
//            touristDTO.setCountry(tourist.getCountry());
//            touristDTO.setSex(tourist.getSex());
//            touristDTO.setDateOfBirth(tourist.getDateOfBirth().toString());
//            touristDTO.setNotes(tourist.getNotes());
//            touristsDTO.add(touristDTO);
//
//        }
        return new ArrayList();
    }

    public String getFinalDestination() {
        return finalDestination;
    }

    public void setFinalDestination(String finalDestination) {
        this.finalDestination = finalDestination;
    }

    /* TODO
    przerobić metody setTourists i addTourist
     */

    public void setTourists(Set <Tourist> tourists) {
        this.tourists = tourists;
        this.setTouristAmount();
    }

    public void add(Tourist tourist){
        if(tourists == null){
            tourists = new HashSet<>();
        }
        tourists.add(tourist);
        this.setTouristAmount();


    }
//    możliwe że do usunięcia
//    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
//            CascadeType.REFRESH}, fetch = FetchType.LAZY
//    )
//    @JoinTable(
//            name = "reservation",
//            joinColumns = @JoinColumn(name = "FLIGHT_ID"),
//            inverseJoinColumns = @JoinColumn(name = "TOURIST_ID")
//    )
//    @MapKey(name = "touristId")
////    @JsonIgnore
//    private Map<Long, Tourist> tourists;


    @OneToMany(mappedBy = "touristInFlight")
    private Set<Ticket> tickets;


}
