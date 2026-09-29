package com.aslah.jobportal.dto;
import com.aslah.jobportal.model.JobApplication;
import java.time.LocalDateTime;
public record ApplicationResponse(Long id,Long jobId,String jobTitle,String applicantName,String status,LocalDateTime appliedAt){
    public static ApplicationResponse from(JobApplication a){return new ApplicationResponse(a.getId(),a.getJob().getId(),a.getJob().getTitle(),a.getApplicant().getName(),a.getStatus().name(),a.getAppliedAt());}
}
