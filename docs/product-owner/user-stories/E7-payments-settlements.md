# E7 — Payments & Settlements

← [User Story Map](../User-Story-Map.md)

## Background

Payments fund activity; settlements and cash transactions record post-trade money movement.

---

## US-14 Payments

**As an** investor  
**I want** to submit payments  
**So that** account funding is recorded

### Acceptance criteria

1. **Scenario** Payment APIs  
   **GIVEN** a valid payment payload  
   **WHEN** payment APIs are used  
   **THEN** payment persists (fraud fields may be present when scoring is enabled)

### Status

Done

---

## US-15 Settlements & cash

**As an** ops user  
**I want** settlements and cash transactions  
**So that** post-trade money movement is tracked

### Acceptance criteria

1. **Scenario** Settlement and cash APIs  
   **GIVEN** settlement/cash payloads  
   **WHEN** corresponding APIs are used  
   **THEN** records persist per API docs

### Status

Done
