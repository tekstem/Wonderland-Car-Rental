package edu.miu.cs.cs425.carsystem.repository;

import edu.miu.cs.cs425.carsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
