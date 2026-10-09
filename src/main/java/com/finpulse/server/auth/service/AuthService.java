package com.finpulse.server.auth.service;

import com.finpulse.server.auth.domain.model.Session;
import com.finpulse.server.auth.domain.model.UserCredential;
import com.finpulse.server.auth.domain.repository.SessionRepository;
import com.finpulse.server.auth.domain.repository.UserCredentialRepository;
import com.finpulse.server.auth.dto.ChangePasswordRequest;
import com.finpulse.server.auth.dto.LoginRequest;
import com.finpulse.server.auth.dto.RegisterRequest;
import com.finpulse.server.customer.domain.model.Customer;
import com.finpulse.server.customer.domain.repository.CustomerRepository;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Locale;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
  private static final int SESSION_DAYS = 7;

  private final UserCredentialRepository credentialRepository;
  private final SessionRepository sessionRepository;
  private final CustomerRepository customerRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final SecureRandom secureRandom = new SecureRandom();

  public record AuthResult(String token, Customer customer) {}

  public AuthResult login(LoginRequest request) {
    String email = normalizeEmail(request.getEmail());
    UserCredential credential =
        credentialRepository.findByEmail(email).orElseThrow(this::invalidCredentials);
    if (!passwordEncoder.matches(request.getPassword(), credential.getPasswordHash())) {
      throw invalidCredentials();
    }
    Customer customer =
        customerRepository
            .findById(credential.getCustomerId())
            .orElseThrow(this::invalidCredentials);
    return new AuthResult(createSessionToken(customer.getId().getValue()), customer);
  }

  public AuthResult register(RegisterRequest request) {
    String email = normalizeEmail(request.getEmail());
    if (credentialRepository.findByEmail(email).isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registered");
    }
    Customer customer =
        customerRepository.save(Customer.createCustomer(request.getName().trim(), email));
    credentialRepository.save(
        UserCredential.createCredential(
            customer.getId().getValue(), email, passwordEncoder.encode(request.getPassword())));
    return new AuthResult(createSessionToken(customer.getId().getValue()), customer);
  }

  @Transactional(readOnly = true)
  public Customer me(String token) {
    return customerForToken(token);
  }

  public void logout(String token) {
    sessionRepository.deleteByToken(token);
  }

  public void changePassword(String token, ChangePasswordRequest request) {
    Customer customer = customerForToken(token);
    UserCredential credential =
        credentialRepository
            .findByCustomerId(customer.getId())
            .orElseThrow(this::invalidCredentials);
    if (!passwordEncoder.matches(request.getCurrentPassword(), credential.getPasswordHash())) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Current password is incorrect");
    }
    credential.updatePasswordHash(passwordEncoder.encode(request.getNewPassword()));
    credentialRepository.save(credential);
  }

  private Customer customerForToken(String token) {
    Session session =
        sessionRepository
            .findByToken(token)
            .orElseThrow(
                () ->
                    new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Invalid or expired session"));
    if (session.isExpired()) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid or expired session");
    }
    return customerRepository
        .findById(session.getCustomerId())
        .orElseThrow(
            () ->
                new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid or expired session"));
  }

  private String createSessionToken(UUID customerId) {
    byte[] bytes = new byte[32];
    secureRandom.nextBytes(bytes);
    String token = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    Instant expires = Instant.now().plus(SESSION_DAYS, ChronoUnit.DAYS);
    sessionRepository.save(Session.createSession(customerId, token, expires));
    return token;
  }

  private static String normalizeEmail(String email) {
    return email == null ? "" : email.trim().toLowerCase(Locale.ROOT);
  }

  private ResponseStatusException invalidCredentials() {
    return new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
  }
}
