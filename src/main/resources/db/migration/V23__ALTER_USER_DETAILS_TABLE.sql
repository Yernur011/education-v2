ALTER TABLE user_details
    ALTER COLUMN id SET DEFAULT nextval('user_details_id_seq');

-- Если sequence ещё не существует (например, ты вручную создавал таблицу), нужно создать sequence
CREATE SEQUENCE IF NOT EXISTS user_details_id_seq START 1;

-- Если нужно синхронизировать текущие значения
SELECT setval('user_details_id_seq', COALESCE((SELECT MAX(id) FROM user_details), 1));

-- Убедиться, что PK привязан к sequence (если это слетело)
ALTER TABLE user_details ALTER COLUMN id SET DEFAULT nextval('user_details_id_seq');