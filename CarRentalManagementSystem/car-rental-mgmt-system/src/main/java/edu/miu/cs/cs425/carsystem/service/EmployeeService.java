package edu.miu.cs.cs425.carsystem.service;

import edu.miu.cs.cs425.carsystem.model.Car;
import edu.miu.cs.cs425.carsystem.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    public Employee addNewEmployee(Employee newEmployee);
    public Employee getEmployeeById(Integer employeeId);
    public Employee updateEmployee(Employee employee);
    public void deleteEmployeeById(Integer employeeId);
    public Iterable<Employee> getAllEmployees();
    public Page<Employee> getAllEmployeePaged(Integer pageNo);
//    List<Employee> findEmployeeByFirstNameOrLastNameOrEmailAddressOrpOrPhoneNo(
//            String searchString);

}
