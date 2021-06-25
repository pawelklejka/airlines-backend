package com.example.airlines.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<Long, Flight> getFlights() {
        return flights;
    }

    public void setFlights(Map<Long, Flight> flights) {
        this.flights = flights;
    }

    public void add(Flight flight){
        if(flights == null){
            flights = new HashMap<>();
        }else {
            flights.put(flight.getId(), flight);
        }

    }

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH}, fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "reservation",
            joinColumns = @JoinColumn(name = "TOURIST_ID"),
            inverseJoinColumns = @JoinColumn(name = "FLIGHT_ID")
    )
    @MapKey(name = "flightId")
    @JsonIgnore
    private Map<Long, Flight> flights;



}
