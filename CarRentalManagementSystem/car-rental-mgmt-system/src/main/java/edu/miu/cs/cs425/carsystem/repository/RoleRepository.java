package edu.miu.cs.cs425.carsystem.repository;

import edu.miu.cs.cs425.carsystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
