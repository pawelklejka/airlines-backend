package com.airlines.mvc.model;

import com.airlines.mvc.DTO.TouristDTO;
import com.airlines.mvc.exception.AirlinesError;
import com.airlines.mvc.exception.AirlinesException;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

//@Profile("flightControllerWebTest")
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FLIGHT_ID")
    private Long flightId;

    @Column(name = "STARTING_DESTINATION")
    @NotBlank
    private String startingDestination;

    @Column
    @NotBlank
    private String finalDestination;


    @Column(name = "FLIGHT_STARTING_TIME")
    @DateTimeFormat(pattern = "yyy-MM-dd hh:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull
    private LocalDateTime flightStartingTime;

    @Column(name = "FLIGHT_ARRIVAL_TIME")
    @DateTimeFormat(pattern = "yyy-MM-dd hh:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
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
//    @DecimalMin(value = "100.00")
    private BigDecimal price;


    public Flight(){

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
        this.touristAmount = tickets.size();
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


    public String getFinalDestination() {
        return finalDestination;
    }

    public void setFinalDestination(String finalDestination) {
        this.finalDestination = finalDestination;
    }


    public void add(Tourist tourist){
        Ticket ticketForTourist = new Ticket();
        ticketForTourist.setFlightThatTouristIsIn(this);
        ticketForTourist.setTouristInFlight(tourist);
        ticketForTourist.setBoardingTime(this.getFlightStartingTime());
        ticketForTourist.setSeat(10l);
        ticketForTourist.setGate("42");
        if(tickets == null){
            tickets = new HashSet<>();
        }else{
            if(tickets.size() <= capacity){
                tickets.add(ticketForTourist);
            }
            else throw new AirlinesException(AirlinesError.OUT_OF_FLIGHT_CAPACITY_ERROR);

        }
        this.setTouristAmount();

    }

    public boolean removeTouristFromFlight(Ticket ticket){
        System.out.println(ticket.getTicketId());
        System.out.println(tickets.contains(ticket));
        System.out.println(tickets.remove(ticket));
        System.out.println(tickets.contains(ticket));
        return tickets.remove(ticket);
    }

    public Set<TouristDTO> getTourists(){
        return tickets.stream()
                .map(ticket -> {
                    TouristDTO touristDTO = new TouristDTO();
                    touristDTO.setId(ticket.getTouristInFlight().getId());
                    touristDTO.setName(ticket.getTouristInFlight().getName());
                    touristDTO.setSurname(ticket.getTouristInFlight().getSurname());
                    touristDTO.setCountry(ticket.getTouristInFlight().getCountry());
                    touristDTO.setDateOfBirth(ticket.getTouristInFlight().getDateOfBirth().toString());
                    touristDTO.setSex(ticket.getTouristInFlight().getSex());
                    touristDTO.setNotes(ticket.getTouristInFlight().getNotes());
                    return touristDTO;
                })
                .collect(Collectors.toSet());
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


    @OneToMany(mappedBy = "flightThatTouristIsIn", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.DETACH}, fetch = FetchType.LAZY)
    private Set<Ticket> tickets;


}
