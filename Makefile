.PHONY: up down restart logs ps reset

DC=docker compose

## Sobe o ambiente (Postgres)
up:
	$(DC) up -d

## Derruba o ambiente (mantém volumes)
down:
	$(DC) down

## Derruba TUDO (inclusive volumes) – banco limpo
reset:
	$(DC) down -v

## Reinicia containers
restart: down up

## Logs dos containers
logs:
	$(DC) logs -f

## Lista containers ativos
ps:
	$(DC) ps

run:
	mvn spring-boot:run

dev: restart run