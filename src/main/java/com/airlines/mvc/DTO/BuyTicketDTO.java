package com.airlines.mvc.DTO;

public class BuyTicketDTO {
    private Long flightId;
    private TouristDTO touristDTO;
    private boolean sendMailFlag;

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public TouristDTO getTouristDTO() {
        return touristDTO;
    }

    public void setTouristDTO(TouristDTO touristDTO) {
        this.touristDTO = touristDTO;
    }

    public boolean isSendMailFlag() {
        return sendMailFlag;
    }

    public void setSendMailFlag(boolean sendMailFlag) {
        this.sendMailFlag = sendMailFlag;
    }
}
