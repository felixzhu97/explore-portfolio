package com.finpulse.server.order.domain.repository;

import com.finpulse.server.order.domain.model.Order;
import com.finpulse.server.order.domain.model.OrderId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface OrderRepository extends Repository<Order, OrderId> {

  List<Order> findAllByOrderByCreatedAtDesc();

  Optional<Order> findById(OrderId id);

  boolean existsById(OrderId id);

  Order save(Order order);

  void deleteById(OrderId id);
}
