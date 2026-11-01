# E11 — Blockchain demo

← [User Story Map](../User-Story-Map.md)

## Background

Mobile includes a demo path for wallet connect and ETH transfer on a test network (for example Sepolia).

---

## US-23 Blockchain demo

**As an** investor (demo)  
**I want** to connect a wallet and try a testnet transfer  
**So that** connection state and transfer results show without touching production funds

### Acceptance criteria

1. **Scenario** Wallet connect on testnet  
   **GIVEN** mobile blockchain screens and a testnet configuration  
   **WHEN** a wallet is connected  
   **THEN** the app shows connection state without affecting production funds

2. **Scenario** Demo transfer feedback  
   **GIVEN** a connected wallet on testnet  
   **WHEN** a demo transfer is initiated  
   **THEN** the app shows success/failure feedback from the JSON-RPC path

### Status

Done
