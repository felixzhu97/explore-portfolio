package com.finpulse.server.bond.service;

import com.finpulse.server.bond.domain.model.Bond;
import com.finpulse.server.bond.domain.model.BondId;
import com.finpulse.server.bond.domain.repository.BondRepository;
import com.finpulse.server.bond.dto.BondRequest;
import com.finpulse.server.bond.mapper.BondMapper;
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
public class BondService {
  private final BondRepository repository;
  private final BondMapper mapper;

  @Transactional(readOnly = true)
  public List<Bond> list(int limit, int offset) {
    int size = limit <= 0 ? 100 : limit;
    int start = Math.max(offset, 0);
    return repository.findAllByOrderByCreatedAtDesc().stream().skip(start).limit(size).toList();
  }

  @Transactional(readOnly = true)
  public Bond getById(UUID id) {
    return repository
        .findById(BondId.parseId(id))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bond not found"));
  }

  public Bond create(BondRequest request) {
    return repository.save(mapper.toDomain(request));
  }

  public List<Bond> createBatch(List<BondRequest> requests) {
    return requests.stream().map(this::create).toList();
  }

  public Bond update(UUID id, BondRequest request) {
    Bond existing = getById(id);
    mapper.apply(request, existing);
    return repository.save(existing);
  }

  public void delete(UUID id) {
    BondId bondId = BondId.parseId(id);
    if (!repository.existsById(bondId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bond not found");
    }
    repository.deleteById(bondId);
  }
}
