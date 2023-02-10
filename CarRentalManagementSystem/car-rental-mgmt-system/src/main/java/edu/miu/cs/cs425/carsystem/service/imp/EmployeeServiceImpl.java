package edu.miu.cs.cs425.carsystem.service.imp;

import edu.miu.cs.cs425.carsystem.model.Employee;
import edu.miu.cs.cs425.carsystem.repository.EmployeeRepository;
import edu.miu.cs.cs425.carsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Employee addNewEmployee(Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }
    @Override
    public Employee getEmployeeById(Integer employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }
    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Integer employeeId) {
        employeeRepository.deleteById(employeeId);

    }

    @Override
    public Iterable<Employee> getAllEmployees() {
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC,"firstName"));
    }

    @Override
    public Page<Employee> getAllEmployeePaged(Integer pageNo) {
        return employeeRepository.findAll(PageRequest.of(pageNo,3, Sort.by(Sort.Direction.ASC, "firstName")));
    }

//    @Override
//    public List<Employee> findEmployeeByFirstNameOrLastNameOrEmailAddressOrpOrPhoneNo(String searchString) {
//        return employeeRepository.
//                findEmployeeByFirstNameOrLastNameOrEmailAddressOrpOrPhoneNo(searchString,
//                        searchString,searchString,searchString);
//    }
}
