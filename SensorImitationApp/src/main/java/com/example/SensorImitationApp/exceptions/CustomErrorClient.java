package com.example.SensorImitationApp.exceptions;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class CustomErrorClient {
    public static void errorToClientBuilder(BindingResult bindingResult){
        StringBuilder message = new StringBuilder();

        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error:errors) {
            message.append(error.getField())
                    .append("-")
                    .append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
                    .append(";");
        }
        throw new MeasurementException(message.toString());
    }
}
