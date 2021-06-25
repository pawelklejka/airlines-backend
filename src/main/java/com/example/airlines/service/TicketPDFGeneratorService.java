package com.example.airlines.service;

import org.springframework.http.ResponseEntity;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public interface TicketPDFGeneratorService {

    ResponseEntity generatePDF(Long touristId, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
