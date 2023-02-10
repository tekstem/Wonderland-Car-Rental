package edu.miu.cs.cs425.carsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNo;
    @NotBlank(message = "Employee ssn should not be blank or empty")
    @Column(name = "ssn", nullable = false)
    private String ssn;
}
