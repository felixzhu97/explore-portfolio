package com.finpulse.server.trade.service;

import com.finpulse.server.trade.domain.model.Trade;
import com.finpulse.server.trade.domain.model.TradeId;
import com.finpulse.server.trade.domain.repository.TradeRepository;
import com.finpulse.server.trade.dto.TradeRequest;
import com.finpulse.server.trade.mapper.TradeMapper;
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
public class TradeService {
  private final TradeRepository repository;
  private final TradeMapper mapper;

  @Transactional(readOnly = true)
  public List<Trade> list(int limit, int offset) {
    int size = limit <= 0 ? 100 : limit;
    int start = Math.max(offset, 0);
    return repository.findAllByOrderByCreatedAtDesc().stream().skip(start).limit(size).toList();
  }

  @Transactional(readOnly = true)
  public Trade getById(UUID id) {
    return repository
        .findById(TradeId.parseId(id))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trade not found"));
  }

  public Trade create(TradeRequest request) {
    return repository.save(mapper.toDomain(request));
  }

  public List<Trade> createBatch(List<TradeRequest> requests) {
    return requests.stream().map(this::create).toList();
  }

  public Trade update(UUID id, TradeRequest request) {
    Trade existing = getById(id);
    mapper.apply(request, existing);
    return repository.save(existing);
  }

  public void delete(UUID id) {
    TradeId tradeId = TradeId.parseId(id);
    if (!repository.existsById(tradeId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trade not found");
    }
    repository.deleteById(tradeId);
  }
}
