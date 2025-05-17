# Repository Guide for Code Agents

- **Tests:** Always run `pytest -q` before committing. If you modify the backend or frontend, also run `./mvnw test` in `services/backend` and `flutter test` in `apps/guest_app`.
- **OpenAPI Updates:** After editing `services/backend/src/main/resources/openapi.yaml`, run `./scripts/generate.sh` to regenerate the Java stubs and Dart client.
- **Commit Messages:** Use short, present-tense summaries (e.g., "Add CI workflow").
