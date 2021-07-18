package com.airlines.mvc.service;

import com.airlines.mvc.model.Ticket;
import com.airlines.mvc.model.Tourist;
import com.airlines.mvc.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketServiceImplementation implements TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Override
    public Optional<Ticket> getTicket(Long ticketId) {
        return ticketRepository.findById(ticketId);
    }

    public void buyTicket(Long flightId, Tourist tourist, boolean isRegistred, boolean wantToRegister){

        if(isRegistred){
            // TODO
            //  jezeli uzytkownik jest zarejestrowany
            // pobierz jego dane
            // stworz Tourist
            // przypisz go do Ticket

        }else {
            // TODO
            //  jezeli uzytkownik nie jest zarejestrowany
            // to sprawdzamy jaka flage otrzymalismy z requesta z formularza
            if(wantToRegister){
                // tworzymy User i dodajemy go do bazy danych uzytkownikow, ktorzy beda mogli sie uwierzytelniac i autentyfikowac
                // tworzymy Tourist i przypisujemy go do biletu
            }else {
                // tworzymy tylko Tourist i przypisujemy go do biletu
            }
        }
    }


}
