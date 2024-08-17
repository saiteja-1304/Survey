package com.ust.Surveys.ExceptionsHandling;

public class SetNameNotFoundException extends RuntimeException {
    public SetNameNotFoundException(String setName) {
        super("Set name not found: " + setName);
    }

}
