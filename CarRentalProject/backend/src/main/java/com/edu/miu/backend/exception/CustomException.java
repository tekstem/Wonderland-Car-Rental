package com.edu.miu.backend.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends Exception {
    private String message;

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    public CustomException() {}
}
