package com.airlines.mvc.exception;

public enum AirlinesError {
    TOURIST_NOT_FOUND("Tourist not found in database"),
    FLIGHT_NOT_FOUND("Flight not found in database"),
    TICKET_NOT_FOUND("Ticket not found in database"),
    CAN_NOT_GENERATE_PDF("Can not generate ticket pdf"),
    OUT_OF_FLIGHT_CAPACITY_ERROR("You can't add tourist because the maximum capacity is reached");


    private String errorInfo;

    AirlinesError(String errorInfo){
        this.errorInfo = errorInfo;
    }

    public String getErrorInfo() {
        return errorInfo;
    }
}
