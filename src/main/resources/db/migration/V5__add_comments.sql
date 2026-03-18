COMMENT ON COLUMN books.name IS 'Название книги';
COMMENT ON COLUMN books.author IS 'Автор книги';

COMMENT ON COLUMN borrowings.borrowed_at IS 'Когда взяли книгу';
COMMENT ON COLUMN borrowings.returned_at IS 'Когда вернули книгу';
COMMENT ON COLUMN borrowings.due_date IS 'До какой даты надо вернуть книгу';