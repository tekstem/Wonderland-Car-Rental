package edu.miu.cs.cs425.carsystem.service;


import edu.miu.cs.cs425.carsystem.model.Customer;
import edu.miu.cs.cs425.carsystem.repository.CustomerRepository;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {
    public Customer addNewCustomer(Customer customer);

    public void  deleteCustomerById(Integer customerId);

    public Customer updateCustomer(Customer customer);

    public Iterable<Customer> getAllCustomers();
    public Page<Customer> getAllCustomersPaged(int pageNo);
    public Customer getCustomerById(Integer customerId);


}
