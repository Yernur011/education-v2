DO $$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_class WHERE relname = 'course_id_seq') THEN
            CREATE SEQUENCE course_id_seq START 1;
        END IF;
    END $$;


ALTER TABLE course
    ALTER COLUMN id
        SET DEFAULT nextval('course_id_seq');


ALTER SEQUENCE course_id_seq OWNED BY course.id;
