CREATE TABLE IF NOT EXISTS passports(
    id SERIAL PRIMARY KEY,
    series VARCHAR(20),
    number VARCHAR(20),
    created TIMESTAMP,
    expired TIMESTAMP
);
CREATE TABLE IF NOT EXISTS peoples(
    id SERIAL PRIMARY KEY,
    name VARCHAR(30),
    surname VARCHAR(30),
    created TIMESTAMP,
    passports_id INT UNIQUE REFERENCES passports(id)
);