package com.akaene.stpa.scs.exception;

public class ControlStructureParserException extends RuntimeException {

    public ControlStructureParserException(String message) {
        super(message);
    }

    public ControlStructureParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
