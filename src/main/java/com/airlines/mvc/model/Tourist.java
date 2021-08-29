package com.airlines.mvc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tourist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOURIST_ID")
    private Long touristId;

    @Column(name = "TOURIST_NAME")
    @NotBlank
    @Size(max = 100)
    private String name;

    @Column(name = "TOURIST_SURNAME")
    @NotBlank
    @Size(max = 100)
    private String surname;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @Column(name = "TOURIST_SEX")
    @NotBlank
    private String sex;

    @Column(name = "TOURIST_COUNTRY")
    @NotBlank
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

    public String getEmail() {
        return email;
    }public void setEmail(String email) {
        this.email = email;
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

    @OneToMany(mappedBy = "touristInFlight", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private Set<Ticket> tickets;
}
