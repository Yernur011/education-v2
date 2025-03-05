-- Создаём тест для курса "Raising Sheep"
INSERT INTO test (id, title, state, type, created_by, created_date, last_updated_by, last_updated_date)
VALUES (5, 'Raising Sheep', 'ACTIVE', 'MULTIPLE_CHOICE', 'system', NOW(), 'system', NOW());

-- Создаём курс "Raising Sheep" с привязкой к тесту
INSERT INTO course (id, title, description, base64images_id, created_by, created_date, last_updated_by,
                    last_updated_date, test_id)
VALUES (5, 'Raising Sheep', 'Comprehensive guide to raising healthy and productive sheep.',
        6, 'system', NOW(), 'system', NOW(), 5);

-- Уроки курса "Raising Sheep"
INSERT INTO lesson (lesson_number, course_id, title, video_url, body_text, status, is_completed, created_by,
                    created_date, last_updated_by, last_updated_date)
VALUES (1, 5, 'Sheep Farming Basics', 'https://www.youtube.com/watch?v=gY-5e4KqUeU',
        'Introduction to basic practices in sheep farming.', 'ACTIVE', FALSE, 'system', NOW(), 'system', NOW()),
       (2, 5, 'Feeding and Nutrition', 'https://www.youtube.com/watch?v=fs_X3O-5m7U',
        'Nutritional needs and feeding schedules for sheep.', 'ACTIVE', FALSE, 'system', NOW(), 'system', NOW()),
       (3, 5, 'Health Monitoring & Disease Prevention', 'https://www.youtube.com/watch?v=06jBKJ8y42g',
        'Monitoring health and preventing diseases in sheep.', 'ACTIVE', FALSE, 'system', NOW(), 'system', NOW());

-- Вопросы для теста
INSERT INTO question (id, question_number, text, test_id)
VALUES (14, 1, 'What is the most important factor in the first days of a lamb’s life?', 5),
       (15, 2, 'At what age do lambs typically start eating solid feed?', 5),
       (16, 3, 'Why is it important to dock a lamb’s tail in some breeds?', 5),
       (17, 4, 'What is a crucial factor in preventing diseases in sheep?', 5),
       (18, 5, 'At what age are lambs typically weaned from their mothers?', 5);

-- Варианты ответов для каждого вопроса

-- Вопрос 1
INSERT INTO answer (id, text, correct, question_id)
VALUES (45, 'Providing shelter from predators', FALSE, 14),
       (46, 'Feeding colostrum', TRUE, 14),
       (47, 'Immediate shearing', FALSE, 14),
       (48, 'Keeping them isolated from the mother', FALSE, 14);

-- Вопрос 2
INSERT INTO answer (id, text, correct, question_id)
VALUES (49, 'From the first week', FALSE, 15),
       (50, 'At two months', FALSE, 15),
       (51, 'At three weeks', TRUE, 15),
       (52, 'At six months', FALSE, 15);

-- Вопрос 3
INSERT INTO answer (id, text, correct, question_id)
VALUES (53, 'To improve appearance', FALSE, 16),
       (54, 'To reduce the risk of flystrike', TRUE, 16),
       (55, 'To increase wool production', FALSE, 16),
       (56, 'To help with balance', FALSE, 16);

-- Вопрос 4
INSERT INTO answer (id, text, correct, question_id)
VALUES (57, 'Frequent movement between farms', FALSE, 17),
       (58, 'Proper vaccination and parasite control', TRUE, 17),
       (59, 'Feeding only dry grass', FALSE, 17),
       (60, 'Keeping them in confined spaces', FALSE, 17);

-- Вопрос 5
INSERT INTO answer (id, text, correct, question_id)
VALUES (61, '4–6 weeks', FALSE, 18),
       (62, '8–12 weeks', TRUE, 18),
       (63, '5–6 months', FALSE, 18),
       (64, '10 months', FALSE, 18);

