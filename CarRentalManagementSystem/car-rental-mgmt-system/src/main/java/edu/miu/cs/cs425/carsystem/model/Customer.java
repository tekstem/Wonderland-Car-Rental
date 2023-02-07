package edu.miu.cs.cs425.carsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
@NotBlank(message = "drive license should not be blank or null")
    @Column(name = "drive_Licence", nullable = false)
    private String driveLicence;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNo;
    private String ssn;

}
