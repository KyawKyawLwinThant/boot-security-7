package com.example.demo.service;

import com.example.demo.repository.User;
import com.example.demo.repository.UserRepoistory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepoistory userRepoistory;

    public UserDetailsServiceImpl(UserRepoistory userRepoistory) {
        this.userRepoistory = userRepoistory;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Optional<User> user=userRepoistory.searchEmailByUser(email);

       if(!user.isPresent()){
           throw new UsernameNotFoundException(email + " Not found.");
       }
        System.out.println("Role:"+ user.get().getRoles());
        System.out.println("Email:"+ user.get().getEmail());
        System.out.println("Password:"+ user.get().getPassword());
        return user.get();
    }
}
