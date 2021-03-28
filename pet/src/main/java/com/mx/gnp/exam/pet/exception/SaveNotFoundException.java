package com.mx.gnp.exam.pet.exception;

public class SaveNotFoundException extends RuntimeException {

    public SaveNotFoundException(final String errorDescription) {
        super("Could not save: " + errorDescription);
    }
}
