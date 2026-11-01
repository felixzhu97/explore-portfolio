# E5 — Watchlists & Quotes

← [User Story Map](../User-Story-Map.md)

## Background

Investors follow symbols and need quotes (REST and streaming where available).

---

## US-10 Watchlists

**As an** investor  
**I want** watchlists and items  
**So that** I can track symbols of interest

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
**So that** I see current prices

### Acceptance criteria

1. **Scenario** Quote APIs  
   **GIVEN** symbols  
   **WHEN** quote APIs (Java and/or Python aggregate/WS) are used  
   **THEN** quote payloads are returned or streamed per API docs

### Status

Done
