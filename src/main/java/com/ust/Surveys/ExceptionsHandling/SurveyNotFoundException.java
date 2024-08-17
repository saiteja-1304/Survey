package com.ust.Surveys.ExceptionsHandling;

public class SurveyNotFoundException extends RuntimeException {
    public SurveyNotFoundException(String message) {
        super(message);
    }
}
