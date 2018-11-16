package com.neosuniversity.demoweb.exceptions;

import java.util.Date;

public class ExceptionResponse {

    private Date timestamp;
    private String message;
    private String details;
    private int status;
    public ExceptionResponse(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public ExceptionResponse(Date timestamp, String message, String details, int status) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.status=status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public int getStatus() {
        return status;
    }
}
