CREATE TABLE Sensor(
    id int primary key generated always as identity,
    name varchar(100) not null unique
);

CREATE TABLE Measurement(
    id int primary key generated always as identity,
    value double precision not null,
    raining boolean not null,
    date_time timestamp not null,
    sensor varchar(100) references Sensor(name)
);

DROP TABLE measurement,sensor CASCADE;

TRUNCATE measurement, sensor;