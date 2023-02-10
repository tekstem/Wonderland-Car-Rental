package edu.miu.cs.cs425.carsystem.repository;

import edu.miu.cs.cs425.carsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

//    List<Employee> findEmployeeByFirstNameOrLastNameOrEmailAddressOrpOrPhoneNo(
//            String firstName, String lastName,String emailAddress,String phoneNo);
}
