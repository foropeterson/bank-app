package com.example.banking_system.exception;

import com.example.banking_system.response.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException{
    private final ErrorResponse errorResponse;

    public CustomException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
