package com.example.airlines.service;

import com.DemoApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest(classes = DemoApplication.class)
public class DateParserServiceTest {

    @Autowired
    private DateParserService dateParserService;

    @Test
    void parseDateFromStringTest(){
        String stringDate = "1999-02-02";

        Assertions.assertEquals(LocalDate.of(1999, 2, 2), dateParserService.parseDateFromString(stringDate));
    }

    @Test
    void parseDateTimeFromStringTest(){
        String stringDateTime = "2003-05-02T13:55";

        Assertions.assertEquals(LocalDateTime.of(2003,5,2,13,55), dateParserService.parseDateTimeFromString(stringDateTime));
    }
}
