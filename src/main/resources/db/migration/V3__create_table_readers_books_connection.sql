CREATE TABLE borrowings (
  id BIGSERIAL PRIMARY KEY,
  reader_id BIGINT NOT NULL REFERENCES readers(id) ON DELETE CASCADE,
  book_id BIGINT NOT NULL REFERENCES books(id) ON DELETE CASCADE,
  borrowed_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  returned_at TIMESTAMPTZ,
  due_date TIMESTAMPTZ,

  CONSTRAINT unique_active_loan UNIQUE (book_id, returned_at)
)