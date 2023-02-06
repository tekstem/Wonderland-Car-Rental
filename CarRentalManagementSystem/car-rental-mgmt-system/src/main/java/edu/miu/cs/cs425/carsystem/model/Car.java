package edu.miu.cs.cs425.carsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Getter
@Setter


public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carId;

    @NotBlank(message = "Car registration cannot null, empty or blank")
    @Column(name = "registration_Number", nullable = false)
    private String registrationNumber;
    private String model;
    private String make;
    private Integer year;
    private Integer milage;
    private Boolean status;

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", model='" + model + '\'' +
                ", make='" + make + '\'' +
                ", year=" + year +
                ", milage=" + milage +
                ", status=" + status +
                '}';
    }
}
