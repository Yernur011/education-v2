ALTER TABLE user_course_lesson
    DROP COLUMN IF EXISTS user_id;

ALTER TABLE user_course_lesson
    DROP CONSTRAINT IF EXISTS fk_users;