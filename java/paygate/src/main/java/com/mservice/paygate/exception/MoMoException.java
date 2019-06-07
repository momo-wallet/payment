package com.mservice.paygate.exception;

public class MoMoException extends Exception {
    public MoMoException(String message) {
        super(message);
    }

    public MoMoException(Throwable cause) {
        super(cause);
    }
}
