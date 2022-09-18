package com.armaan.examserver.service.impl;

import com.armaan.examserver.entity.User;
import com.armaan.examserver.entity.UserRole;
import com.armaan.examserver.repository.RoleRepository;
import com.armaan.examserver.repository.UserRepository;
import com.armaan.examserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // Creating User
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User local = this.userRepository.findByUsername(user.getUsername());
        User userLocal;

        if (local != null) {
            System.out.println("User is already here");
            throw new Exception("User already present");
        } else {
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole()); // Saving each new role
            }
            user.getUserRoles().addAll(userRoles); // Assigning role in users before saving the users
            userLocal = this.userRepository.save(user);
        }
        return userLocal;
    }

    @Override
    public User findUserByUserName(String username) {

        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUserById(Long userId) {

        this.userRepository.deleteById(userId);
    }

}
