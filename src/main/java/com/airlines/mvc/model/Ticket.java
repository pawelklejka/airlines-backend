package com.airlines.mvc.model;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;
    private Long gate;
    private Long seat;
    private LocalTime boardingTime;
    private String codeBarQrBar; //TODO IMPLEMENT SERVICE THAT GENERATE QR OR BARCODE

    public Ticket() {


    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getGate() {

        return gate;
    }

    public void setGate(Long gate) {
        this.gate = gate;
    }

    public LocalTime getBoardingTime() {

        return boardingTime;
    }

    public void setBoardingTime() {
        LocalTime boardingTime =
                LocalTime.of(getFlightThatTouristIsIn().getFlightStartingTime().getHour(),
                        getFlightThatTouristIsIn().getFlightStartingTime().getMinute() - 30);
        this.boardingTime = boardingTime;
    }

    public String getCodeBarQrBar() {
        return codeBarQrBar;
    }

    public void setCodeBarQrBar(String codeBarQrBar) {
        this.codeBarQrBar = codeBarQrBar;
    }

    public Tourist getTouristInFlight() {
        return touristInFlight;
    }

    public void setTouristInFlight(Tourist touristInFlight) {
        this.touristInFlight = touristInFlight;
    }

    public Flight getFlightThatTouristIsIn() {
        return flightThatTouristIsIn;
    }

    public void setFlightThatTouristIsIn(Flight flightThatTouristIsIn) {
        this.flightThatTouristIsIn = flightThatTouristIsIn;
    }

    public Long getSeat() {
        return seat;
    }

    public void setSeat(Long seat) {
        this.seat = seat;
    }


    @ManyToOne
    @JoinColumn(name = "tourist_id")
    private Tourist touristInFlight;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flightThatTouristIsIn;



}
