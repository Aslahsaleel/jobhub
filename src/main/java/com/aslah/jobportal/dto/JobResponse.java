package com.aslah.jobportal.dto;
import com.aslah.jobportal.model.Job;
public record JobResponse(Long id,String title,String company,String location,String skills,String employmentType,boolean active){
    public static JobResponse from(Job j){return new JobResponse(j.getId(),j.getTitle(),j.getCompany(),j.getLocation(),j.getSkills(),j.getEmploymentType(),j.isActive());}
}
