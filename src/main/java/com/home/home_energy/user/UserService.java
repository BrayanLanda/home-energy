package com.home.home_energy.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserRequest userRequest){
        User newUser = new User(userRequest);

        return userRepository.save(newUser);
    }

    public Optional<User> authenticateUser(String email, String password){
        Optional<User> user = userRepository.findByEmail(email);
        return user.filter(u -> u.getPassword().equals(password));
    }

    public User updateUser(Long id, UpdateUserRequest updateUserRequest){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(updateUserRequest.getName());
        user.setPassword(updateUserRequest.getPassword());
        return userRepository.save(user);
    }
}
