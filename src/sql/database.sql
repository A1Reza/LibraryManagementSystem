CREATE DATABASE library_db;

CREATE TABLE member
(
    id        SERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    phone     VARCHAR(20)  NOT NULL
);

CREATE TABLE book
(
    id     SERIAL PRIMARY KEY,
    title  VARCHAR(100)   NOT NULL,
    author VARCHAR(100)   NOT NULL,
    price  NUMERIC(10, 2) NOT NULL,
    stock  INT            NOT NULL
);