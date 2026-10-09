package com.finpulse.server.customer.service;

import com.finpulse.server.customer.domain.model.Customer;
import com.finpulse.server.customer.domain.model.CustomerId;
import com.finpulse.server.customer.domain.repository.CustomerRepository;
import com.finpulse.server.customer.dto.CustomerRequest;
import com.finpulse.server.customer.mapper.CustomerMapper;
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
public class CustomerService {
  private final CustomerRepository repository;
  private final CustomerMapper mapper;

  @Transactional(readOnly = true)
  public List<Customer> list(int limit, int offset) {
    int size = limit <= 0 ? 100 : limit;
    int start = Math.max(offset, 0);
    return repository.findAllByOrderByCreatedAtDesc().stream().skip(start).limit(size).toList();
  }

  @Transactional(readOnly = true)
  public Customer getById(UUID id) {
    return repository
        .findById(CustomerId.parseId(id))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
  }

  public Customer create(CustomerRequest request) {
    return repository.save(mapper.toDomain(request));
  }

  public List<Customer> createBatch(List<CustomerRequest> requests) {
    return requests.stream().map(this::create).toList();
  }

  public Customer update(UUID id, CustomerRequest request) {
    Customer existing = getById(id);
    mapper.apply(request, existing);
    return repository.save(existing);
  }

  public void delete(UUID id) {
    CustomerId customerId = CustomerId.parseId(id);
    if (!repository.existsById(customerId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
    }
    repository.deleteById(customerId);
  }
}
