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
# VideoInsight AI Backend

Spring Boot backend for VideoInsight-AI. Handles YouTube transcript fetching and summary generation using OpenAI.

## Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

## Setup & Run
```bash
cd backend
mvn clean install
mvn spring-boot:run
```
Configure your OpenAI API key in `src/main/resources/application.properties`:
```
OPENAI_API_KEY=sk-...
```

## Key Endpoints

- `GET    /api/transcript/{videoId}` — Fetch YouTube transcript (mocked for MVP)
- `POST   /api/summarizeVideo` — Generate summary from YouTube video (requires `{ url, title, transcript }`)

## Example Request

POST `/api/summarizeVideo`
```json
{
    "url": "https://www.youtube.com/watch?v=...",
    "title": "My Video",
    "transcript": "...transcript text..."
}
```

## Notes
- Uses H2 in-memory DB for development
- All config in `application.properties`
- See root README for full-stack usage

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
