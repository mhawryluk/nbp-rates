package com.intern.nbprates;

import java.util.List;
import org.springframework.http.HttpStatus;

public class ApiError {

    private HttpStatus status;
    private String message;
    private String longMessage;
    private List<ApiError> subErrors;

    ApiError(HttpStatus status) {
        this.status = status;
    }

    ApiError(HttpStatus status, Throwable ex) {
        this.status = status;
        this.message = "Unexpected error";
        this.longMessage = ex.getLocalizedMessage();
    }

    ApiError(HttpStatus status, String message, Throwable ex) {
        this.status = status;
        this.message = message;
        this.longMessage = ex.getLocalizedMessage();
    }

    public List<ApiError> getSubErrors() {
        return subErrors;
    }

    public String getLongMessage() {
        return longMessage;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}