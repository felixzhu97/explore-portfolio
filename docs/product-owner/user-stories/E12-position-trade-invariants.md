# E12 — Position & Trade invariants

← [User Story Map](../User-Story-Map.md)

## Background

Orders, trades, and positions exist as APIs, but fill-to-quantity rules and
settlement timing are not stated as invariants. Ops and investors cannot
tell whether a fill must update a position, or when settlement applies.

---

## US-24 Position quantity follows fills

**As an** investor or ops user  
**I want** position quantity to update when a trade fill is recorded  
**So that** holdings match executed trades under the documented rule

### Acceptance criteria

1. **Scenario** Fill increases or decreases position  
   **GIVEN** a portfolio with a position in an instrument (or none yet)  
   **WHEN** a trade fill for that instrument is recorded per product rules  
   **THEN** position quantity reflects the fill  
   **AND** the result is visible via position list/detail APIs

2. **Scenario** No silent skip  
   **GIVEN** a fill that the product rules say must adjust holdings  
   **WHEN** the fill is accepted  
   **THEN** either the position updates or the operation fails with a clear error  
   **AND** holdings do not stay unchanged without explanation

### Status

Planned

---

## US-25 Glossary clarifies Trade, Position, Settlement

**As a** cross-repo developer  
**I want** Glossary entries for when Trade updates Position and what Settlement owns  
**So that** stories and code use one Preferred Term set for timing and duty

### Acceptance criteria

1. **Scenario** Terms present  
   **GIVEN** `docs/Glossary.md`  
   **WHEN** Trade, Position, and Settlement (or CashTransaction) are opened  
   **THEN** each has a definition that states ownership and timing relative to fills

2. **Scenario** Aligns with US-24  
   **GIVEN** the Glossary fill-to-position rule  
   **WHEN** compared with US-24 acceptance scenarios  
   **THEN** the rule text matches the story outcomes

### Status

Planned

---

## US-26 Domain model shows position adjustment behavior

**As a** cross-repo developer  
**I want** `C4-Code-Domain-Model.puml` to show position-adjustment domain behavior  
**So that** reviews can check aggregate methods against the Glossary

### Acceptance criteria

1. **Scenario** Behavior on the diagram  
   **GIVEN** the FinPulse domain model PlantUML  
   **WHEN** Portfolio / Position / Trade types are inspected  
   **THEN** public domain behavior related to applying a fill to a position is listed as `+method`  
   **OR** the diagram explicitly points to the application service that owns that rule with Glossary terms

2. **Scenario** No synonym drift  
   **GIVEN** Preferred Terms in the Glossary  
   **WHEN** reading the diagram  
   **THEN** type names match Preferred Terms

### Status

Planned
