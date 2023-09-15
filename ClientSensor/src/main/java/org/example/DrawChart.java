package org.example;

import org.example.dto.MeasurementDTO;
import org.example.dto.MeasurementResponse;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DrawChart {

    private static final String URL_MEASUREMENT = "http://localhost:8080/measurements";
    public static void main(String[] args) {
        List<Double> temperatures = getTemperatures();
        if (temperatures.isEmpty()){
            System.out.println("List of temperatures is empty");
            System.exit(0);
        }
        draw(temperatures);
    }

    private static List<Double> getTemperatures(){
        final RestTemplate restTemplate = new RestTemplate();
        MeasurementResponse response = restTemplate.getForObject(URL_MEASUREMENT, MeasurementResponse.class);
        if (response == null || response.getMeasurements() == null)
            return Collections.emptyList();
        return response.getMeasurements()
                .stream()
                .map(MeasurementDTO::getValue)
                .collect(Collectors.toList());
    }

    private static void draw(List<Double> temperatures){
        double[] xValues = IntStream.range(0, temperatures.size()).asDoubleStream().toArray();
        double[] yValues = temperatures.stream().mapToDouble(x -> x).toArray();

        XYChart chart = QuickChart.getChart("Temperatures", "x", "y", "temperatures",
            xValues, yValues);
        new SwingWrapper(chart).displayChart();
    }
}
