package com.devsuperior.bds02.services.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException (String message) {
        super(message);
    }
}
