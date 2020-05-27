DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users
(
    id                  BIGSERIAL PRIMARY KEY,
    username            VARCHAR(30) UNIQUE NOT NULL,
    password            VARCHAR(255)       NOT NULL,
    email               VARCHAR(255)       NOT NULL
);
