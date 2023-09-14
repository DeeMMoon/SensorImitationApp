CREATE TABLE Sensor(
    id int primary key generated always as identity,
    name varchar(100) not null unique
);

CREATE TABLE Measurement(
    id int primary key generated always as identity,
    value double precision not null,
    raining boolean not null,
    date_time timestamp not null,
    sensor_id int references Sensor(id)
);