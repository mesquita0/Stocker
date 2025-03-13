package com.Stocker.services;

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

        user.setPassword(null);            
        return user;
    }
}
