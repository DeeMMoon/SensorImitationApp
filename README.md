# SensorImitationApp

## About Project

A project using REST API. Simulation of the operation of an IoT server. The server registers weather sensors, saves them to the database and returns measurement results. A temperature diagram is also plotted.
##Stack

-  Spring
-  Jackson
-  Postgres
-  Maven
## API

-  POST
    - /sensors/registration
    - /measurements/add
-  GET
    - /measurements
    - /measurements/rainyDaysCount
## How to use

1. Download the Git repository
2. Set up application.properties (database connection)
3. Start the server
4. Launch the client application
5. Use the API
