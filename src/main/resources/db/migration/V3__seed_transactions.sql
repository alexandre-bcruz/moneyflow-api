insert into transactions (
  id, user_id, category_id, amount_cents, type, description, occurred_at
)
values
-- ====== PAST WEEK (now - 7d .. now - 1d) ======

-- Income (past)
('11111111-1111-1111-1111-111111111101', '00000000-0000-0000-0000-000000000001',
 '0c9f2f1b-7c8e-4e78-bb3f-9a7d9e000009', 650000, 'INCOME',
 'Salary – Monthly payment', now() - interval '6 days' + interval '09 hours'),

('11111111-1111-1111-1111-111111111102', '00000000-0000-0000-0000-000000000001',
 'c2c1bcd4-4f91-4f25-9a9f-8bca7d9e0002', 220000, 'INCOME',
 'Business – PJ invoice', now() - interval '4 days' + interval '14 hours'),

('11111111-1111-1111-1111-111111111103', '00000000-0000-0000-0000-000000000001',
 '0c9f2f1b-7c8e-4e78-bb3f-9a7d9e00000a', 18500, 'INCOME',
 'Investments – Dividend', now() - interval '2 days' + interval '10 hours'),

-- Expenses (past)
('11111111-1111-1111-1111-111111111201', '00000000-0000-0000-0000-000000000001',
 '7101c9a7-c7d3-4679-a531-e18f3d3e462d', 4590, 'EXPENSE',
 'Food – Lunch at restaurant', now() - interval '6 days' + interval '13 hours'),

('11111111-1111-1111-1111-111111111202', '00000000-0000-0000-0000-000000000001',
 '0c9f2f1b-7c8e-4e78-bb3f-9a7d9e000003', 1290, 'EXPENSE',
 'Transport – Metro ticket', now() - interval '5 days' + interval '08 hours'),

('11111111-1111-1111-1111-111111111203', '00000000-0000-0000-0000-000000000001',
 '5b4b4a10-1e3b-4f0a-9c1a-1c5a7d9e0001', 185000, 'EXPENSE',
 'Housing – Rent', now() - interval '5 days' + interval '09 hours'),

('11111111-1111-1111-1111-111111111204', '00000000-0000-0000-0000-000000000001',
 '0c9f2f1b-7c8e-4e78-bb3f-9a7d9e000005', 23990, 'EXPENSE',
 'Bills – Internet bill', now() - interval '4 days' + interval '11 hours'),

('11111111-1111-1111-1111-111111111205', '00000000-0000-0000-0000-000000000001',
 '0c9f2f1b-7c8e-4e78-bb3f-9a7d9e000004', 8900, 'EXPENSE',
 'Health – Pharmacy', now() - interval '2 days' + interval '19 hours'),

-- ====== FUTURE WEEK (now + 1d .. now + 7d) ======

-- Planned income (future)
('11111111-1111-1111-1111-111111111301', '00000000-0000-0000-0000-000000000001',
 'c2c1bcd4-4f91-4f25-9a9f-8bca7d9e0002', 120000, 'INCOME',
 'Business – Partial payment (scheduled)', now() + interval '3 days' + interval '10 hours'),

('11111111-1111-1111-1111-111111111302', '00000000-0000-0000-0000-000000000001',
 '0c9f2f1b-7c8e-4e78-bb3f-9a7d9e00000b', 2500, 'INCOME',
 'Refund – Credit card cashback', now() + interval '5 days' + interval '09 hours'),

-- Planned expenses (future)
('11111111-1111-1111-1111-111111111401', '00000000-0000-0000-0000-000000000001',
 '9096619b-81b2-492c-89d9-5abe47f2164f', 27520, 'EXPENSE',
 'Travel – Flight payment', now() + interval '2 days' + interval '16 hours'),

('11111111-1111-1111-1111-111111111402', '00000000-0000-0000-0000-000000000001',
 '0c9f2f1b-7c8e-4e78-bb3f-9a7d9e000007', 12990, 'EXPENSE',
 'Entertainment – Cinema tickets', now() + interval '2 days' + interval '20 hours'),

('11111111-1111-1111-1111-111111111403', '00000000-0000-0000-0000-000000000001',
 '0c9f2f1b-7c8e-4e78-bb3f-9a7d9e000006', 19990, 'EXPENSE',
 'Shopping – Clothing', now() + interval '4 days' + interval '15 hours'),

('11111111-1111-1111-1111-111111111404', '00000000-0000-0000-0000-000000000001',
 '0c9f2f1b-7c8e-4e78-bb3f-9a7d9e000008', 5900, 'EXPENSE',
 'Education – Online course', now() + interval '6 days' + interval '12 hours'),

('11111111-1111-1111-1111-111111111405', '00000000-0000-0000-0000-000000000001',
 '7101c9a7-c7d3-4679-a531-e18f3d3e462d', 6790, 'EXPENSE',
 'Food – Grocery shopping', now() + interval '6 days' + interval '18 hours');