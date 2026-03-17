CREATE INDEX IF NOT EXISTS idx_books_name_lower ON books (LOWER(name));
CREATE INDEX IF NOT EXISTS idx_readers_name_lower ON readers (LOWER(name));