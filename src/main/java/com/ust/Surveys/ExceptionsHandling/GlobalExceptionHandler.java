package com.ust.Surveys.ExceptionsHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<String> handlQuestionIdNotFoundException(QuestionNotFoundException ex) {

        //String message =  " "+ ex.getMessage();
        return ResponseEntity.status(HttpStatus.OK).body(ex.getMessage());
        //return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    @ExceptionHandler(SurveyNotFoundException.class)
    public ResponseEntity<String> handleSurveyNotFoundException(SurveyNotFoundException ex) {

        String message = " " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
