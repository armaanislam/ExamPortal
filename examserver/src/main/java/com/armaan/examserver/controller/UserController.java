package com.armaan.examserver.controller;

import com.armaan.examserver.entity.Role;
import com.armaan.examserver.entity.User;
import com.armaan.examserver.entity.UserRole;
import com.armaan.examserver.repository.UserRepository;
import com.armaan.examserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    // Creating User
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {

        Set<UserRole> roles = new HashSet<>();
        Role role = new Role();
        role.setRoleId(45L);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);

        return this.userService.createUser(user, roles);
    }

    // Getting User by username
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username) {

        return this.userService.findUserByUserName(username);
    }

    // Deleting User by userId
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {

        this.userService.deleteUserById(userId);
    }

}
