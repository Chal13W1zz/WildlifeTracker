CREATE DATABASE wildlife_tracker;

\c wildlife_tracker;
CREATE TABLE  sightings(
    id serial PRIMARY KEY,
    rangerName VARCHAR,
    endangered BOOLEAN,
    location VARCHAR
);

CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;