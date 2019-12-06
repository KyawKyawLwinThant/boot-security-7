package com.example.demo.controller;

import com.example.demo.repository.User;
import com.example.demo.service.UserDetailsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {
    private final UserDetailsServiceImpl userDetailsService;

    public AuthController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("success",model.containsAttribute("success"));
        return "auth/login";
    }
    @GetMapping("/register")
    public String register(Model model){

        model.addAttribute("user",new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String processRegister(@Valid User user,BindingResult result
            ,RedirectAttributes redirectAttributes){
        System.out.println("register ...........");
        if(result.hasErrors()){
            return "auth/register";
        }
        this.userDetailsService.register(user);
        redirectAttributes.addFlashAttribute("success"
                ,true);
        return "redirect:/login";
    }



}
