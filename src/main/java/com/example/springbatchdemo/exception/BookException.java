package com.example.springbatchdemo.exception;

public class BookException extends RuntimeException {

//    private static final ResponseCode responseCode = ResponseCode.UNDEFINED_ERROR;

    public BookException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookException(String message) {
        super(message);
    }

//    public ResponseCode getResponseCode() {
//        return responseCode;
//    }
}
