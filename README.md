# SensorImitationApp

## About Project

This pet-project using REST API. 
Simulation of the operation of an IoT server. The server registers weather sensors, saves them to the database and returns measurement results. A temperature diagram is also plotted.
## Stack

-  Spring
-  Jackson
-  Postgres
-  Maven
## API

-  POST
    - /sensors/registration - Registering a new sensor
    - /measurements/add - Adding new weather data received from the sensor
-  GET
    - /measurements - Getting all the weather data
    - /measurements/rainyDaysCount - Getting the number of rainy days
## How to use

1. Download the Git repository
2. Set up application.properties (database connection)
3. Start the server
4. Launch the client application
5. Use the API
