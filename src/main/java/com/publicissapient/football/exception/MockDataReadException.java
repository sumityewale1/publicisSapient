package com.publicissapient.football.exception;

public class MockDataReadException extends RuntimeException {
    // Custom exception for better clarity
    public MockDataReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
