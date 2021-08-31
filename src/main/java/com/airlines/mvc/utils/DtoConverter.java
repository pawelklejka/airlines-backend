package com.airlines.mvc.utils;

import com.airlines.mvc.DTO.TouristDTO;
import com.airlines.mvc.model.Tourist;

public class DtoConverter {
    public static Tourist convertDtoToEntity(TouristDTO touristDTO) {
        DateParserService dateParserService = new DateParserServiceImpl();
        Tourist tourist = new Tourist();
        tourist.setName(touristDTO.getName());
        tourist.setSurname(touristDTO.getSurname());
        tourist.setEmail(touristDTO.getEmail());
        tourist.setSex(touristDTO.getSex());
        tourist.setCountry(touristDTO.getCountry());
        tourist.setDateOfBirth(dateParserService.parseDateFromString(touristDTO.getDateOfBirth()));
        tourist.setNotes(touristDTO.getNotes());
        return tourist;
    }
}
