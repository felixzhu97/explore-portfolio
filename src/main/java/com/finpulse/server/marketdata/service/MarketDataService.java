package com.finpulse.server.marketdata.service;

import com.finpulse.server.marketdata.domain.model.MarketData;
import com.finpulse.server.marketdata.domain.model.MarketDataId;
import com.finpulse.server.marketdata.domain.repository.MarketDataRepository;
import com.finpulse.server.marketdata.dto.MarketDataRequest;
import com.finpulse.server.marketdata.mapper.MarketDataMapper;
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
public class MarketDataService {
  private final MarketDataRepository repository;
  private final MarketDataMapper mapper;

  @Transactional(readOnly = true)
  public List<MarketData> list(int limit, int offset) {
    int size = limit <= 0 ? 100 : limit;
    int start = Math.max(offset, 0);
    return repository.findAllByOrderByCreatedAtDesc().stream().skip(start).limit(size).toList();
  }

  @Transactional(readOnly = true)
  public MarketData getById(UUID id) {
    return repository
        .findById(MarketDataId.parseId(id))
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Market data not found"));
  }

  public MarketData create(MarketDataRequest request) {
    return repository.save(mapper.toDomain(request));
  }

  public List<MarketData> createBatch(List<MarketDataRequest> requests) {
    return requests.stream().map(this::create).toList();
  }

  public MarketData update(UUID id, MarketDataRequest request) {
    MarketData existing = getById(id);
    mapper.apply(request, existing);
    return repository.save(existing);
  }

  public void delete(UUID id) {
    MarketDataId marketDataId = MarketDataId.parseId(id);
    if (!repository.existsById(marketDataId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Market data not found");
    }
    repository.deleteById(marketDataId);
  }
}
