package com.armaan.examserver.user.serviceImpl;

import com.armaan.examserver.user.entity.User;
import com.armaan.examserver.user.entity.UserRole;
import com.armaan.examserver.user.repository.RoleRepository;
import com.armaan.examserver.user.repository.UserRepository;
import com.armaan.examserver.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
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
            log.error("User already present");
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

    // Getting User by Username
    @Override
    public User findUserByUserName(String username) {

        return this.userRepository.findByUsername(username);
    }

    // Deleting User by Id
    @Override
    public void deleteUserById(Long userId) {

        this.userRepository.deleteById(userId);
    }

}
