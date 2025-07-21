package com.lld.bookmyshow.services;

import com.lld.bookmyshow.models.User;
import com.lld.bookmyshow.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

   public User createUser(String email, String password, String name){
       User user = new User();

       user.setEmail(email);
       user.setName(name);
       BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
       user.setPassword(passwordEncoder.encode(password));

       return userRepository.save(user);
    }
}
