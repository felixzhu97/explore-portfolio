# E4 — Portfolios & Positions

← [User Story Map](../User-Story-Map.md)

## Background

Portfolios group holdings; positions record quantity per instrument.

---

## US-08 Portfolio CRUD

**As an** investor or ops user  
**I want** portfolios  
**So that** holdings are grouped by account or mandate

### Acceptance criteria

1. **Scenario** Portfolio CRUD  
   **GIVEN** an account/customer context  
   **WHEN** portfolio CRUD is used  
   **THEN** portfolios persist and are listable

### Status

Done

---

## US-09 Positions

**As an** investor  
**I want** positions in a portfolio  
**So that** quantity per instrument is visible

### Acceptance criteria

1. **Scenario** Position CRUD  
   **GIVEN** a portfolio and instrument  
   **WHEN** position CRUD/batch is used  
   **THEN** positions reflect quantity and instrument references

### Status

Done
