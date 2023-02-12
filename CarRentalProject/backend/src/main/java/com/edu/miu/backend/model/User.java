package com.edu.miu.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "First Name is required")
    @Size(min = 2, max = 20)
    private String firstName;

    @NotBlank(message = "Last Name is required")
    @Size(min = 2, max = 20)
    private String lastName;

    @Email
    @NotBlank(message = "email cannot be blank or null")
    private String email;

    @Column(unique = true)
    @NotBlank(message = "username is required")
    private String username;

    @Column(unique = true)
    private String driverLicenseNumber;

    @Column(unique = true)
    private String contactPhoneNumber;

    @NotNull(message = "role cannot be null")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

//    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Reservation> reservations;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' + '}';
    }

    /**
     * Returns granted Role as set of SimpleGrantedAuthority for the user.
     * granted authorities are used by spring for authentication.
     *
     * @return Set<SimpleGrantedAuthority>
     */
    @JsonIgnore
    public Set<SimpleGrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> permissions = new HashSet<>();
        permissions.add(new SimpleGrantedAuthority("ROLE_" + getRole().toString()));
        return permissions;
    }

    @JsonIgnore
    public List<Reservation> getReservations() {
        return reservations;
    }
}
