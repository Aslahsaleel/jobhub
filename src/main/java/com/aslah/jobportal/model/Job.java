package com.aslah.jobportal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="jobs", indexes={
    @Index(name="idx_job_title", columnList="title"),
    @Index(name="idx_job_location", columnList="location")
})
public class Job {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @NotBlank private String title;
    @NotBlank private String company;
    @NotBlank @Column(length=4000) private String description;
    @NotBlank private String location;
    @NotBlank private String skills;
    @NotNull @FutureOrPresent private LocalDate deadline;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private String employmentType = "FULL_TIME";
    private boolean active = true;
    @ManyToOne(optional=false) private AppUser employer;

    public Long getId(){return id;}
    public String getTitle(){return title;} public void setTitle(String v){title=v;}
    public String getCompany(){return company;} public void setCompany(String v){company=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public String getLocation(){return location;} public void setLocation(String v){location=v;}
    public String getSkills(){return skills;} public void setSkills(String v){skills=v;}
    public LocalDate getDeadline(){return deadline;} public void setDeadline(LocalDate v){deadline=v;}
    public BigDecimal getMinSalary(){return minSalary;} public void setMinSalary(BigDecimal v){minSalary=v;}
    public BigDecimal getMaxSalary(){return maxSalary;} public void setMaxSalary(BigDecimal v){maxSalary=v;}
    public String getEmploymentType(){return employmentType;} public void setEmploymentType(String v){employmentType=v;}
    public boolean isActive(){return active;} public void setActive(boolean v){active=v;}
    public AppUser getEmployer(){return employer;} public void setEmployer(AppUser v){employer=v;}
}
