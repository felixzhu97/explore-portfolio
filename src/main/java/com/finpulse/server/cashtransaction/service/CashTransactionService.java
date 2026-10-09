package com.finpulse.server.cashtransaction.service;

import com.finpulse.server.cashtransaction.domain.model.CashTransaction;
import com.finpulse.server.cashtransaction.domain.model.CashTransactionId;
import com.finpulse.server.cashtransaction.domain.repository.CashTransactionRepository;
import com.finpulse.server.cashtransaction.dto.CashTransactionRequest;
import com.finpulse.server.cashtransaction.mapper.CashTransactionMapper;
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
public class CashTransactionService {
  private final CashTransactionRepository repository;
  private final CashTransactionMapper mapper;

  @Transactional(readOnly = true)
  public List<CashTransaction> list(int limit, int offset) {
    int size = limit <= 0 ? 100 : limit;
    int start = Math.max(offset, 0);
    return repository.findAllByOrderByCreatedAtDesc().stream().skip(start).limit(size).toList();
  }

  @Transactional(readOnly = true)
  public CashTransaction getById(UUID id) {
    return repository
        .findById(CashTransactionId.parseId(id))
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CashTransaction not found"));
  }

  public CashTransaction create(CashTransactionRequest request) {
    return repository.save(mapper.toDomain(request));
  }

  public List<CashTransaction> createBatch(List<CashTransactionRequest> requests) {
    return requests.stream().map(this::create).toList();
  }

  public CashTransaction update(UUID id, CashTransactionRequest request) {
    CashTransaction existing = getById(id);
    mapper.apply(request, existing);
    return repository.save(existing);
  }

  public void delete(UUID id) {
    CashTransactionId cashTransactionId = CashTransactionId.parseId(id);
    if (!repository.existsById(cashTransactionId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CashTransaction not found");
    }
    repository.deleteById(cashTransactionId);
  }
}
