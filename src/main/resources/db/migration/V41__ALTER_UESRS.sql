ALTER TABLE users
    ADD COLUMN category_id_list jsonb DEFAULT '[]';

ALTER TABLE register_user
    ADD COLUMN category_id_list jsonb DEFAULT '[]';