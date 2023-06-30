CREATE TABLE people (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    nick_name VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    occasion VARCHAR(255),
    subscription BOOLEAN NOT NULL
);

Insert into people values (1,'shashank','shan','kingbreath2707@gmail.com','1999-07-27','Birthday',true);
