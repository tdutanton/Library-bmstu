CREATE TABLE books (
    id BIGINT PRIMARY KEY,
    name TEXT NOT NULL,
    author TEXT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT name_not_empty CHECK (LENGTH(TRIM(name)) > 0),
    CONSTRAINT name_not_empty CHECK (LENGTH(TRIM(author)) > 0)
);