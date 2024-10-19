package com.api.kimatesting.apirest.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ApiResponse {

    private Boolean success;
    private Object data;
    private String message;
    private String error;

    public ApiResponse(Object data, Boolean success, String message) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.error = null;
    }

    public ApiResponse(String error) {
        this.success = false;
        this.data = null;
        this.message = null;
        this.error = error;
    }
}

