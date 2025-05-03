-- Удаляем уникальное ограничение, если существует
ALTER TABLE course_tags DROP CONSTRAINT IF EXISTS uc_course_tags_tags;

-- Добавляем составной первичный ключ (если его ещё нет)
ALTER TABLE course_tags ADD CONSTRAINT pk_course_tags PRIMARY KEY (course_id, tags_id);
