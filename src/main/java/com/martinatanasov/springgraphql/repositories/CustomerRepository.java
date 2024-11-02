package com.martinatanasov.springgraphql.repositories;

import com.martinatanasov.springgraphql.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
