package com.edu.miu.backend.services.impl;

import com.edu.miu.backend.exception.CustomException;
import com.edu.miu.backend.repository.UserRepository;
import com.edu.miu.backend.services.UserService;
import com.edu.miu.backend.dto.UserDTO;
import com.edu.miu.backend.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElse(null);
    }

    public User createUser(User user) throws CustomException {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new CustomException("Username is taken");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new CustomException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public User registerUser(UserDTO userDTO) throws CustomException {
        User user = new User();

        user.setRole(userDTO.getRole());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setLastName(userDTO.getLastName());
        user.setFirstName(userDTO.getFirstName());
        user.setContactPhoneNumber(userDTO.getContactPhoneNumber());
        user.setDriverLicenseNumber(userDTO.getDriverLicenseNumber());

        return createUser(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User loggedUser = userRepository.findByUsername(username);
        if (loggedUser == null) {
            throw new UsernameNotFoundException("Invalid login credentials");
        }
        return new org.springframework.security.core.userdetails.User(
                loggedUser.getUsername(),
                loggedUser.getPassword(),
                loggedUser.getAuthorities()
        );
    }
}
