package edu.miu.cs.cs425.carsystem.service.imp;

import edu.miu.cs.cs425.carsystem.model.Customer;
import edu.miu.cs.cs425.carsystem.repository.CustomerRepository;
import edu.miu.cs.cs425.carsystem.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl  implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer addNewCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Integer customerId) {
        customerRepository.deleteById(customerId);

    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
    }

    @Override
    public Page<Customer> getAllCustomersPaged(int pageNo) {
        return customerRepository.findAll(PageRequest.of(pageNo,4,Sort.by(Sort.Direction.ASC,"firstName")));
    }

    @Override
    public Customer getCustomerById(Integer customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

//    @Override
//    public List<Customer> searchCustomer(String searchCar) {
//        return null ;
//    }
}
