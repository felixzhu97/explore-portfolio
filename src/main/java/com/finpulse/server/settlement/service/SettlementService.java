package com.finpulse.server.settlement.service;

import com.finpulse.server.settlement.domain.model.Settlement;
import com.finpulse.server.settlement.domain.model.SettlementId;
import com.finpulse.server.settlement.domain.repository.SettlementRepository;
import com.finpulse.server.settlement.dto.SettlementRequest;
import com.finpulse.server.settlement.mapper.SettlementMapper;
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
public class SettlementService {
  private final SettlementRepository repository;
  private final SettlementMapper mapper;

  @Transactional(readOnly = true)
  public List<Settlement> list(int limit, int offset) {
    int size = limit <= 0 ? 100 : limit;
    int start = Math.max(offset, 0);
    return repository.findAllByOrderByCreatedAtDesc().stream().skip(start).limit(size).toList();
  }

  @Transactional(readOnly = true)
  public Settlement getById(UUID id) {
    return repository
        .findById(SettlementId.parseId(id))
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Settlement not found"));
  }

  public Settlement create(SettlementRequest request) {
    return repository.save(mapper.toDomain(request));
  }

  public List<Settlement> createBatch(List<SettlementRequest> requests) {
    return requests.stream().map(this::create).toList();
  }

  public Settlement update(UUID id, SettlementRequest request) {
    Settlement existing = getById(id);
    mapper.apply(request, existing);
    return repository.save(existing);
  }

  public void delete(UUID id) {
    SettlementId settlementId = SettlementId.parseId(id);
    if (!repository.existsById(settlementId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Settlement not found");
    }
    repository.deleteById(settlementId);
  }
}
