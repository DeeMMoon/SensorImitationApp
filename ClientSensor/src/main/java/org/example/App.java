package org.example;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final String REGISTRATION_URL = "http://localhost:8080/sensor/registration";
    private static final String ADD_MEASUREMENT_URL = "http://localhost:8080/measurement/add";

    public static void main( String[] args )
    {

    }

    public static void registrySensor(String name)
    {
        Map<String, Object> jasonName = new HashMap<>();
        jasonName.put("name", name);

        makePostRequest(jasonName, REGISTRATION_URL);
    }

    public static void sendMeasurement(String name, double value, boolean isRaining){
        Map<String, Object> measurement = new HashMap<>();
        measurement.put("value", value);
        measurement.put("isRaining", isRaining);
        measurement.put("sensor", Map.of("name", name));

        makePostRequest(measurement, ADD_MEASUREMENT_URL);
    }

    public static void makePostRequest(Map<String, Object> data, String url){

        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity request = new HttpEntity<>(data,httpHeaders);
        restTemplate.postForEntity(url, request, String.class);
    }
}
