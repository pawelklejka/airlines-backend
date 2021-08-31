package com.airlines.mvc.controller;

import com.airlines.mvc.DTO.FlightDTO;
import com.airlines.mvc.DTO.TouristDTO;
import com.AirlinesApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest(classes = AirlinesApplication.class)
//@AutoConfigureMockMvc
public class FlightControllerWebTest {
//    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

//    @Autowired
//    FlightController flightController;
//
//    @Autowired
//    MockMvc mockMvc;
//
//
//    @Test
//    void findByStartingDestination() throws Exception{
//        mockMvc.perform(get("/flight/searchByDestination")
//                    .queryParam("name", "Dublin")
//                    .queryParam("page", "1"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void findAll() throws Exception{
//        mockMvc.perform(get("/flight/all")
//                        .queryParam("page", "1"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void findById() throws Exception{
//        mockMvc.perform(get("/flight/1"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void deleteById() throws Exception{
//        mockMvc.perform(delete("/flight/1")).andExpect(status().isOk());
//    }
//
//    @Test
//    void saveFlight() throws Exception{
//        FlightDTO flightDTO = new FlightDTO();
//        flightDTO.setStartingDestination("Warsaw");
//        flightDTO.setFlightStartingTime("2020-05-02T13:55");
//        flightDTO.setFlightArrivalTime("2020-05-02T17:55");
//        flightDTO.setCapacity(17);
//        flightDTO.setPrice(new BigDecimal(160.00));
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
//        String requestJSON = objectWriter.writeValueAsString(flightDTO);
//
//        mockMvc.perform(post("/flight/").contentType(APPLICATION_JSON_UTF8)
//                .content(requestJSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void updateFlight() throws Exception{
//        FlightDTO flightDTO = new FlightDTO();
//        flightDTO.setStartingDestination("Warsaw");
//        flightDTO.setFlightStartingTime("2020-05-02T13:55");
//        flightDTO.setFlightArrivalTime("2020-05-02T17:55");
//        flightDTO.setCapacity(17);
//        flightDTO.setPrice(new BigDecimal(160.00));
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
//        String requestJSON = objectWriter.writeValueAsString(flightDTO);
//
//        mockMvc.perform(put("/flight/1").contentType(APPLICATION_JSON_UTF8)
//                .content(requestJSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void deleteTouristFromFlight() throws Exception{
//        mockMvc.perform(patch("/flight/delete/2")
//                        .queryParam("touristId", "5"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void addTouristToFlight() throws Exception{
//        TouristDTO touristDTO = new TouristDTO();
//        touristDTO.setName("Jan");
//        touristDTO.setSurname("Kowalski");
//        touristDTO.setSex("male");
//        touristDTO.setCountry("Poland");
//        touristDTO.setDateOfBirth("1999-02-02");
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
//        String requestJSON = objectWriter.writeValueAsString(touristDTO);
//
//        mockMvc.perform(patch("/flight/4")
//                    .contentType(APPLICATION_JSON_UTF8)
//                    .content(requestJSON))
//                .andExpect(status().isOk());
//    }
}
