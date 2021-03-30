CREATE TABLE IF NOT EXISTS tasks (
     id BIGINT AUTO_INCREMENT,
        description VARCHAR(512) NOT NULL,
        duration INTEGER,
        author_id BIGINT NOT NULL,
        responsible_id BIGINT NOT NULL,
        PRIMARY KEY (id)
)