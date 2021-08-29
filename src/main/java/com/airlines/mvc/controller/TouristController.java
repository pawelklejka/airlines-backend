package com.airlines.mvc.controller;

import com.airlines.mvc.DTO.FlightDTO;
import com.airlines.mvc.DTO.TouristDTO;
import com.airlines.mvc.model.Tourist;
import com.airlines.mvc.service.MailService;
import com.airlines.mvc.service.TicketPDFGeneratorService;
import com.airlines.mvc.service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tourists")
public class TouristController {
    private final TouristService touristService;
    private final TicketPDFGeneratorService ticketPDFGeneratorService;
    private final MailService mailService;

    public TouristController(@Qualifier("touristService") TouristService touristService, TicketPDFGeneratorService ticketPDFGeneratorService, MailService mailService) {
        this.touristService = touristService;
        this.ticketPDFGeneratorService = ticketPDFGeneratorService;
        this.mailService = mailService;
    }

    @GetMapping("/")
    public Page<Tourist> findAll(@RequestParam Optional<Integer> page){
        return touristService.findAll(PageRequest.of(page.orElse(0), 21, Sort.by("surname").and(Sort.by("name"))));
    }

    @GetMapping("/{id}")
    public Tourist findById(@PathVariable("id") Long id){
        return touristService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") Long id){
        touristService.deleteById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addTicketToTourist(@PathVariable("id") Long id, @RequestBody FlightDTO flightDTO){
        touristService.addTicketToTourist(id, flightDTO);
    }


    @PatchMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteTicketFromTourist(@PathVariable("id") Long touristId, @RequestParam Long flightId){
        touristService.deleteTicketFromTourist(touristId, flightId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable("id") Long id, @Valid @RequestBody TouristDTO touristDTO){
        touristService.updateTourist(id, touristDTO);

    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody TouristDTO touristDTO){
        touristService.save(touristDTO);
    }

    @GetMapping("/{id}/download-ticket")
    public ResponseEntity<?> getPDF(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ticket.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(ticketPDFGeneratorService.generatePDF(id, request, response));

    }

    @GetMapping("/send-mail")
    public void sendMail() throws MessagingException {
        mailService.sendMail(
                "MatiKawaler96@o2.pl",
                "siema eniu",
                "<b> Wygrales starego wraz z pralka. Pozdrawiam </b>",
                true);
    }
}
