# ⚽ Football Standings Microservice

This project provides football league standings for a given **country**, **league**, and **team** using a public API. It is a full-stack microservice application built with **Java (Spring Boot)** and **React**, designed with **SOLID principles**, **offline fallback support**, **CI/CD pipelines**, and **Docker-based deployment**.

---

## 🚀 Features

- Get **standings** of a football team using:
    - Country name
    - League name
    - Team name
- **Offline fallback** support (mock data used when public API is unavailable)
- Built using **Spring Boot** (RESTful API) and **React**
- Swagger/OpenAPI documentation
- Dockerized and production-ready
- CI/CD pipelines using **Jenkins**
- TDD and BDD practices applied
- No third-party libraries or external DB used

---

## 🛠️ Tech Stack

| Layer      | Technology     |
|------------|----------------|
| Backend    | Java 17, Spring Boot |
| Frontend   | React (JavaScript) |
| Build Tool | Maven, npm     |
| CI/CD      | Jenkins        |
| Deployment | Docker         |
| Docs       | Swagger/OpenAPI, draw.io |

---

## 🔄 API Usage

### Endpoint

### Sample Response

```json
GET /api/v1/standings?country=England&league=Premier League&team=Arsenal
{
  "countryId": "41",
  "countryName": "England",
  "leagueId": "148",
  "leagueName": "Premier League",
  "teamId": "2612",
  "teamName": "Arsenal",
  "overallPosition": "2"
}

Frontend
UI built in React

Inputs: Country, League, Team (text fields or dropdowns)

Output: Team standings shown below the form

Axios used to interact with backend API

Fallback message shown in offline mode

--Design Patterns Used
Factory Pattern – for API client creation

Strategy Pattern – to switch between live and offline data

DTO Pattern – for decoupling internal data from API payloads

Builder Pattern – for test data setup

Singleton Pattern – for config loading

HATEOAS principles demonstrated in response structure

12-Factor Compliance
Externalized configuration

Backing services as attached resources

Dev/prod parity

Logs as event streams

Stateless and disposable processes

CI/CD integration


🖼️ Sequence Diagram
📄 View Sequence Diagram on draw.io
(File available at: docs/sequence-diagram.drawio)
User → Frontend UI → Spring Controller → Service Layer → API Client
                                                       ↘ Offline Fallback (if needed)


