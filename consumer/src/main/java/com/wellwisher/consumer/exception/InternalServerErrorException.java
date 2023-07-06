package com.wellwisher.consumer.exception;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;
    final HttpStatus httpStatus;
	
    public InternalServerErrorException(String message, HttpStatus httpStatus) {
    	super(message);
        this.httpStatus = httpStatus;
    }
    
    public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
