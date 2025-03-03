ALTER TABLE users
    DROP COLUMN IF EXISTS user_details;

ALTER TABLE users
    ADD COLUMN lastname VARCHAR(255);

ALTER TABLE users
    ADD COLUMN image_id BIGINT;

ALTER TABLE users
    ADD CONSTRAINT fk_users_image
        FOREIGN KEY (image_id)
            REFERENCES base64images (id)
            ON DELETE SET NULL;

DROP TABLE IF EXISTS user_details CASCADE;