package com.apstra.task.exception;

public class IpValidationException extends RuntimeException {

    private static final String VALIDATION_ERROR_MESSAGE = "Invalid ip address: ";

    public IpValidationException(String invalidIpString) {
        super(VALIDATION_ERROR_MESSAGE + quotationMark(invalidIpString));
    }

    private static String quotationMark(String quote) {
        return "'" + quote + "'";
    }
}
