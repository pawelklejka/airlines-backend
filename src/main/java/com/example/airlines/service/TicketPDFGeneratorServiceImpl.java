package com.example.airlines.service;


import com.example.airlines.model.Flight;
import com.example.airlines.model.Tourist;
import com.google.common.io.Files;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.text.Document;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Optional;

@Service
public class TicketPDFGeneratorServiceImpl  implements TicketPDFGeneratorService{

    @Autowired
    TouristService touristService;
    @Autowired
    ServletContext servletContext;
    @Autowired
    TemplateEngine templateEngine;


    @Override
    public ResponseEntity generatePDF(Long touristId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Optional<Tourist> tourist = touristService.findById(touristId);
        Optional<Flight> flight = Optional.ofNullable(tourist.get().getFlights().remove(0l));

        System.out.println(tourist.get().toString());

        /* Create HTML using Thymeleaf template Engine */

        WebContext context = new WebContext(request, response, servletContext);
        context.setVariable("tourist", tourist.get());
        context.setVariable("flight", flight.get());
        String orderHtml = templateEngine.process("ticket", context);

        /* Setup Source and target I/O streams */

        ByteArrayOutputStream target = new ByteArrayOutputStream();

        /*Setup converter properties. */
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:8081");

        /* Call convert method */
        HtmlConverter.convertToPdf(orderHtml, target, converterProperties);

        /* extract output as bytes */
        byte[] bytes = target.toByteArray();

        Files.write(bytes, new File("resources/template/ticket.pdf"));


        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ticket.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }
}
