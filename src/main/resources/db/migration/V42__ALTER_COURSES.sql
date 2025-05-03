-- Создаем join-таблицу для связи многие-ко-многим между course и forum_category
CREATE TABLE course_forum_category (
                                       course_id BIGINT NOT NULL,
                                       forum_category_id INTEGER NOT NULL,
                                       PRIMARY KEY (course_id, forum_category_id)
);

-- Добавляем внешние ключи
ALTER TABLE course_forum_category
    ADD CONSTRAINT fk_course_forum_category_course
        FOREIGN KEY (course_id) REFERENCES course (id);

ALTER TABLE course_forum_category
    ADD CONSTRAINT fk_course_forum_category_forum_category
        FOREIGN KEY (forum_category_id) REFERENCES forum_category (id);

-- Индекс для обратного поиска (по категории)
CREATE INDEX idx_course_forum_category_forum_category_id ON course_forum_category (forum_category_id);