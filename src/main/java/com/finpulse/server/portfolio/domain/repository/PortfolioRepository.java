package com.finpulse.server.portfolio.domain.repository;

import com.finpulse.server.portfolio.domain.model.Portfolio;
import com.finpulse.server.portfolio.domain.model.PortfolioId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface PortfolioRepository extends Repository<Portfolio, PortfolioId> {

  List<Portfolio> findAllByOrderByCreatedAtDesc();

  Optional<Portfolio> findById(PortfolioId id);

  boolean existsById(PortfolioId id);

  Portfolio save(Portfolio portfolio);

  void deleteById(PortfolioId id);
}
