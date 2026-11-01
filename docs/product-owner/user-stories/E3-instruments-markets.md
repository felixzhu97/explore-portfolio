# E3 — Instruments & Markets

← [User Story Map](../User-Story-Map.md)

## Background

Positions and orders reference an instrument catalog and stored market observations.

---

## US-06 Instrument catalog

**As a** user  
**I want** instruments available via API  
**So that** positions and orders use stable symbols and ids

### Acceptance criteria

1. **Scenario** List instruments  
   **GIVEN** seeded instruments  
   **WHEN** `GET /api/v1/instruments`  
   **THEN** instruments are returned with stable ids/symbols

### Status

Done

---

## US-07 Market data records

**As an** ops user  
**I want** market-data CRUD  
**So that** historical observations persist for analytics and quotes

### Acceptance criteria

1. **Scenario** Market-data CRUD  
   **GIVEN** market-data payloads  
   **WHEN** market-data endpoints are used  
   **THEN** records persist per API contract

### Status

Done
