-- 1. Удалить уникальное ограничение
ALTER TABLE course_tags DROP CONSTRAINT uc_course_tags_tags;

-- 2. Добавить составной первичный ключ или уникальное ограничение на пару
ALTER TABLE course_tags ADD CONSTRAINT pk_course_tags PRIMARY KEY (course_id, tags_id);
