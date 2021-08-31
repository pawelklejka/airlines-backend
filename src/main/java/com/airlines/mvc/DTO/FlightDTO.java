package com.airlines.mvc.DTO;


import javax.validation.constraints.*;
import java.math.BigDecimal;


public class FlightDTO {

    private Long id;

    @NotBlank
    private String startingDestination;

    @NotBlank
    private String finalDestination;

    @NotNull
    private String flightStartingTime;

    @NotNull
    private String flightArrivalTime;

    @NotNull
    @Min(10)
    @Max(20)
    private Integer capacity;

    @NotNull
    @DecimalMin(value = "00.00")
    private BigDecimal price;

    public String getStartingDestination() {
        return startingDestination;
    }

    public void setStartingDestination(String startingDestination) {
        this.startingDestination = startingDestination;
    }

    public String getFlightStartingTime() {
        return flightStartingTime;
    }

    public void setFlightStartingTime(String flightStartingTime) {
        this.flightStartingTime = flightStartingTime;
    }

    public String getFlightArrivalTime() {
        return flightArrivalTime;
    }

    public void setFlightArrivalTime(String flightArrivalTime) {
        this.flightArrivalTime = flightArrivalTime;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFinalDestination() {
        return finalDestination;
    }

    public void setFinalDestination(String finalDestination) {
        this.finalDestination = finalDestination;
    }
}
