package com.reeinvent.backend.exceptions;

import io.grpc.Status;
import io.grpc.StatusException;

public class GRPCException extends StatusException {

    public GRPCException(Status status, String message) {
        super(status.withDescription(message));
    }

    public GRPCException(Status status, String message, Throwable cause) {
        super(status.withDescription(message).withCause(cause));
    }
}
