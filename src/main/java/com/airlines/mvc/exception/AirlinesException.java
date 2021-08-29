package com.airlines.mvc.exception;

public class AirlinesException extends RuntimeException{
    private AirlinesError airlinesError;

    public AirlinesException(AirlinesError airlinesError){
        this.airlinesError = airlinesError;
    }

    public AirlinesError getAirlinesError() {
        return airlinesError;
    }
}
