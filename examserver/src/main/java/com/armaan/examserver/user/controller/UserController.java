package com.armaan.examserver.user.controller;

import com.armaan.examserver.user.entity.Role;
import com.armaan.examserver.user.entity.User;
import com.armaan.examserver.user.entity.UserRole;
import com.armaan.examserver.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // User Registration
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {

        user.setProfile("default.jpg");
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
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

    // Updating User by userId

    // User Not Found Exception
//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<?> exceptionHandler(UserNotFoundException ex) {
//        return ResponseEntity;
//    }
}
