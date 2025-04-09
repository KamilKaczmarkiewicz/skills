# Basic Skills

## API Documentation

The API documentation is available at:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Running the Application

### Option 1: Run Full Stack (App + Postgres + Redis)

1. Build the JAR file:

   ```bash
   ./gradlew bootJar
   ```

2. Start the full stack with Docker Compose:

   ```bash
   docker compose -f docker-compose-with-backend.yml -p skill up -d
   ```

This will start:
- Spring Boot application (port: `8080`)
- PostgreSQL (port: `5432`)
- Redis (port: `6379`)

---

### Option 2: Run Only Databases (Postgres + Redis)

If you want to run the databases in Docker but launch the app locally:

```bash
docker compose -p skill up -d
```

Then start your Spring Boot app locally using your IDE or:

```bash
./gradlew bootRun
```

---

## How to Use the Application

To interact with the application, open [Swagger UI](http://localhost:8080/swagger-ui/index.html).

### Working with the `Person` resource

- Creating, updating, or deleting a `Person` will return a `taskId` in the response.
- You can use this `taskId` to check the similarity of fields that were changed in the `Person` object.
- To do this, call the endpoint for retrieving the task (e.g., `/tasks/{taskId}`), which will return information about the compared fields and their similarity status.

---

## Requirements

- Docker & Docker Compose
- Java 21
- Gradle 8+

