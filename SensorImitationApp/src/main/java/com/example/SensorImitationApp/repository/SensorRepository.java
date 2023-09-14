package com.example.SensorImitationApp.repository;

import com.example.SensorImitationApp.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SensorRepository extends JpaRepository <Sensor, Long> {

     Optional<Sensor> findByName(String name);
}
