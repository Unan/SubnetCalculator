package com.apstra.task.exception;

public class EmptySubnetException extends RuntimeException {

    private static final String EMPTY_SUBNET_ERROR_MESSAGE = "Subnet ip address set in is empty";

    public EmptySubnetException() {
        super(EMPTY_SUBNET_ERROR_MESSAGE);
    }
}
