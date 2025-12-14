# MoneyFlow API

MoneyFlow is a personal finance MVP focused on weekly cash flow tracking, designed to demonstrate clean backend architecture and real-world trade-offs.

## Tech Stack
- Java 25
- Spring Boot 4
- Maven

## Current Status
The project currently provides a complete CRUD for category management, including request validation and global exception handling.
This phase focuses on establishing a clean architecture, consistent API behavior, and a solid foundation for upcoming features.

## Endpoints

### Health
- `GET /healthz` — application health check

### Categories
- `POST /categories` — create a new category
- `GET /categories` — list categories
- `DELETE /categories/{id}` — remove a category

## Error Handling
The API uses a global exception handler to translate domain and validation errors into meaningful HTTP responses (400, 409, 500).

## Local Development

To simplify local setup, the project provides a Makefile with common commands:

```bash
make up      # start infrastructure (PostgreSQL)
make run     # run the Spring Boot application
make dev     # start infrastructure and application
make down    # stop infrastructure
make reset   # reset database (drop volumes)