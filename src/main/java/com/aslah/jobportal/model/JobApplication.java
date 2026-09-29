package com.aslah.jobportal.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="applications", uniqueConstraints=@UniqueConstraint(columnNames={"job_id","applicant_id"}))
public class JobApplication {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false) private Job job;
    @ManyToOne(optional=false) private AppUser applicant;
    @Enumerated(EnumType.STRING) private ApplicationStatus status = ApplicationStatus.APPLIED;
    private LocalDateTime appliedAt = LocalDateTime.now();
    private String coverLetter;

    public Long getId(){return id;} public Job getJob(){return job;} public void setJob(Job v){job=v;}
    public AppUser getApplicant(){return applicant;} public void setApplicant(AppUser v){applicant=v;}
    public ApplicationStatus getStatus(){return status;} public void setStatus(ApplicationStatus v){status=v;}
    public LocalDateTime getAppliedAt(){return appliedAt;}
    public String getCoverLetter(){return coverLetter;} public void setCoverLetter(String v){coverLetter=v;}
}
