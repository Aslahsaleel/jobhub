package com.aslah.jobportal.controller;

import com.aslah.jobportal.model.*;
import com.aslah.jobportal.repository.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class JobController {
    private final JobRepository jobs; private final UserRepository users; private final ApplicationRepository apps;
    public JobController(JobRepository jobs,UserRepository users,ApplicationRepository apps){this.jobs=jobs;this.users=users;this.apps=apps;}

    @GetMapping("/employer/jobs") public String employerJobs(Model m,org.springframework.security.core.Authentication a){
        var u=users.findByEmail(a.getName()).orElseThrow();m.addAttribute("jobs",jobs.findByEmployerId(u.getId()));return "employer-jobs";
    }
    @GetMapping("/employer/jobs/new") public String newJob(Model m){m.addAttribute("job",new Job());return "job-form";}
    @PostMapping("/employer/jobs") public String create(@Valid @ModelAttribute Job job,org.springframework.validation.BindingResult br,org.springframework.security.core.Authentication a){
        if(br.hasErrors()) return "job-form";job.setEmployer(users.findByEmail(a.getName()).orElseThrow());jobs.save(job);return "redirect:/employer/jobs";
    }
    @PostMapping("/employer/jobs/{id}/delete") public String delete(@PathVariable Long id,org.springframework.security.core.Authentication a){
        var u=users.findByEmail(a.getName()).orElseThrow();jobs.findById(id).filter(j->j.getEmployer().getId().equals(u.getId())).ifPresent(j->{j.setActive(false);jobs.save(j);});return "redirect:/employer/jobs";
    }
    @GetMapping("/employer/applications") public String employerApplications(Model m,org.springframework.security.core.Authentication a){
        var u=users.findByEmail(a.getName()).orElseThrow();m.addAttribute("applications",apps.findByJobEmployerIdOrderByAppliedAtDesc(u.getId()));m.addAttribute("statuses",ApplicationStatus.values());return "employer-applications";
    }
    @PostMapping("/employer/applications/{id}/status") public String updateStatus(@PathVariable Long id,@RequestParam ApplicationStatus status,org.springframework.security.core.Authentication a){
        var u=users.findByEmail(a.getName()).orElseThrow();apps.findById(id).filter(x->x.getJob().getEmployer().getId().equals(u.getId())).ifPresent(x->{x.setStatus(status);apps.save(x);});return "redirect:/employer/applications";
    }
    @GetMapping("/applicant/jobs") public String applicantJobs(@RequestParam(required=false) String q,@RequestParam(required=false) String q2,@RequestParam(defaultValue="0") int page,Model m){
        String query = (q == null ? "" : q).trim();
        String location = (q2 == null ? "" : q2).trim();
        var p = jobs.findByActiveTrueAndTitleContainingIgnoreCaseOrActiveTrueAndLocationContainingIgnoreCaseOrActiveTrueAndSkillsContainingIgnoreCase(query, location.isBlank()?query:location, query, PageRequest.of(Math.max(page,0),6));
        m.addAttribute("jobs",p);m.addAttribute("q",q);return "applicant-jobs";
    }
    @GetMapping("/applicant/jobs/{id}") public String jobDetails(@PathVariable Long id,Model m){m.addAttribute("job",jobs.findById(id).orElseThrow());m.addAttribute("application",new JobApplication());return "job-details";}
    @PostMapping("/applicant/jobs/{id}/apply") public String apply(@PathVariable Long id,@ModelAttribute JobApplication form,org.springframework.security.core.Authentication a){
        var u=users.findByEmail(a.getName()).orElseThrow();if(!apps.existsByJobIdAndApplicantId(id,u.getId())){var app=new JobApplication();app.setJob(jobs.findById(id).orElseThrow());app.setApplicant(u);app.setCoverLetter(form.getCoverLetter());apps.save(app);}return "redirect:/applicant/applications";
    }
    @GetMapping("/applicant/applications") public String applicantApplications(Model m,org.springframework.security.core.Authentication a){var u=users.findByEmail(a.getName()).orElseThrow();m.addAttribute("applications",apps.findByApplicantIdOrderByAppliedAtDesc(u.getId()));return "applicant-applications";}
}
