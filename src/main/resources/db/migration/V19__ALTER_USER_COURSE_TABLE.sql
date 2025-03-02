ALTER TABLE postgres.public.user_course
    ADD COLUMN status VARCHAR(255) DEFAULT 'ACTIVE';
