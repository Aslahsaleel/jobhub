package com.aslah.jobportal.api;

import com.aslah.jobportal.dto.ApplicationResponse;
import com.aslah.jobportal.repository.ApplicationRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationApiController {
    private final ApplicationRepository applications;
    public ApplicationApiController(ApplicationRepository applications){this.applications=applications;}
    @GetMapping("/job/{jobId}") public List<ApplicationResponse> byJob(@PathVariable Long jobId){return applications.findAll().stream().filter(a->a.getJob().getId().equals(jobId)).map(ApplicationResponse::from).toList();}
}
