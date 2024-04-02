package com.meri.murdermysterygame.exception;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatusCode;

public class ObjectNotFoundException extends ChangeSetPersister.NotFoundException {

    private static String errorMessage;
    private static HttpStatusCode statusCode;
    public ObjectNotFoundException(String message, HttpStatusCode code){
        errorMessage = message;
        statusCode = code;
    }

    public static String getErrorMessage() {
        return errorMessage;
    }

    public static void setErrorMessage(String errorMessage) {
        ObjectNotFoundException.errorMessage = errorMessage;
    }

    public static HttpStatusCode getStatusCode() {
        return statusCode;
    }

    public static void setStatusCode(HttpStatusCode statusCode) {
        ObjectNotFoundException.statusCode = statusCode;
    }
}
