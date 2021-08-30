package com.airlines.mvc.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public interface TicketPDFGeneratorService {

    Optional<byte[]> generatePDF(Long touristId, HttpServletRequest request, HttpServletResponse response);
}
