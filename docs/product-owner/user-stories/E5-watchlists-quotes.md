# E5 — Watchlists & Quotes

← [User Story Map](../User-Story-Map.md)

## Background

Investors track symbols on watchlists and read quotes over REST or streaming where available.

---

## US-10 Watchlists

**As an** investor  
**I want** watchlists and items  
**So that** followed symbols persist across sessions

### Acceptance criteria

1. **Scenario** Watchlist CRUD  
   **GIVEN** an authenticated user  
   **WHEN** watchlist and watchlist-item APIs are used  
   **THEN** lists and items persist

### Status

Done

---

## US-11 Quotes

**As an** investor  
**I want** quotes for symbols  
**So that** current prices are available in portal and mobile

### Acceptance criteria

1. **Scenario** Quote APIs  
   **GIVEN** symbols  
   **WHEN** quote APIs (Java and/or Python aggregate/WS) are used  
   **THEN** quote payloads are returned or streamed per API docs

### Status

Done
