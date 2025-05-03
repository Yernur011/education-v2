ALTER TABLE forum_question DROP CONSTRAINT IF EXISTS forum_question_category_id_fkey;
ALTER TABLE forum_question DROP COLUMN IF EXISTS category_id;