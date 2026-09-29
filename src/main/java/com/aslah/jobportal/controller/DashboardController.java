package com.aslah.jobportal.controller;

import com.aslah.jobportal.model.Role;
import com.aslah.jobportal.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    private final UserRepository users; public DashboardController(UserRepository users){this.users=users;}
    @GetMapping("/") public String home(){return "home";}
    @GetMapping("/dashboard") public String dashboard(Authentication auth){
        var u=users.findByEmail(auth.getName()).orElseThrow();
        return u.getRole()==Role.EMPLOYER ? "redirect:/employer/jobs" : "redirect:/applicant/jobs";
    }
}
