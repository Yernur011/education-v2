INSERT INTO question (id, question_number, text, test_id)
VALUES (7, 1, 'What is JVM?', 3),
       (8, 2, 'Which keyword is used to inherit a class in Java?', 2);
INSERT INTO answer (id, text, correct, question_id)
VALUES (19, 'Java Virtual Machine', TRUE, 7),
       (20, 'JavaScript Version Manager', FALSE, 7),
       (21, 'Joint Virtual Memory', FALSE, 7);
INSERT INTO answer (id, text, correct, question_id)
VALUES (22, 'extends', TRUE, 8),
       (23, 'implements', FALSE, 8),
       (24, 'inherits', FALSE, 8);
