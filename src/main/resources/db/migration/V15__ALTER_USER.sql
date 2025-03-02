ALTER TABLE users
    ADD COLUMN user_details bigint;
ALTER TABLE users
    ADD CONSTRAINT fk_user_details
        FOREIGN KEY (user_details) REFERENCES user_details(id)
            ON DELETE CASCADE;
