package com.finpulse.server.account.service;

import com.finpulse.server.account.domain.model.Account;
import com.finpulse.server.account.domain.model.AccountId;
import com.finpulse.server.account.domain.repository.AccountRepository;
import com.finpulse.server.account.dto.AccountRequest;
import com.finpulse.server.account.mapper.AccountMapper;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
  private final AccountRepository repository;
  private final AccountMapper mapper;

  @Transactional(readOnly = true)
  public List<Account> list(int limit, int offset) {
    int size = limit <= 0 ? 100 : limit;
    int start = Math.max(offset, 0);
    return repository.findAllByOrderByCreatedAtDesc().stream().skip(start).limit(size).toList();
  }

  @Transactional(readOnly = true)
  public Account getById(UUID id) {
    return repository
        .findById(AccountId.parseId(id))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
  }

  public Account create(AccountRequest request) {
    return repository.save(mapper.toDomain(request));
  }

  public List<Account> createBatch(List<AccountRequest> requests) {
    return requests.stream().map(this::create).toList();
  }

  public Account update(UUID id, AccountRequest request) {
    Account existing = getById(id);
    mapper.apply(request, existing);
    return repository.save(existing);
  }

  public void delete(UUID id) {
    AccountId accountId = AccountId.parseId(id);
    if (!repository.existsById(accountId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
    }
    repository.deleteById(accountId);
  }
}
