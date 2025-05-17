### Backend code-generation — **MUST** run after every change to `openapi.yaml`

```bash
# regenerate Spring stubs
cd demo
./mvnw clean generate-sources
