DROP TABLE IF EXISTS user;

CREATE SEQUENCE seq_user_id;

CREATE TABLE user
(
    id BIGINT DEFAULT nextval('seq_user_id') PRIMARY KEY,
    name VARCHAR(30),
    age INT,
    email VARCHAR(50)
);