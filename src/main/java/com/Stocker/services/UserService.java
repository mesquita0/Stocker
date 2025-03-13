package com.Stocker.services;

import com.Stocker.dto.CreateUserDTO;
import com.Stocker.entity.User;
import com.Stocker.repository.UserRepository;

public class UserService {
    private UserRepository userRepository;
    
    public UserService() {
        userRepository = new UserRepository();
    }

    public User login(String email, String password) {
        User user = userRepository.getByEmail(email);

        if ((user == null) || !user.getPassword().equals(password)) 
            return null;

        return stripPassword(user);
    }

    public User createUser(CreateUserDTO user) {
        User newUser = new User(
            null,
            user.getName(),
            user.getCpf(),
            user.getEmail(),
            user.getPassword(),
            user.getPhoneNumber(),
            null
        );

        return stripPassword(userRepository.save(newUser));
    }

    public void updateUser(User user) {
        userRepository.update(user);
    }

    private User stripPassword(User user) {
        user.setPassword(null);
        return user;
    }
}
