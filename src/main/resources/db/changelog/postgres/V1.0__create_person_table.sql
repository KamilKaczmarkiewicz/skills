CREATE TABLE person
(
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    company VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL
);
