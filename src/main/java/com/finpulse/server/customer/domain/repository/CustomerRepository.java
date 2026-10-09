package com.finpulse.server.customer.domain.repository;

import com.finpulse.server.customer.domain.model.Customer;
import com.finpulse.server.customer.domain.model.CustomerId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface CustomerRepository extends Repository<Customer, CustomerId> {

  List<Customer> findAllByOrderByCreatedAtDesc();

  Optional<Customer> findById(CustomerId id);

  boolean existsById(CustomerId id);

  Customer save(Customer customer);

  void deleteById(CustomerId id);
}
