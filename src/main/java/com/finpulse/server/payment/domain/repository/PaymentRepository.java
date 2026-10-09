package com.finpulse.server.payment.domain.repository;

import com.finpulse.server.payment.domain.model.Payment;
import com.finpulse.server.payment.domain.model.PaymentId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface PaymentRepository extends Repository<Payment, PaymentId> {

  List<Payment> findAllByOrderByCreatedAtDesc();

  Optional<Payment> findById(PaymentId id);

  boolean existsById(PaymentId id);

  Payment save(Payment payment);

  void deleteById(PaymentId id);
}
