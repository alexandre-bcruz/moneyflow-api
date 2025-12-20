# MoneyFlow API

MoneyFlow is a **personal finance backend MVP** focused on **weekly cash flow tracking**, designed to demonstrate **clean architecture**, **financial correctness**, and **real-world backend trade-offs**.

The project intentionally prioritizes **domain clarity**, **money handling correctness**, and **testability** over feature volume.

---

## ✨ Key Concepts

- Weekly-based financial summaries
- All monetary values stored in **cents** to avoid floating-point precision issues
- Clear separation between **domain** and **presentation**
- Hexagonal / Clean Architecture inspired structure
- Realistic seed data for development and API exploration

---

## 🛠️ Tech Stack

- Java 21
- Spring Boot 4
- Maven
- PostgreSQL

---

## 🧱 Architecture Overview

The project follows a **clean / hexagonal architecture approach**:

- **Domain**
    - Core business models (`Transaction`, `Category`)
    - Business invariants and rules
- **Application (Use Cases)**
    - Orchestrates business logic
    - Weekly aggregation and summaries
- **Adapters**
    - Web (REST controllers, DTOs)
    - Persistence (PostgreSQL)

Money formatting and currency conversion are handled **only at the API boundary**, keeping the domain free from presentation concerns.

---

## 💰 Money Handling Strategy

- Monetary values are **persisted as integers in cents** (`amount_cents`)
- All calculations are done using integers or `BigDecimal`
- Conversion to currency format (e.g. BRL) happens only in the web layer

This approach avoids precision issues and keeps the system ready for **multi-currency support**.

---

## 📊 Current Features

### Categories
- CRUD for user categories
- Uniqueness enforced per user (`user_id + name`)
- Used as the basis for aggregation and budgeting

### Transactions
- Full CRUD for income and expense transactions
- All transactions are associated with:
    - user
    - category
    - occurrence timestamp
- Stored internally in cents, exposed as currency in the API
- Supports listing and filtering by category

### Weekly Summary
- Aggregates transactions within a weekly window
- Calculates:
    - total income
    - total expense
    - balance
    - top expense categories with percentage share
- Designed to reflect real financial reporting logic

---

## 🔌 API Endpoints

### Health
- `GET /healthz` — application health check

---

### Categories
- `POST /categories` — create a new category
- `GET /categories` — list categories
- `GET /categories/{id}` — get category details
- `DELETE /categories/{id}` — remove a category

---

### Transactions
- `POST /transactions` — create a transaction
- `GET /transactions` — list all transactions
- `GET /transactions/{id}` — get transaction details
- `GET /transactions/category/{categoryId}` — list transactions by category
- `PATCH /transactions/{id}` — update a transaction
- `DELETE /transactions/{id}` — remove a transaction

---

### Weekly Summary
- `GET /weeks/{date}/summary`  
  Returns a financial summary for the week starting at `{date}`.

Example:
```http
GET /weeks/2025-12-18/summary
```

#### MoneyFlow API

MoneyFlow is a **personal finance backend MVP** focused on **weekly cash flow tracking**, designed to demonstrate **clean architecture**, **financial correctness**, and **real-world backend trade-offs**.

The project intentionally prioritizes **domain clarity**, **money handling correctness**, and **testability** over feature volume.

---

## ✨ Key Concepts

- Weekly-based financial summaries
- All monetary values stored in **cents** to avoid floating-point precision issues
- Clear separation between **domain** and **presentation**
- Hexagonal / Clean Architecture inspired structure
- Realistic seed data for development and API exploration

---

## 🛠️ Tech Stack

- Java 21
- Spring Boot 4
- Maven
- PostgreSQL

---

## 🧱 Architecture Overview

The project follows a **clean / hexagonal architecture approach**:

- **Domain**
    - Core business models (`Transaction`, `Category`)
    - Business invariants and rules
- **Application (Use Cases)**
    - Orchestrates business logic
    - Weekly aggregation and summaries
