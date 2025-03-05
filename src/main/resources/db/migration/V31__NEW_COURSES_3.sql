-- Создаём тест для курса "A Bull's Diet"
INSERT INTO test (id, title, state, type, created_by, created_date, last_updated_by, last_updated_date)
VALUES (6, 'Raising Bulls', 'ACTIVE', 'MULTIPLE_CHOICE', 'system', NOW(), 'system', NOW());

-- Создаём курс "A Bull's Diet" с привязкой к тесту
INSERT INTO course (id, title, description, base64images_id, created_by, created_date, last_updated_by,
                    last_updated_date, test_id)
VALUES (6, 'A Bull`s Diet', 'Comprehensive guide on proper nutrition, care, and management for raising healthy bulls.',
        7, 'system', NOW(), 'system', NOW(), 6);

-- Уроки курса "A Bull's Diet"
INSERT INTO lesson (lesson_number, course_id, title, video_url, body_text, status, is_completed, created_by,
                    created_date, last_updated_by, last_updated_date)
VALUES (1, 6, 'Introduction to Bull Nutrition', 'https://www.youtube.com/watch?v=pnz8-bajpPQ',
        'Overview of essential dietary needs for healthy bulls.', 'ACTIVE', FALSE, 'system', NOW(), 'system', NOW());

-- Вопросы для теста
INSERT INTO question (id, question_number, text, test_id)
VALUES (19, 1, 'What is the primary factor in ensuring a bull calf`s healthy growth?', 6),
       (20, 2, 'At what age do bulls typically reach sexual maturity?', 6),
       (21, 3, 'What type of diet is essential for a growing bull?', 6),
       (22, 4, 'Why is proper handling and training important for bulls?', 6),
       (23, 5, 'What is a common method used to manage a bull’s temperament?', 6);

-- Варианты ответов для каждого вопроса

-- Вопрос 1
INSERT INTO answer (id, text, correct, question_id)
VALUES (65, 'Immediate separation from the herd', FALSE, 19),
       (66, 'Proper nutrition and balanced diet', TRUE, 19),
       (67, 'Keeping them indoors at all times', FALSE, 19),
       (68, 'Limiting movement to prevent injuries', FALSE, 19);

-- Вопрос 2
INSERT INTO answer (id, text, correct, question_id)
VALUES (69, '6 months', FALSE, 20),
       (70, '12 months', FALSE, 20),
       (71, '18 months', TRUE, 20),
       (72, '24 months', FALSE, 20);

-- Вопрос 3
INSERT INTO answer (id, text, correct, question_id)
VALUES (73, 'Only grass and hay', FALSE, 21),
       (74, 'High-protein diet with grains and minerals', TRUE, 21),
       (75, 'Only fresh vegetables', FALSE, 21),
       (76, 'A diet based mainly on water intake', FALSE, 21);

-- Вопрос 4
INSERT INTO answer (id, text, correct, question_id)
VALUES (77, 'To increase aggression for better defense', FALSE, 22),
       (78, 'To reduce the risk of injuries and ensure safety', TRUE, 22),
       (79, 'To make them run faster', FALSE, 22),
       (80, 'To keep them isolated from humans', FALSE, 22);

-- Вопрос 5
INSERT INTO answer (id, text, correct, question_id)
VALUES (81, 'Regular socialization and controlled exposure to people', TRUE, 23),
       (82, 'Keeping them in dark, enclosed spaces', FALSE, 23),
       (83, 'Restricting their movement to reduce energy levels', FALSE, 23),
       (84, 'Allowing them to roam freely without supervision', FALSE, 23);