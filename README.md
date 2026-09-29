# Jwt-Demo

Reference project: Spring Security + JWT (jjwt 0.12.x), stateless authentication, MySQL.

## Flow

```mermaid
sequenceDiagram
    participant C as Client
    participant F as JwtFilter
    participant U as UserController
    participant A as AuthenticationManager
    participant J as JwtService

    C->>U: POST /register {name, password}
    U-->>C: 201 (password stored as BCrypt hash)
    C->>U: POST /login {name, password}
    U->>A: authenticate()
    A-->>U: OK (or BadCredentials -> 401)
    U->>J: generateToken(userDetails)
    U-->>C: {"token": "..."}
    C->>F: GET /hello + Authorization: Bearer <token>
    F->>J: parse + verify signature/expiration
    F->>F: set Authentication in SecurityContext
    F-->>C: 200 (no/invalid token -> 403/401)
```

## Where is what

| File | Role |
|------|------|
| `configuration/SecurityConfig` | Filter chain: public `/register`, `/login`; `/admin/**` needs `ROLE_ADMIN`; STATELESS session; CSRF off |
| `configuration/JwtFilter` | `OncePerRequestFilter` - reads `Bearer` header, validates token, fills `SecurityContext` |
| `service/JwtService` | Generates and parses tokens (secret + lifetime from `application.properties`) |
| `service/CustomUserDetailsService` | Loads user from DB for Spring Security |
| `exception/GlobalExceptionHandler` | Bad credentials -> 401, duplicate user -> 409, validation -> 400 |

## Run

1. Generate a secret (Base64, min. 256 bits) and set env variables:

   ```bash
   openssl rand -base64 32
   ```

   ```
   DB_PASSWORD=<your mysql root password>
   JWT_SECRET=<generated secret>
   ```

2. Start the app (`./mvnw spring-boot:run`) and try the requests from `requests.http`.

## Notes

- Roles are stored with the `ROLE_` prefix (`ROLE_USER`), so `hasRole("USER")` works.
  A new user always gets `ROLE_USER`; to try `/admin/**`, change the role in the DB manually.
- The role is also stored as a `role` claim in the token (example of a custom claim).
- A JWT cannot be revoked before it expires - that is why the lifetime is short (`jwt.expiration-ms`).
- Never log tokens and never commit `JWT_SECRET`.

## Tests

```bash
./mvnw test -Dtest=JwtServiceTest
```

(`JwtDemoApplicationTests` needs a running MySQL and the env variables.)
