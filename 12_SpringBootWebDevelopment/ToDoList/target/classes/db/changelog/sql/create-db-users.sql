CREATE TABLE users (
    id BIGINT AUTO_INCREMENT,
    login VARCHAR(50) NOT NULL,
    password VARCHAR(512) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    role_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);