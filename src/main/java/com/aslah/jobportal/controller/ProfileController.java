package com.aslah.jobportal.controller;

import com.aslah.jobportal.repository.UserRepository;
import com.aslah.jobportal.service.FileStorageService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProfileController {
    private final UserRepository users; private final FileStorageService storage;
    public ProfileController(UserRepository users,FileStorageService storage){this.users=users;this.storage=storage;}
    @GetMapping("/profile") public String profile(Authentication a,Model m){m.addAttribute("user",users.findByEmail(a.getName()).orElseThrow());return "profile";}
    @PostMapping("/profile") public String update(@Valid @ModelAttribute("user") com.aslah.jobportal.model.AppUser form, Authentication a){
        var u=users.findByEmail(a.getName()).orElseThrow(); u.setName(form.getName());u.setPhone(form.getPhone());u.setHeadline(form.getHeadline());users.save(u);return "redirect:/profile?saved";
    }
    @PostMapping("/profile/resume") public String upload(@RequestParam MultipartFile resume,Authentication a){
        try { var u=users.findByEmail(a.getName()).orElseThrow();u.setResumeFileName(storage.storePdf(resume));users.save(u);return "redirect:/profile?uploaded"; }
        catch(Exception e){return "redirect:/profile?error="+java.net.URLEncoder.encode(e.getMessage(),java.nio.charset.StandardCharsets.UTF_8);}
    }
}
