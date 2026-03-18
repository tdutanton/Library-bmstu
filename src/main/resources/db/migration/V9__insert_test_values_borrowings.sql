INSERT INTO borrowings (reader_id, book_id, borrowed_at, due_date, returned_at) VALUES
    (1, 1, NOW() - INTERVAL '10 days', NOW() + INTERVAL '4 days', NULL),
    (1, 3, NOW() - INTERVAL '5 days',  NOW() + INTERVAL '9 days', NULL),
    (2, 2, NOW() - INTERVAL '15 days', NOW() - INTERVAL '1 day', NULL),
    (3, 4, NOW() - INTERVAL '2 days',  NOW() + INTERVAL '12 days', NULL),
    (4, 5, NOW() - INTERVAL '1 day',   NOW() + INTERVAL '13 days', NULL),
    (2, 6, NOW() - INTERVAL '30 days', NOW() - INTERVAL '16 days', NOW() - INTERVAL '16 days'),
    (3, 7, NOW() - INTERVAL '25 days', NOW() - INTERVAL '11 days', NOW() - INTERVAL '11 days'),
    (5, 8, NOW() - INTERVAL '20 days', NOW() - INTERVAL '6 days',  NOW() - INTERVAL '6 days'),
    (1, 6, NOW() - INTERVAL '60 days', NOW() - INTERVAL '46 days', NOW() - INTERVAL '46 days'),
    (1, 7, NOW() - INTERVAL '45 days', NOW() - INTERVAL '31 days', NOW() - INTERVAL '31 days');