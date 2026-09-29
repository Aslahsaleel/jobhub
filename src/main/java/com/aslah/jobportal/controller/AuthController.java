package com.aslah.jobportal.controller;

import com.aslah.jobportal.model.*;
import com.aslah.jobportal.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final UserRepository users; private final PasswordEncoder encoder;
    public AuthController(UserRepository users,PasswordEncoder encoder){this.users=users;this.encoder=encoder;}
    @GetMapping("/login") public String login(){return "login";}
    @GetMapping("/register") public String register(Model m){ AppUser user = new AppUser(); user.setRole(Role.APPLICANT); m.addAttribute("user",user); m.addAttribute("roles",Role.values()); return "register";}
    @PostMapping("/register") public String register(@Valid @ModelAttribute("user") AppUser user, BindingResult br, Model m){
        if (user.getEmail() != null) user.setEmail(user.getEmail().trim().toLowerCase());
        if (user.getEmail() != null && !user.getEmail().isBlank() && users.findByEmail(user.getEmail()).isPresent()) { br.rejectValue("email","duplicate","This email is already registered. Please login or use another email."); }
        if(br.hasErrors()){m.addAttribute("roles",Role.values());return "register";}
        user.setPassword(encoder.encode(user.getPassword())); users.save(user); return "redirect:/login?registered";
    }
}
