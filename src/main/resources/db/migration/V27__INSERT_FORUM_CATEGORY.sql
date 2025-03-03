-- Insert sample data
INSERT INTO forum_category (category_name) VALUES ('General Discussion'), ('Programming'), ('Science');

INSERT INTO forum_question (created_at, status, title, category_id, question_text, author_id, images_id)
VALUES
    (NOW(), 'Approved', 'How to learn Java?', 1, 'What are the best resources to learn Java?', 1, 1),
    (NOW(), 'Created', 'What is Quantum Computing?', 2, 'Can someone explain quantum computing basics?', 1, 1);

INSERT INTO forum_answers (user_id, answer_text, created_date, question_id)
VALUES
    (1, 'You can start with Java tutorials on Oracle website.', NOW(), 1);

INSERT INTO forum_likes (user_id, forum_id, created_at)
VALUES
    (1, 1, NOW());