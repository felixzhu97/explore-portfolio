# E9 — Admin, Portal, Mobile surfaces

← [User Story Map](../User-Story-Map.md)

## Background

Admin, portal, and mobile clients share the Java API entry and common packages.

---

## US-18 Admin console

**As an** ops user  
**I want** an admin web app  
**So that** customers, portfolios, and transactions can be managed in the browser

### Acceptance criteria

1. **Scenario** Admin loads against API  
   **GIVEN** `pnpm dev:admin`  
   **WHEN** the admin app is pointed at :8801  
   **THEN** core management pages load against the API

### Status

Done

---

## US-19 Portal

**As an** investor  
**I want** a portal web app  
**So that** portfolios and markets are available in the browser

### Acceptance criteria

1. **Scenario** Portal against seeded data  
   **GIVEN** `pnpm dev:portal`  
   **WHEN** the portal runs against :8801  
   **THEN** portfolio/market flows work for seeded data

### Status

Done

---

## US-20 Mobile

**As an** investor  
**I want** a mobile app  
**So that** dashboard and watchlist data load on device

### Acceptance criteria

1. **Scenario** Mobile dashboard  
   **GIVEN** Expo mobile with `EXPO_PUBLIC_API_BASE_URL`  
   **WHEN** dashboard/watchlist is opened  
   **THEN** data loads from the API entry

### Status

Done
