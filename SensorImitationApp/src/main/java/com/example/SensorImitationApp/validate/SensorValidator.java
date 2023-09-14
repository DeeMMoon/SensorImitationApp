package com.example.SensorImitationApp.validate;

import com.example.SensorImitationApp.entity.Sensor;
import com.example.SensorImitationApp.repository.SensorRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    SensorRepository sensorRepository;

    public SensorValidator(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        if(sensorRepository.findByName(sensor.getName()).isPresent())
            errors.rejectValue("name", "", "Sensor with this name already exist");
    }
}
