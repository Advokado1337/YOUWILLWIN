# YOU WILL WIN

A personal coding interview training platform built by someone who got mass-rejected and chose violence (the productive kind).

Instead of crying into my LeetCode premium subscription, I decided to build my own platform from scratch. Because apparently solving hundreds of algorithm problems wasn't enough — I also needed to build the system that serves them.

## What is this?

A full-stack web app where I torture myself with coding problems, track how badly I'm doing, and slowly get better at it. Think LeetCode, but with fewer features and more emotional investment.

## The stack

- **Backend:** Java + Spring Boot (because enterprise pain builds character)
- **Frontend:** React (coming soon, once the backend stops throwing 500s)
- **Database:** PostgreSQL (running in Docker, like a responsible adult)
- **Code execution:** A Docker sandbox that runs your Java solutions and judges them silently

## Current status

Phase 1 is done. You can create questions, tag them, and stare at them while questioning your career choices.

More phases are coming. Eventually there will be a UI, an interview simulator, and an AI that generates problems when I run out of ways to suffer manually.

## Running locally

```bash
# Start the database
cd docker
docker-compose up -d

# Start the backend
cd backend
.\mvnw.cmd spring-boot:run
```

Then open http://localhost:8080/api/questions and enjoy the emptiness.

---

*Built with determination, caffeine, and the lingering fear of whiteboard interviews.*
