create extension if not exists "uuid-ossp";

create table users (
  id uuid primary key default uuid_generate_v4(),
  name varchar(120) not null,
  email varchar(180) not null unique,
  password_hash varchar(255) not null,
  created_at timestamptz not null default now()
);

create table categories (
  id uuid primary key default uuid_generate_v4(),
  user_id uuid not null references users(id) on delete cascade,
  name varchar(80) not null,
  created_at timestamptz not null default now(),
  unique (user_id, name)
);

create type transaction_type as enum ('INCOME', 'EXPENSE');

create table transactions (
  id uuid primary key default uuid_generate_v4(),
  user_id uuid not null references users(id) on delete cascade,
  category_id uuid not null references categories(id),
  amount_cents int not null check (amount_cents > 0),
  type transaction_type not null,
  description varchar(255),
  occurred_at timestamptz not null,
  created_at timestamptz not null default now()
);

create table weekly_budgets (
  id uuid primary key default uuid_generate_v4(),
  user_id uuid not null references users(id) on delete cascade,
  category_id uuid not null references categories(id),
  week_start date not null,
  limit_cents int not null check (limit_cents > 0),
  created_at timestamptz not null default now(),
  unique (user_id, category_id, week_start)
);

create index idx_transactions_user_occurred_at on transactions(user_id, occurred_at);
create index idx_transactions_user_category_occurred_at on transactions(user_id, category_id, occurred_at);
create index idx_weekly_budgets_user_week_start on weekly_budgets(user_id, week_start);

insert into users (id, name, email, password_hash)
values (
  '00000000-0000-0000-0000-000000000001',
  'MVP User',
  'mvp@moneyflow.dev',
  'not_used'
);