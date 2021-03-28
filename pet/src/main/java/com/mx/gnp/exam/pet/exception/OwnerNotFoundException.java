package com.mx.gnp.exam.pet.exception;

public class OwnerNotFoundException extends RuntimeException{

    public OwnerNotFoundException(Integer id) {
        super("Could not find owner " + id);
    }
}
