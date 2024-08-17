package com.ust.Surveys.ExceptionsHandling;

public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException(Integer id) {
        super("Question with ID " + id + " not found");
    }
}
