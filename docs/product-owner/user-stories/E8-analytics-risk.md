# E8 — Analytics & Risk

← [User Story Map](../User-Story-Map.md)

## Background

Python analytics provide portfolio aggregates, risk metrics, forecasts, and behavior events.

---

## US-16 Portfolio analytics

**As an** investor or admin  
**I want** portfolio aggregates and risk summaries  
**So that** I understand exposure

### Acceptance criteria

1. **Scenario** Analytics endpoints  
   **GIVEN** backend services are up  
   **WHEN** analytics/portfolio and risk endpoints are called (via :8801 entry or :8800)  
   **THEN** aggregate/risk payloads are returned

### Status

Done

---

## US-17 Behavior & forecast

**As an** admin  
**I want** behavior events and forecast hooks  
**So that** product analytics and ML demos work

### Acceptance criteria

1. **Scenario** Behavior events  
   **GIVEN** an event payload  
   **WHEN** `POST /api/v1/analytics/events`  
   **THEN** the event is stored and listable

2. **Scenario** Forecast  
   **GIVEN** an MLflow model URI  
   **WHEN** forecast model endpoint is called  
   **THEN** predictions are returned or a clear error is shown

### Status

Done
