package com.learnerguide.dashboard.serviceImpl;

import com.learnerguide.dashboard.entities.User;
import com.learnerguide.dashboard.model.UserDto;
import com.learnerguide.dashboard.repository.UserRepository;
import com.learnerguide.dashboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(UserDto userDto) {
        User user = new User(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()) , "USER", userDto.getFullname());
        return userRepository.save(user);
    }

}
