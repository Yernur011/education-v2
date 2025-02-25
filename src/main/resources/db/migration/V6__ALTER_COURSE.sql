ALTER TABLE course
    ADD COLUMN test_id BIGINT,
    ADD CONSTRAINT fk_course_test
        FOREIGN KEY (test_id) REFERENCES test(id)
            ON DELETE CASCADE;