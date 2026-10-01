# Job Application Assistant

A Java and Spring Boot portfolio application for analysing job offers, selecting the most suitable CV and generating a tailored cover letter in Spanish or English.

## Features

- Detects the language of a job offer or accepts a manual language selection.
- Recommends a Java or Web CV according to the technologies mentioned in the offer.
- Generates a cover letter adapted to the company, role and language.
- Opens searches across LinkedIn, InfoJobs, Tecnoempleo and Indeed.
- Keeps the final application under manual review. It never submits applications automatically.

## Tech stack

- Java 20+
- Spring Boot 4.1.1
- Spring MVC
- Bean Validation
- Maven
- HTML, CSS and JavaScript for the browser interface

## Requirements

- JDK 20 or newer
- Maven 3.9+

## Run locally

```powershell
git clone https://github.com/carlosmotosvillanueva/job-application-assistant.git
cd job-application-assistant
.\run.ps1
```

The application starts at `http://localhost:8081`.

You can also run it directly with Maven:

```powershell
mvn spring-boot:run "-Dspring-boot.run.arguments=--server.port=8081"
```

## API

### Analyse an offer

`POST /api/offers/analyze`

Example request:

```json
{
  "company": "Example Tech",
  "title": "Java Backend Developer",
  "description": "We are looking for a developer with Java, Spring Boot and SQL.",
  "url": "https://example.com/job/123",
  "language": ""
}
```

The response includes the detected language, the recommended CV and a generated cover letter.

## Project structure

```text
src/main/java
├── controller   HTTP endpoints
├── model        request and response models
└── service      offer analysis and cover letter generation
```

## Privacy and safety

The application does not submit job applications automatically. Review the generated content before using it. Do not add credentials, private CV files, API keys or personal configuration files to the repository.

## Portfolio

This project demonstrates Java, Spring Boot, REST API design, validation, layered application structure and practical job-search automation with human review.
