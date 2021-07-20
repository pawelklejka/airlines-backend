package com.airlines.mvc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Tourist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOURIST_ID")
    private Long touristId;

    @Column(name = "TOURIST_NAME")
    @NotNull
    @Size(max = 100)
    private String name;

    @Column(name = "TOURIST_SURNAME")
    @NotNull
    @Size(max = 100)
    private String surname;

    @Column(name = "TOURIST_SEX")
    @NotNull
    private String sex;

    @Column(name = "TOURIST_COUNTRY")
    @NotNull
    @Size(max = 75)
    private String country;

    @Column(name = "TOURIST_DATE_OF_BIRTH")
    @DateTimeFormat(pattern = "yyyy-MM-dd", style = "S")
    @JsonFormat(pattern = "YYYY-MM-dd")
    @NotNull
    private LocalDate dateOfBirth;

    @Column(name = "TOURIST_NOTES")
    private String notes;

    public Long getId() {
        return touristId;
    }

    public void setId(Long id) {
        this.touristId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setFlights(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void add(Flight flight){
        Ticket touristTicket = new Ticket();
        touristTicket.setTouristInFlight(this);
        touristTicket.setFlightThatTouristIsIn(flight);
        if(tickets == null){
            tickets = new HashSet<>();
        }else {
            tickets.add(touristTicket);
        }

    }


    //    mozliwe ze do usuniecia
//    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
//            CascadeType.REFRESH}, fetch = FetchType.LAZY
//    )
//    @JoinTable(
//            name = "reservation",
//            joinColumns = @JoinColumn(name = "TOURIST_ID"),
//            inverseJoinColumns = @JoinColumn(name = "FLIGHT_ID")
//    )
//    @MapKey(name = "flightId")
//    @JsonIgnore
//    private Map<Long, Flight> flights;

    @OneToMany(mappedBy = "touristInFlight", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Ticket> tickets;
}
