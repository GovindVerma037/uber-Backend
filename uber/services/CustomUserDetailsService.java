package com.project.uber.services;

import com.project.uber.entity.User;
import com.project.uber.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return userRepository.findByEmail(username); // email bhej rhe he

//        return org.springframework.security.core.userdetails
//          .User.withUsername(user.getEmail()).password("{noop}"+user.getPassword()).roles("USER").build();
    }
}
