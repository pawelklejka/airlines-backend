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
class TouristControllerTest {
//    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

//    @Autowired
//    TouristController touristController;
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    void findAll() throws Exception{
//        mockMvc.perform(get("/tourist/all")
//                    .queryParam("page", "1"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void findById() throws Exception{
//        mockMvc.perform(get("/tourist/1"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void deleteById() throws Exception{
//        mockMvc.perform(delete("/tourist/1"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void addFlightToTourist() throws Exception{
//        FlightDTO flightDTO = new FlightDTO();
//        flightDTO.setStartingDestination("Berlin");
//        flightDTO.setFlightStartingTime("2020-05-02T14:55");
//        flightDTO.setFlightArrivalTime("2020-05-02T18:55");
//        flightDTO.setCapacity(13);
//        flightDTO.setPrice(new BigDecimal(200.00));
//
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
//        String requestJSON = objectWriter.writeValueAsString(flightDTO);
//
//        mockMvc.perform(patch("/tourist/4")
//                .contentType(APPLICATION_JSON_UTF8)
//                .content(requestJSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void deleteFlightFromTourist() throws Exception{
//        mockMvc.perform(patch("/tourist/delete/2")
//                    .queryParam("flightId","3"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void updateTourist() throws Exception{
//        TouristDTO touristDTO = new TouristDTO();
//        touristDTO.setName("Jan");
//        touristDTO.setSurname("Nowak");
//        touristDTO.setSex("male");
//        touristDTO.setCountry("Poland");
//        touristDTO.setDateOfBirth("1989-03-16");
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
//        String requestJSON = objectWriter.writeValueAsString(touristDTO);
//
//        mockMvc.perform(put("/tourist/4").contentType(APPLICATION_JSON_UTF8)
//                .content(requestJSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void save() throws Exception{
//        TouristDTO touristDTO = new TouristDTO();
//        touristDTO.setName("Karolina");
//        touristDTO.setSurname("Nowak");
//        touristDTO.setSex("female");
//        touristDTO.setCountry("Russia");
//        touristDTO.setDateOfBirth("1988-03-16");
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
//        String requestJSON = objectWriter.writeValueAsString(touristDTO);
//
//        mockMvc.perform(post("/tourist/").contentType(APPLICATION_JSON_UTF8)
//                .content(requestJSON))
//                .andExpect(status().isOk());
//    }
}