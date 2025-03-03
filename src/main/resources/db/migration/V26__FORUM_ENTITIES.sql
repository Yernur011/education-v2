CREATE TABLE forum_category (
                                id SERIAL PRIMARY KEY,
                                category_name VARCHAR(255) NOT NULL
);

CREATE TABLE forum_question (
                                id SERIAL PRIMARY KEY,
                                created_at TIMESTAMP DEFAULT NOW(),
                                status VARCHAR(255),
                                title VARCHAR(255) NOT NULL,
                                category_id INT REFERENCES forum_category(id) ON DELETE SET NULL,
                                images_id INT REFERENCES base64images(id) ON DELETE SET NULL,
                                question_text TEXT,
                                author_id INT REFERENCES users(id) ON DELETE SET NULL
);

CREATE TABLE forum_answers (
                               id SERIAL PRIMARY KEY,
                               user_id INT REFERENCES users(id) ON DELETE SET NULL,
                               answer_text TEXT NOT NULL,
                               created_date TIMESTAMP DEFAULT NOW(),
                               question_id INT REFERENCES forum_question(id) ON DELETE CASCADE
);


CREATE TABLE forum_likes (
                             id SERIAL PRIMARY KEY,
                             user_id INT REFERENCES users(id) ON DELETE CASCADE,
                             forum_id INT REFERENCES forum_question(id) ON DELETE CASCADE,
                             created_at TIMESTAMP DEFAULT NOW()
);
