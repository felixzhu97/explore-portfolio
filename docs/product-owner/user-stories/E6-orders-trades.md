# E6 — Orders & Trades

← [User Story Map](../User-Story-Map.md)

## Background

Users place orders and observe resulting trades.

---

## US-12 Orders

**As an** investor  
**I want** to create and view orders  
**So that** I can trade instruments

### Acceptance criteria

1. **Scenario** Order APIs  
   **GIVEN** a valid order payload  
   **WHEN** order APIs are used  
   **THEN** orders persist and are retrievable

### Status

Done

---

## US-13 Trades

**As an** investor or ops user  
**I want** trades  
**So that** fills are visible

### Acceptance criteria

1. **Scenario** Trade APIs  
   **GIVEN** trade records  
   **WHEN** trade APIs are used  
   **THEN** trades list/detail match the API contract (including optional surveillance fields when enabled)

### Status

Done
