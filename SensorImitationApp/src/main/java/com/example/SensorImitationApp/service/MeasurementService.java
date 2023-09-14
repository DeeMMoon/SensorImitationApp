package com.example.SensorImitationApp.service;

import com.example.SensorImitationApp.entity.Measurement;
import com.example.SensorImitationApp.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
@Service
public class MeasurementService {

    MeasurementRepository measurementRepository;
    SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    public List<Measurement> fidAll(){
        return measurementRepository.findAll();
    }

    @Transactional
    public void save(Measurement measurement){
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()).get());
        measurement.setDateTime(LocalDateTime.now());
        measurementRepository.save(measurement);
    }
}
