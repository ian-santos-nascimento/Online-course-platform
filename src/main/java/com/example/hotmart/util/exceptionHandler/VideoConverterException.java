package com.example.hotmart.util.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class VideoConverterException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public VideoConverterException(String exception) {
        super(exception);
    }

    public VideoConverterException() {
        super("An error occurred while manipulating the video");
    }
}
