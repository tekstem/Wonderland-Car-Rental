package edu.miu.cs.cs425.carsystem.service;

import edu.miu.cs.cs425.carsystem.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();
    User saveUser(User user);
    User getUserById(Integer userId);
}