- **Adapters**
    - Web (REST controllers, DTOs)
    - Persistence (PostgreSQL)

Money formatting and currency conversion are handled **only at the API boundary**, keeping the domain free from presentation concerns.

---

## 💰 Money Handling Strategy

- Monetary values are **persisted as integers in cents** (`amount_cents`)
- All calculations are done using integers or `BigDecimal`
- Conversion to currency format (e.g. BRL) happens only in the web layer

This approach avoids precision issues and keeps the system ready for **multi-currency support**.

---

## 📊 Current Features

### Categories
- CRUD for user categories
- Uniqueness enforced per user (`user_id + name`)
- Used as the basis for aggregation and budgeting

### Transactions
- Full CRUD for income and expense transactions
- All transactions are associated with:
    - user
    - category
    - occurrence timestamp
- Stored internally in cents, exposed as currency in the API
- Supports listing and filtering by category

### Weekly Summary
- Aggregates transactions within a weekly window
- Calculates:
    - total income
    - total expense
    - balance
    - top expense categories with percentage share
- Designed to reflect real financial reporting logic

---

## 🔌 API Endpoints

### Health
- `GET /healthz` — application health check

---

### Categories
- `POST /categories` — create a new category
- `GET /categories` — list categories
- `GET /categories/{id}` — get category details
- `DELETE /categories/{id}` — remove a category

---

### Transactions
- `POST /transactions` — create a transaction
- `GET /transactions` — list all transactions
- `GET /transactions/{id}` — get transaction details
- `GET /transactions/category/{categoryId}` — list transactions by category
- `PATCH /transactions/{id}` — update a transaction
- `DELETE /transactions/{id}` — remove a transaction

---

### Weekly Summary
- `GET /weeks/{date}/summary`  
  Returns a financial summary for the week starting at `{date}`.

#### Example:
#### http GET /weeks/2025-12-18/summary

```json
{
  "weekStart": "2025-12-18T00:00:00Z",
  "weekEnd": "2025-12-25T00:00:00Z",
  "currency": "BRL",
  "income": 1385.00,
  "expense": 594.00,
  "balance": 791.00,
  "transactionCount": 6,
  "topExpenses": [
    {
      "categoryId": "9096619b-81b2-492c-89d9-5abe47f2164f",
      "categoryName": "Travel",
      "expense": 275.20,
      "sharePct": 39.7
    },
    {
      "categoryId": "9c9f21bc-7bc8-4e78-bb3f-9a7d9e000006",
      "categoryName": "Shopping",
      "expense": 199.90,
      "sharePct": 28.6
    },
    {
      "categoryId": "9c9f21bc-7bc8-4e78-bb3f-9a7d9e000007",
      "categoryName": "Entertainment",
      "expense": 119.90,
      "sharePct": 18.7
    }
  ]
}
```

## 🧪 Error Handling

The API uses a **global exception handler** to translate domain and validation errors into meaningful HTTP responses:

- `400` — validation errors
- `409` — business conflicts
- `500` — unexpected server errors

---

## 🧰 Local Development

To simplify local setup, the project provides a **Makefile** with common commands:

```bash
make up      # start infrastructure (PostgreSQL)
make run     # run the Spring Boot application
make dev     # start infrastructure and application
make down    # stop infrastructure
make reset   # reset database (drop volumes)
```

## 🧪 Development Seed Data

For development environments, the database is seeded with:

- A default test user
- Multiple realistic categories
- Transactions distributed across **past and future weeks**

This ensures that:
- Weekly summaries always return meaningful data
- The API can be explored without manual setup

Seed data is **disabled for production environments**.

---

## 🚧 Roadmap

Planned next steps (intentionally incremental):

- Weekly budgets per category
- Budget overrun alerts
- Extended summary insights
- Authentication & authorization layer

---

## 🎯 Project Goal

This project is intentionally built as a **deep, realistic backend case study**, focusing on:

- correctness over shortcuts
- clarity over overengineering
- decisions that scale beyond a demo  