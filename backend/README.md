# VideoInsight AI Backend

A Spring Boot backend application for the VideoInsight AI project.

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── videoinsight/
│   │           └── backend/
│   │               ├── VideoInsightBackendApplication.java
│   │               ├── controller/
│   │               │   └── HealthController.java
│   │               └── config/
│   │                   └── WebConfig.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/
            └── videoinsight/
                └── backend/
```

## Running the Application

### Using Maven
```bash
# Navigate to the backend directory
cd backend

# Clean and compile
mvn clean compile

# Run the application
mvn spring-boot:run
```

### Using IDE
Run the `VideoInsightBackendApplication.java` class directly from your IDE.

## Application Endpoints

- **Health Check**: `GET http://localhost:8080/api/health`
- **H2 Console**: `http://localhost:8080/api/h2-console`

## Configuration

The application is configured to run on:
- **Port**: 8080
- **Context Path**: `/api`
- **Database**: H2 in-memory database (for development)

## Dependencies

- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Validation
- H2 Database
- Spring Boot DevTools

## Development

The application includes:
- CORS configuration for frontend integration
- H2 console for database management
- Detailed logging for debugging
- Hot reload with Spring Boot DevTools
