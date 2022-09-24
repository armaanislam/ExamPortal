package com.armaan.examserver.user.service;

import com.armaan.examserver.user.entity.User;
import com.armaan.examserver.user.entity.UserRole;

import java.util.Set;

public interface UserService {

    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    public User findUserByUserName(String username);

    public void deleteUserById(Long userId);
}
