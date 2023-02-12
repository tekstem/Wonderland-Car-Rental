package com.edu.miu.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "brands")
public class CarBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brandId;
    private String name;
    private String description;
}
