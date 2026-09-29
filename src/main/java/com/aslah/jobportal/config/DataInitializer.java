package com.aslah.jobportal.config;

import com.aslah.jobportal.model.*;
import com.aslah.jobportal.repository.JobRepository;
import com.aslah.jobportal.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner seed(UserRepository users, JobRepository jobs, PasswordEncoder encoder) {
        return args -> {
            AppUser employer = users.findByEmail("hr@jobhub.demo").orElseGet(() -> {
                AppUser u = new AppUser(); u.setName("JobHub Technologies"); u.setEmail("hr@jobhub.demo");
                u.setPassword(encoder.encode("Password@123")); u.setRole(Role.EMPLOYER); return users.save(u);
            });
            if (jobs.count() == 0) {
                add(jobs, employer, "Java Backend Developer", "JobHub Technologies", "Bengaluru, Karnataka", "Java, Spring Boot, REST, MySQL", "Build scalable backend services, APIs and database-driven features with Java and Spring Boot.", 700000, 1100000);
                add(jobs, employer, "Full Stack Java Developer", "FinEdge Labs", "Bengaluru / Hybrid", "Java, Spring Boot, React, MySQL", "Develop customer-facing applications across backend services and modern React interfaces.", 800000, 1300000);
                add(jobs, employer, "Spring Boot Developer", "CloudNova", "Remote", "Java, Spring Boot, JPA, Docker", "Design and implement secure REST APIs and cloud-ready services.", 750000, 1200000);
                add(jobs, employer, "Software Engineer", "Nexora Systems", "Hyderabad", "Java, SQL, Git, Microservices", "Work with an engineering team to develop reliable services and improve application performance.", 650000, 1000000);
                add(jobs, employer, "Junior Java Developer", "BrightStack", "Chennai", "Java, OOP, JDBC, SQL", "Join a collaborative team and build maintainable Java applications while learning best practices.", 400000, 650000);
                add(jobs, employer, "Backend Engineer", "PayBridge", "Pune / Hybrid", "Java, Spring, PostgreSQL, REST", "Build transaction-focused backend systems with strong testing and observability.", 900000, 1500000);
                add(jobs, employer, "Java Developer - Fresher", "CodeSpring", "Bengaluru", "Java, Spring Boot, SQL, Git", "Entry-level opportunity for developers who enjoy problem solving and backend development.", 350000, 550000);
                add(jobs, employer, "Associate Software Engineer", "TechOrbit", "Remote", "Java, REST APIs, SQL, AWS", "Support feature development, bug fixing and API integration in a modern engineering team.", 500000, 850000);
            }
        };
    }
    private void add(JobRepository jobs, AppUser employer, String title, String company, String location, String skills, String description, int min, int max) {
        Job j = new Job(); j.setEmployer(employer); j.setTitle(title); j.setCompany(company); j.setLocation(location); j.setSkills(skills);
        j.setDescription(description); j.setDeadline(LocalDate.now().plusDays(45)); j.setEmploymentType("FULL_TIME");
        j.setMinSalary(BigDecimal.valueOf(min)); j.setMaxSalary(BigDecimal.valueOf(max)); j.setActive(true); jobs.save(j);
    }
}
