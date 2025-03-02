CREATE TABLE IF NOT EXISTS user_details (
                              id BIGSERIAL PRIMARY KEY,
                              lastname VARCHAR(255) NOT NULL,
                              images_id BIGINT UNIQUE,
                              CONSTRAINT fk_images FOREIGN KEY (images_id) REFERENCES base64images(id) ON DELETE CASCADE);
);
