package com.apstra.task.exception;

public class IpDuplicationException extends RuntimeException {

    private static final String DUPLICATION_ERROR_MESSAGE_BEGIN = "Ip address ";
    private static final String DUPLICATION_ERROR_MESSAGE_END = " is duplicated";


    public IpDuplicationException(String duplicatedIpAddress) {
        super(DUPLICATION_ERROR_MESSAGE_BEGIN + quotationMark(duplicatedIpAddress) + DUPLICATION_ERROR_MESSAGE_END);
    }

    private static String quotationMark(String quote) {
        return "'" + quote + "'";
    }
}
