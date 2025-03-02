CREATE TABLE user_test
(
    id                BIGSERIAL PRIMARY KEY,
    created_by        VARCHAR(255)                NOT NULL,
    created_date      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    last_updated_by   VARCHAR(255)                NOT NULL,
    last_updated_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    user_id           BIGINT                      NOT NULL,
    test_id           BIGINT                      NOT NULL,
    correct_answered  INTEGER,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_test FOREIGN KEY (test_id) REFERENCES test (id) ON DELETE CASCADE

);