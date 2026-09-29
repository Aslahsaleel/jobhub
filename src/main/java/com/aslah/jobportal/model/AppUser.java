package com.aslah.jobportal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="users")
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotBlank private String name;
    @Email @NotBlank @Column(unique=true, nullable=false) private String email;
    @NotBlank private String password;
    @NotNull(message="Please select an account type") @Enumerated(EnumType.STRING) @Column(nullable=false) private Role role = Role.APPLICANT;
    private String phone;
    private String headline;
    private String resumeFileName;

    public Long getId(){return id;}
    public String getName(){return name;} public void setName(String v){name=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getPassword(){return password;} public void setPassword(String v){password=v;}
    public Role getRole(){return role;} public void setRole(Role v){role=v;}
    public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
    public String getHeadline(){return headline;} public void setHeadline(String v){headline=v;}
    public String getResumeFileName(){return resumeFileName;} public void setResumeFileName(String v){resumeFileName=v;}
}
