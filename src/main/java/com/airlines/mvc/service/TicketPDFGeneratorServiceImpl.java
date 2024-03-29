package com.airlines.mvc.service;


import com.airlines.mvc.exception.AirlinesError;
import com.airlines.mvc.exception.AirlinesException;
import com.airlines.mvc.model.Ticket;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class TicketPDFGeneratorServiceImpl implements TicketPDFGeneratorService {
    private final TicketService ticketService;
    private final ServletContext servletContext;
    private final TemplateEngine templateEngine;

    public TicketPDFGeneratorServiceImpl(TicketService ticketService, ServletContext servletContext, TemplateEngine templateEngine) {
        this.ticketService = ticketService;
        this.servletContext = servletContext;
        this.templateEngine = templateEngine;
    }


    @Override
    public Optional<byte[]> generatePDF(Long ticketId, HttpServletRequest request, HttpServletResponse response)  {
        Ticket ticket = ticketService.findById(ticketId);

        System.out.println(ticket.toString());

        /* Create HTML using Thymeleaf template Engine */

        WebContext context = new WebContext(request, response, servletContext);
        context.setVariable("ticket", ticket);
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
        //cos nie zapisuje
//        Files.write(bytes, new File("resources/template/ticket.pdf"));


        return Optional.ofNullable(bytes);
    }
}
