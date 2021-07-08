package com.airlines.mvc.service;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface TicketPDFGeneratorService {

    ResponseEntity generatePDF(Long touristId, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
