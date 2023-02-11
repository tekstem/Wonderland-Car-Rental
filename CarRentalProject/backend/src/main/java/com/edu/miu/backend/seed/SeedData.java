package com.edu.miu.backend.seed;

import com.edu.miu.backend.exception.CustomException;
import com.edu.miu.backend.services.UserService;
import com.edu.miu.backend.model.Role;
import com.edu.miu.backend.model.User;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Transactional(propagation = Propagation.REQUIRED)
public class  SeedData {
    private final UserService userService;
    private final Random random = new Random();

    public SeedData(UserService userService) {
        this.userService = userService;
    }

    private void loadEmployees() {
        List<String> employees = new ArrayList<>() {{
            add("admin");
            add("employee");
        }};
        employees.forEach(name -> {
            User user = new User();
            user.setPassword("1234");
            user.setRole(Role.EMPLOYEE);
            user.setUsername(name.toLowerCase());
            user.setLastName(name.toUpperCase());
            user.setFirstName(name.toUpperCase());
            user.setEmail(name.toLowerCase() + "@rental.org");
            user.setContactPhoneNumber("641 8167887" + name.length());

            try {
                userService.createUser(user);
            } catch (CustomException e) {
                e.printStackTrace();
            }
        });
    }

    private void loadCustomers() {
        List<String> customers = new ArrayList<>() {{
            add("reid");
            add("becky");
            add("customer");
            add("thompson");
        }};
        for (int i = 0; i < customers.size(); i++) {
            User user = new User();
            user.setPassword("1234");
            user.setRole(Role.CUSTOMER);
            user.setDriverLicenseNumber("A2987 B2" + i + 1);
            user.setUsername(customers.get(i).toLowerCase());
            user.setLastName(customers.get(i).toUpperCase());
            user.setFirstName(customers.get(i).toUpperCase());
            user.setContactPhoneNumber("641 816 661" + i + 1);
            user.setEmail(customers.get(i).toLowerCase() + "@gmail.org");

            try {
                userService.createUser(user);
            } catch (CustomException e) {
                e.printStackTrace();
            }
        }
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        if (userService.findAll().size() == 0) {
            loadEmployees();
            loadCustomers();
        }
    }
}
