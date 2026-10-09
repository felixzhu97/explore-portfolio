package com.finpulse.server.portfolio.service;

import com.finpulse.server.portfolio.domain.model.Portfolio;
import com.finpulse.server.portfolio.domain.model.PortfolioId;
import com.finpulse.server.portfolio.domain.repository.PortfolioRepository;
import com.finpulse.server.portfolio.dto.PortfolioRequest;
import com.finpulse.server.portfolio.mapper.PortfolioMapper;
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
public class PortfolioService {
  private final PortfolioRepository repository;
  private final PortfolioMapper mapper;

  @Transactional(readOnly = true)
  public List<Portfolio> list(int limit, int offset) {
    int size = limit <= 0 ? 100 : limit;
    int start = Math.max(offset, 0);
    return repository.findAllByOrderByCreatedAtDesc().stream().skip(start).limit(size).toList();
  }

  @Transactional(readOnly = true)
  public Portfolio getById(UUID id) {
    return repository
        .findById(PortfolioId.parseId(id))
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Portfolio not found"));
  }

  public Portfolio create(PortfolioRequest request) {
    return repository.save(mapper.toDomain(request));
  }

  public List<Portfolio> createBatch(List<PortfolioRequest> requests) {
    return requests.stream().map(this::create).toList();
  }

  public Portfolio update(UUID id, PortfolioRequest request) {
    Portfolio existing = getById(id);
    mapper.apply(request, existing);
    return repository.save(existing);
  }

  public void delete(UUID id) {
    PortfolioId portfolioId = PortfolioId.parseId(id);
    if (!repository.existsById(portfolioId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Portfolio not found");
    }
    repository.deleteById(portfolioId);
  }
}
