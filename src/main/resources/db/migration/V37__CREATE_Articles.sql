CREATE TABLE articles (
                          id SERIAL PRIMARY KEY,
                          title TEXT,
                          sub_title TEXT,
                          description TEXT,
                          status TEXT CHECK (status IN ('CREATED', 'PUBLISHED', 'NOT_PUBLISHED'))
);