ALTER TABLE question
    ADD COLUMN test_id BIGINT REFERENCES test(id) ON DELETE CASCADE;

INSERT INTO test (id, title, state, type, created_by, created_date, last_updated_by, last_updated_date)
VALUES (1, 'Java Basics Test', 'ACTIVE', 'MULTIPLE_CHOICE', 'admin', NOW(), 'admin', NOW());
INSERT INTO question (id, question_number, text, test_id)
VALUES (1, 1, 'What is JVM?', 1),
       (2, 2, 'Which keyword is used to inherit a class in Java?', 1);
INSERT INTO answer (id, text, correct, question_id)
VALUES (1, 'Java Virtual Machine', TRUE, 1),
       (2, 'JavaScript Version Manager', FALSE, 1),
       (3, 'Joint Virtual Memory', FALSE, 1);
INSERT INTO answer (id, text, correct, question_id)
VALUES (4, 'extends', TRUE, 2),
       (5, 'implements', FALSE, 2),
       (6, 'inherits', FALSE, 2);