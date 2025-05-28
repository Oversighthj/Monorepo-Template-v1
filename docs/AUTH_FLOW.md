# JWT Authentication Flow

1. **Login**  
   ```bash
   curl -X POST http://localhost:8080/auth/login \
        -H "Content-Type: application/json" \
        -d '{"email":"admin@example.com","password":"secret"}'
