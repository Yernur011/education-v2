CREATE TABLE user_course_lesson (
                                    id BIGSERIAL PRIMARY KEY,
                                    created_by        VARCHAR(255)                NOT NULL,
                                    created_date      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
                                    last_updated_by   VARCHAR(255)                NOT NULL,
                                    last_updated_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
                                    user_course_id BIGINT NOT NULL,
                                    lesson_id BIGINT NOT NULL,
                                    user_id BIGINT NOT NULL,
                                    status VARCHAR(255),

                                    CONSTRAINT fk_user_course FOREIGN KEY (user_course_id)
                                        REFERENCES user_course (id) ON DELETE CASCADE,

                                    CONSTRAINT fk_lesson FOREIGN KEY (lesson_id)
                                        REFERENCES lesson (id) ON DELETE CASCADE,

                                    CONSTRAINT fk_users FOREIGN KEY (user_id)
                                        REFERENCES users (id) ON DELETE CASCADE
);