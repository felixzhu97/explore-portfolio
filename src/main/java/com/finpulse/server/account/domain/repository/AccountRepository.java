package com.finpulse.server.account.domain.repository;

import com.finpulse.server.account.domain.model.Account;
import com.finpulse.server.account.domain.model.AccountId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface AccountRepository extends Repository<Account, AccountId> {

  List<Account> findAllByOrderByCreatedAtDesc();

  Optional<Account> findById(AccountId id);

  boolean existsById(AccountId id);

  Account save(Account account);

  void deleteById(AccountId id);
}
