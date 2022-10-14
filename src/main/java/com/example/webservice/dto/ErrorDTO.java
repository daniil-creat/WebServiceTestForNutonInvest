package com.example.webservice.dto;

import lombok.Getter;

@Getter
public class ErrorDTO {
    private String message;

    public ErrorDTO(String message) {
        this.message = message;
    }
}
