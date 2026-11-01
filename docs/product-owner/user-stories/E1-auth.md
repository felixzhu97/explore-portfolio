# E1 — Auth

← [User Story Map](../User-Story-Map.md)

## Background

Registration, login, and session endpoints gate FinPulse portal, admin, and mobile.

---

## US-01 Register

**As a** guest  
**I want** to register with email and password  
**So that** a customer record exists for later login

### Acceptance criteria

1. **Scenario** Valid registration  
   **GIVEN** a valid registration payload  
   **WHEN** `POST /api/v1/auth/register`  
   **THEN** the API returns token + customer and persists the user

2. **Scenario** Duplicate email rejected  
   **GIVEN** an existing email  
   **WHEN** register is attempted  
   **THEN** the API returns a client error without creating a second user

### Status

Done

---

## US-02 Login

**As a** registered user  
**I want** to log in  
**So that** I receive a bearer token for API calls

### Acceptance criteria

1. **Scenario** Valid credentials  
   **GIVEN** valid credentials  
   **WHEN** `POST /api/v1/auth/login`  
   **THEN** the API returns `{ token, customer }`

2. **Scenario** Invalid credentials rejected  
   **GIVEN** invalid credentials  
   **WHEN** login is attempted  
   **THEN** the API rejects the request

### Status

Done

---

## US-03 Session

**As a** signed-in user  
**I want** `/auth/me`, logout, and change-password  
**So that** the current session can be inspected or ended

### Acceptance criteria

1. **Scenario** Current user  
   **GIVEN** a valid Bearer token  
   **WHEN** `GET /api/v1/auth/me`  
   **THEN** the current customer is returned

2. **Scenario** Logout  
   **GIVEN** a valid Bearer token  
   **WHEN** `POST /api/v1/auth/logout`  
   **THEN** the session is invalidated (204)

### Status

Done
