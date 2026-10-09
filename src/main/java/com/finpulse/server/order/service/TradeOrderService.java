package com.finpulse.server.order.service;

import com.finpulse.server.order.domain.model.Order;
import com.finpulse.server.order.domain.model.OrderId;
import com.finpulse.server.order.domain.repository.OrderRepository;
import com.finpulse.server.order.dto.TradeOrderRequest;
import com.finpulse.server.order.mapper.TradeOrderMapper;
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
public class TradeOrderService {
  private final OrderRepository repository;
  private final TradeOrderMapper mapper;

  @Transactional(readOnly = true)
  public List<Order> list(int limit, int offset) {
    int size = limit <= 0 ? 100 : limit;
    int start = Math.max(offset, 0);
    return repository.findAllByOrderByCreatedAtDesc().stream().skip(start).limit(size).toList();
  }

  @Transactional(readOnly = true)
  public Order getById(UUID id) {
    return repository
        .findById(OrderId.parseId(id))
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TradeOrder not found"));
  }

  public Order create(TradeOrderRequest request) {
    return repository.save(mapper.toDomain(request));
  }

  public List<Order> createBatch(List<TradeOrderRequest> requests) {
    return requests.stream().map(this::create).toList();
  }

  public Order update(UUID id, TradeOrderRequest request) {
    Order existing = getById(id);
    mapper.apply(request, existing);
    return repository.save(existing);
  }

  public void delete(UUID id) {
    OrderId orderId = OrderId.parseId(id);
    if (!repository.existsById(orderId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TradeOrder not found");
    }
    repository.deleteById(orderId);
  }
}
