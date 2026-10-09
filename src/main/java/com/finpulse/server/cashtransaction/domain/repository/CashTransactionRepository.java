package com.finpulse.server.cashtransaction.domain.repository;

import com.finpulse.server.cashtransaction.domain.model.CashTransaction;
import com.finpulse.server.cashtransaction.domain.model.CashTransactionId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface CashTransactionRepository
    extends Repository<CashTransaction, CashTransactionId> {

  List<CashTransaction> findAllByOrderByCreatedAtDesc();

  Optional<CashTransaction> findById(CashTransactionId id);

  boolean existsById(CashTransactionId id);

  CashTransaction save(CashTransaction cashTransaction);

  void deleteById(CashTransactionId id);
}
