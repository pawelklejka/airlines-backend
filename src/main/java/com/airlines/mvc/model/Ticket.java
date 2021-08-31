package com.airlines.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;
    @Column(name = "TICKET_GATE")
    private String gate;
    @Column(name = "TICKET_SEAT")
    private Long seat;
    @Column(name = "TICKET_BOARDING_TIME")
    private LocalTime boardingTime;
    @Column(name = "TICKET_QR_CODE")
    @Lob
    private Byte[] codeBarQrBar; //TODO IMPLEMENT SERVICE THAT GENERATE QR OR BARCODE

    public Ticket() {


    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getGate() {

        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public LocalTime getBoardingTime() {

        return boardingTime;
    }

    public void setBoardingTime(LocalDateTime startingTime) {

        LocalTime localTime = LocalTime.of((startingTime.getHour()), startingTime.getMinute());

        this.boardingTime = LocalTime.of(localTime.getHour(), localTime.getMinute()).minusMinutes(30);
    }

    public Byte[] getCodeBarQrBar() {
        return codeBarQrBar;
    }

    public void setCodeBarQrBar(Byte[] codeBarQrBar) {
        this.codeBarQrBar = codeBarQrBar;
    }

    public Tourist getTouristInFlight() {
        return touristInFlight;
    }

    public void setTouristInFlight(Tourist touristInFlight) {
        this.touristInFlight = touristInFlight;
    }

    public Flight getFlightThatTouristIsIn() {
        return flightBoundWithTourist;
    }

    public void setFlightThatTouristIsIn(Flight flightThatTouristIsIn) {
        this.flightBoundWithTourist = flightThatTouristIsIn;
    }

    public Long getSeat() {
        return seat;
    }

    public void setSeat(Long seat) {
        this.seat = seat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(getTicketId(), ticket.getTicketId()) &&
                Objects.equals(getGate(), ticket.getGate()) &&
                Objects.equals(getSeat(), ticket.getSeat()) &&
                Objects.equals(getBoardingTime(), ticket.getBoardingTime()) &&
                Objects.equals(getCodeBarQrBar(), ticket.getCodeBarQrBar()) &&
                Objects.equals(getTouristInFlight(), ticket.getTouristInFlight()) &&
                Objects.equals(getFlightThatTouristIsIn(), ticket.getFlightThatTouristIsIn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTicketId(), getGate(), getSeat(), getBoardingTime(), getCodeBarQrBar(), getTouristInFlight(), getFlightThatTouristIsIn());
    }





    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.DETACH})
    @JoinColumn(name = "flight_id")
    @JsonIgnore
    private Flight flightBoundWithTourist;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.DETACH})
    @JoinColumn(name = "tourist_id")
    @JsonIgnore
    private Tourist touristInFlight;

}
