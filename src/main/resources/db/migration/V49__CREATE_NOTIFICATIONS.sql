CREATE TABLE notifications(
    id          UUID PRIMARY KEY,
    title       TEXT,
    description TEXT,
    start_time  TIMESTAMPTZ,
    user_id     UUID        NOT NULL,
    entity_id   UUID        NOT NULL,
    entity      VARCHAR(20) NOT NULL,
    is_read     BOOLEAN
);