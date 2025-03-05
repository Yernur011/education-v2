-- Удаляем ответы
DELETE FROM answer
WHERE question_id IN (
    SELECT q.id
    FROM question q
             JOIN test t ON q.test_id = t.id
             JOIN course c ON c.test_id = t.id
    WHERE c.id IN (1, 2, 3)
);

-- Удаляем вопросы
DELETE FROM question
WHERE test_id IN (
    SELECT id
    FROM test
    WHERE id IN (
        SELECT test_id
        FROM course
        WHERE id IN (1, 2, 3)
    )
);

-- Удаляем тесты
DELETE FROM test
WHERE id IN (
    SELECT test_id
    FROM course
    WHERE id IN (1, 2, 3)
);

-- Удаляем уроки
DELETE FROM lesson
WHERE course_id IN (1, 2, 3);

-- Удаляем курсы
DELETE FROM course
WHERE id IN (1, 2, 3);