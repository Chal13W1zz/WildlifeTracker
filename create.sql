CREATE DATABASE wildlife_tracker;

\c wildlife_tracker;
CREATE TABLE  sightings(
    id serial PRIMARY KEY,
    rangerName VARCHAR,
    endangered BOOLEAN,
    location VARCHAR,
    sightingTime timestamp
);
CREATE TABLE animals (id serial PRIMARY KEY, animalName varchar, health varchar, animalAge varchar, sightingId int);

CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;