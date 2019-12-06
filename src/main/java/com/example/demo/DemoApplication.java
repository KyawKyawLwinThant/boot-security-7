package com.example.demo;

import com.example.demo.repository.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.User;
import com.example.demo.repository.UserRepoistory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DemoApplication {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepoistory userRepoistory;


    public DemoApplication(BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository, UserRepoistory userRepoistory) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.userRepoistory = userRepoistory;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(){
        return run -> {
           // System.out.println("Hello CommandLine Runner....");
            Role userRole=new Role();
            userRole.setName("ROLE_USER");

            Role adminRole=new Role();
            adminRole.setName("ROLE_ADMIN");

            User adminUser=new User();
            adminUser.setEmail("kyaw@gmail.com");
            adminUser.setPassword(bCryptPasswordEncoder.encode("kyaw"));


            User userUser=new User();
            userUser.setEmail("thaw@gmail.com");
            userUser.setPassword(bCryptPasswordEncoder.encode("thaw"));


            adminRole.getUsers().add(adminUser);
            userRole.getUsers().add(adminUser);


            userRole.getUsers().add(userUser);


            adminUser.getRoles().add(adminRole);
            adminUser.getRoles().add(userRole);


            userUser.getRoles().add(userRole);


        /*    roleRepository.save(userRole);
            roleRepository.save(adminRole);
            userRepoistory.save(adminUser);
            userRepoistory.save(userUser);

         */


        };
    }

}
