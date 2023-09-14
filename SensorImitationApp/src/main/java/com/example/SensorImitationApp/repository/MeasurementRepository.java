package com.example.SensorImitationApp.repository;

import com.example.SensorImitationApp.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
}
