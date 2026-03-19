# YOU WILL WIN

A personal coding interview training platform built by someone who got mass-rejected and chose violence (the productive kind).

Instead of crying into my LeetCode premium subscription, I decided to build my own platform from scratch. Because apparently solving hundreds of algorithm problems wasn't enough — I also needed to build the system that serves them.

## What is this?

A full-stack web app where I torture myself with coding problems, track how badly I'm doing, and slowly get better at it. Think LeetCode, but with fewer features and more emotional investment.

## What it can do (so far)

- **Browse questions** — filter by difficulty and tags, pick your poison
- **Solve problems** — write Java in a Monaco editor, just like a real IDE except you can't blame IntelliJ when your code doesn't work
- **Run & Submit** — your code gets sandboxed in Docker, executed, and judged without mercy. Memory limited, network disabled, timeout enforced. No cheating.
- **Track your stats** — see your solve rate, performance by tag, and activity history. The numbers don't lie, but you might wish they did.
- **Admin panel** — create and manage questions with pre-built driver code templates, because writing stdin parsers from scratch is a special kind of hell I've already been through

## The stack

- **Backend:** Java 21 + Spring Boot (because enterprise pain builds character)
- **Frontend:** React + Monaco Editor (dark theme, orange accents, looks like a hacker movie)
- **Database:** PostgreSQL (running in Docker, like a responsible adult)
- **Code execution:** A Docker sandbox that runs your Java solutions in a locked-down container with no network, limited memory, and a timeout that kills infinite loops before they kill your CPU

## Current status

The MVP is done. All 5 phases shipped:

1. **Foundation** — database, models, REST API, CRUD
2. **Execution engine** — Docker sandbox, compile, run, compare output
3. **Attempt recording** — every submission saved, no hiding from your past
4. **Frontend** — React UI with split-pane editor, test results, the whole deal
5. **Stats & Admin** — dashboards, question management, driver code templates

Next up: practice sessions, interview simulator, weakness detection, and an AI that generates problems when I run out of ways to suffer manually.

## Running locally

```bash
# Start the database
cd docker
docker-compose up -d

# Build the sandbox image
docker build -t java-sandbox ./sandbox

# Start the backend
cd ../backend
.\mvnw.cmd spring-boot:run

# Start the frontend
cd ../frontend
npm install
npm run dev
```

Then open http://localhost:5173 and start questioning your career choices.

---

*Built with determination, caffeine, and the lingering fear of whiteboard interviews.*
