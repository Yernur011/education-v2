-- Создание последовательности
CREATE SEQUENCE base64docs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Создание таблицы Base64Docs
CREATE TABLE base64docs
(
    id         BIGINT       NOT NULL DEFAULT nextval('base64docs_id_seq'),
    filename   VARCHAR(255) NOT NULL,
    base64Docs TEXT         NOT NULL,
    PRIMARY KEY (id)
);