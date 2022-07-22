DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id INT PRIMARY KEY NOT NULL,
    name VARCHAR(30),
    age INT,
    email VARCHAR(50)
);