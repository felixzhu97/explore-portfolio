# E3 — Instruments & Markets

← [User Story Map](../User-Story-Map.md)

## Background

Trading and portfolios depend on a catalog of Instruments and related market data.

---

## US-06 Instrument catalog

**As a** user  
**I want** instruments available via API  
**So that** positions and orders reference real symbols

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
**So that** historical observations can be stored

### Acceptance criteria

1. **Scenario** Market-data CRUD  
   **GIVEN** market-data payloads  
   **WHEN** market-data endpoints are used  
   **THEN** records persist per API contract

### Status

Done
