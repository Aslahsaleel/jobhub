package com.aslah.jobportal.api;

import com.aslah.jobportal.dto.JobResponse;
import com.aslah.jobportal.repository.JobRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
public class JobApiController {
    private final JobRepository jobs;
    public JobApiController(JobRepository jobs){this.jobs=jobs;}
    @GetMapping
    public Page<JobResponse> search(@RequestParam(defaultValue="") String q,
                                    @RequestParam(defaultValue="0") int page,
                                    @RequestParam(defaultValue="10") int size){
        int safeSize=Math.min(Math.max(size,1),50);
        return jobs.findByActiveTrueAndTitleContainingIgnoreCaseOrActiveTrueAndLocationContainingIgnoreCaseOrActiveTrueAndSkillsContainingIgnoreCase(q,q,q,PageRequest.of(Math.max(page,0),safeSize)).map(JobResponse::from);
    }
    @GetMapping("/{id}")
    public JobResponse get(@PathVariable Long id){return jobs.findById(id).filter(j->j.isActive()).map(JobResponse::from).orElseThrow();}
}
