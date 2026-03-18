CREATE INDEX idx_borrowing_reader ON borrowings(reader_id);
CREATE INDEX idx_borrowing_book ON borrowings(book_id);
CREATE INDEX idx_borrowing_active ON borrowings(book_id, returned_at) WHERE returned_at IS NULL;