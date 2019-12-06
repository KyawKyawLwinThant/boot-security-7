package com.example.demo.service;

import com.example.demo.repository.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.User;
import com.example.demo.repository.UserRepoistory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepoistory userRepoistory;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDetailsServiceImpl(UserRepoistory userRepoistory
    ,RoleRepository roleRepository,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepoistory = userRepoistory;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    public void register(User user){
        Role role=roleRepository.findByName("ROLE_USER");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.getRoles().add(role);
        this.userRepoistory.save(user);
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
