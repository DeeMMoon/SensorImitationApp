package com.example.SensorImitationApp.dto;

import java.util.List;

public class MeasurementResponse {

    List<MeasurementDTO> measurementDTOS;

    public MeasurementResponse(List<MeasurementDTO> measurementDTOS) {
        this.measurementDTOS = measurementDTOS;
    }

    public List<MeasurementDTO> getMeasurementDTOS() {
        return measurementDTOS;
    }

    public void setMeasurementDTOS(List<MeasurementDTO> measurementDTOS) {
        this.measurementDTOS = measurementDTOS;
    }
}
