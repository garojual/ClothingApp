package com.jag.clothingApp.services;

import com.jag.clothingApp.models.AdminRegisterRequest;
import com.jag.clothingApp.models.RegisterRequest;
import com.jag.clothingApp.models.Roles;
import com.jag.clothingApp.models.User;
import com.jag.clothingApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }
    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void registerUser(RegisterRequest request) {
        if (userRepository.findByUserEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException(("Email is already registered"));
        }

        User newUser = new User();
        newUser.setUserEmail(request.getEmail());
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(Roles.USER);

        this.createUser(newUser);
    }

    public void registerUserForAdmins(AdminRegisterRequest request){
        if (userRepository.findByUserEmail(request.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email is already registered");
        }

        User newUser = new User();
        newUser.setUserEmail(request.getEmail());
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(request.getRole());

        this.createUser(newUser);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

}
