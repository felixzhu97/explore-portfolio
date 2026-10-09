package com.finpulse.server.auth.domain.repository;

import com.finpulse.server.auth.domain.model.CredentialId;
import com.finpulse.server.auth.domain.model.UserCredential;
import com.finpulse.server.customer.domain.model.CustomerId;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface UserCredentialRepository extends Repository<UserCredential, CredentialId> {

  Optional<UserCredential> findByEmail(String email);

  Optional<UserCredential> findByCustomerId(CustomerId customerId);

  UserCredential save(UserCredential credential);
}
