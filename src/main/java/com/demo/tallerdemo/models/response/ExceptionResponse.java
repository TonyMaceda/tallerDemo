package com.demo.tallerdemo.models.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {

    private String message;
    private int status;
    private String error;
    private String timestamp;
}
