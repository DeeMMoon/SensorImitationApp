package org.example;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final String REGISTRATION_URL = "http://localhost:8080/sensor/registration";
    private static final String ADD_MEASUREMENT_URL = "http://localhost:8080/measurements/add";

    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in);
        Scanner scannerName =  new Scanner(System.in);
        String menuResult = "1";
        while (!menuResult.equals("3")) {
            showMenu();
            menuResult = scanner.nextLine();
            switch (menuResult) {
                case "1": {
                    System.out.println("Input sensor name");
                    String name = scannerName.nextLine();
                    registrySensor(name);
                    break;
                }
                case "2": {
                    System.out.println("Input sensor name");
                    String name = scannerName.nextLine();
                    System.out.println("Input count measurement");
                    Scanner scannerCountValues = new Scanner(System.in);
                    Integer count = scannerCountValues.nextInt();
                    generateMeasurement(name, count);
                    break;
                }
                case "3":{
                    System.out.println("Good bye!");
                    System.exit(0);
                }

                default:
                    System.out.println("Wrong command! Try again!");
            }
        }
    }

    public static void registrySensor(String sensorName)
    {
        Map<String, Object> jasonName = new HashMap<>();
        jasonName.put("name", sensorName);

        makePostRequest(jasonName, REGISTRATION_URL);
    }

    public static void sendMeasurement(String name, double value, boolean isRaining){
        Map<String, Object> measurement = new HashMap<>();
        measurement.put("value", value);
        measurement.put("raining", isRaining);
        measurement.put("sensor", Map.of("name", name));

        makePostRequest(measurement, ADD_MEASUREMENT_URL);
    }

    public static void makePostRequest(Map<String, Object> data, String url){
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(data,httpHeaders);
        try {
            restTemplate.postForObject(url, request, String.class);
            System.out.println("The measurement was successfully sent to the server");
        }catch (HttpClientErrorException e){
            System.err.println("Error " + e.getMessage());
        }
    }

    private static void showMenu(){
        System.out.println("1. Register new sensor");
        System.out.println("2. Send measurements");
        System.out.println("3. Exit");
        System.out.println("Choose variant");
    }

    private static void generateMeasurement(String sensorName, int count){
        Random random = new Random();
        double min = -30.0;
        double max = 30.0;
        double temperature =  min + (max - min) * random.nextDouble();
        for (int i = 0; i < count; i++){
            sendMeasurement(sensorName, temperature, random.nextBoolean());
        }
    }


}
