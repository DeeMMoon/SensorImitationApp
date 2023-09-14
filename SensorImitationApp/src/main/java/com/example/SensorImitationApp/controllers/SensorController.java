package com.example.SensorImitationApp.controllers;

import com.example.SensorImitationApp.dto.SensorDTO;
import com.example.SensorImitationApp.entity.Sensor;
import com.example.SensorImitationApp.service.SensorService;
import com.example.SensorImitationApp.exceptions.MeasurementErrorResponses;
import com.example.SensorImitationApp.exceptions.MeasurementException;
import com.example.SensorImitationApp.validate.SensorValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.example.SensorImitationApp.exceptions.CustomErrorClient.errorToClientBuilder;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    private final SensorService sensorService;

    private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorService sensorService, SensorValidator sensorValidator, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){
        Sensor sensor = sensorConverter(sensorDTO);
        sensorValidator.validate(sensor, bindingResult);
        if (bindingResult.hasErrors())
            errorToClientBuilder(bindingResult);
        sensorService.register(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponses> exceptionHandler(MeasurementException e){
        MeasurementErrorResponses measurementErrorResponses = new MeasurementErrorResponses(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(measurementErrorResponses,HttpStatus.BAD_REQUEST);
    }

    private Sensor sensorConverter(SensorDTO sensorDTO){
       return modelMapper.map(sensorDTO, Sensor.class);
    }
}
