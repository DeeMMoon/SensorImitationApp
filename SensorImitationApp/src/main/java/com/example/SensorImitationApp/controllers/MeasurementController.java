package com.example.SensorImitationApp.controllers;

import com.example.SensorImitationApp.dto.MeasurementDTO;
import com.example.SensorImitationApp.dto.MeasurementResponse;
import com.example.SensorImitationApp.entity.Measurement;
import com.example.SensorImitationApp.exceptions.CustomErrorClient;
import com.example.SensorImitationApp.exceptions.MeasurementErrorResponses;
import com.example.SensorImitationApp.exceptions.MeasurementException;
import com.example.SensorImitationApp.service.MeasurementService;
import com.example.SensorImitationApp.validate.MeasurementValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.SensorImitationApp.exceptions.CustomErrorClient.errorToClientBuilder;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService, MeasurementValidator measurementValidator, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public MeasurementResponse getAllMeasurement(){
        return new MeasurementResponse(measurementService.fidAll().stream()
                .map(this::measurementDTOConverter)
                .collect(Collectors.toList()));
    }

    @GetMapping("/rainyDaysCount")
    public Long getCountRainyDays(){
        return measurementService.fidAll().stream().filter(Measurement::getRaining).count();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){
        Measurement measurement = measurementConverter(measurementDTO);
        measurementValidator.validate(measurement, bindingResult);
        if(bindingResult.hasErrors())
            errorToClientBuilder(bindingResult);
        measurementService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponses> exceptionHandler(MeasurementException e){
        MeasurementErrorResponses measurementErrorResponses = new MeasurementErrorResponses(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(measurementErrorResponses, HttpStatus.BAD_REQUEST);
    }
    private Measurement measurementConverter(MeasurementDTO measurementDTO){
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO measurementDTOConverter(Measurement measurement){
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
