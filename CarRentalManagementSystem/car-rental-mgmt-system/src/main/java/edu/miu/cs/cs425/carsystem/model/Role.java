package edu.miu.cs.cs425.carsystem.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Column(nullable=false, unique=true)
    @NotBlank
    private String name;
    @ManyToMany(mappedBy="roles")
    private List<User> users;
    public Role() {
    }

    public Role(Integer roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        Role otherRole = (Role)obj;
        if(this.roleId == null) {
            if(otherRole.roleId != null) return false;
        }
        return (this.roleId.equals(otherRole.roleId)
                && this.name.equals(otherRole.name));
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.roleId, this.name);
//        int result = roleId != null ? roleId.hashCode() : 0;
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        return result;
    }
}
