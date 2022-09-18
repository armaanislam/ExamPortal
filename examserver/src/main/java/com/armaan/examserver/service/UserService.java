package com.armaan.examserver.service;

import com.armaan.examserver.entity.User;
import com.armaan.examserver.entity.UserRole;

import java.util.Set;

public interface UserService {

    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    public User findUserByUserName(String username);

    public void deleteUserById(Long userId);
}
