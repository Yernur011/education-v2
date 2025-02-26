CREATE TABLE user_course (
                             id                BIGSERIAL PRIMARY KEY,
                             created_by        VARCHAR(255)                NOT NULL,
                             created_date      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
                             last_updated_by   VARCHAR(255)                NOT NULL,
                             last_updated_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
                             user_id           BIGINT                      NOT NULL,
                             course_id         BIGINT                      NOT NULL,
                             CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
                             CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE CASCADE
);