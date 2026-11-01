# E9 — Admin, Portal, Mobile surfaces

← [User Story Map](../User-Story-Map.md)

## Background

FinPulse ships three clients that share the Java API entry and shared packages.

---

## US-18 Admin console

**As an** ops user  
**I want** an admin web app  
**So that** I can manage clients, portfolios, and transactions

### Acceptance criteria

1. **Scenario** Admin loads  
   **GIVEN** `pnpm dev:admin`  
   **WHEN** I open the admin app pointed at :8801  
   **THEN** core management pages load against the API

### Status

Done

---

## US-19 Portal

**As an** investor  
**I want** a portal web app  
**So that** I can view portfolios and markets in the browser

### Acceptance criteria

1. **Scenario** Portal flows  
   **GIVEN** `pnpm dev:portal`  
   **WHEN** I use the portal against :8801  
   **THEN** portfolio/market flows work for seeded data

### Status

Done

---

## US-20 Mobile

**As an** investor  
**I want** a mobile app  
**So that** I can monitor portfolios on the go

### Acceptance criteria

1. **Scenario** Mobile dashboard  
   **GIVEN** Expo mobile with `EXPO_PUBLIC_API_BASE_URL`  
   **WHEN** I open dashboard/watchlist  
   **THEN** data loads from the API entry

### Status

Done
