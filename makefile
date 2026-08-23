# Docker 相关
docker_down:
	docker compose down -v
docker_up:
	docker compose --env-file .env up -d
docker_rebuild:
	docker compose down -v
	docker compose --env-file .env up -d --build

.PHONY: docker_down docker_up docker_rebuild