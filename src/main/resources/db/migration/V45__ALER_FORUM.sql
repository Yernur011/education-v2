-- Добавляем колонку tag_id
ALTER TABLE forum_question
    ADD COLUMN tag_id BIGINT;

-- Добавляем внешний ключ
ALTER TABLE forum_question
    ADD CONSTRAINT fk_forum_question_tag
        FOREIGN KEY (tag_id)
            REFERENCES tags(id)
            ON DELETE SET NULL;
