package com.nology.carpark;

public class NoSpaceException extends RuntimeException {
    public NoSpaceException(String message) {
        super(message);
    }
}
