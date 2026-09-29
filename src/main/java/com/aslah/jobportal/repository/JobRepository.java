package com.aslah.jobportal.repository;

import com.aslah.jobportal.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobRepository extends JpaRepository<Job,Long>{
    List<Job> findByEmployerId(Long employerId);
    Page<Job> findByActiveTrueAndTitleContainingIgnoreCaseOrActiveTrueAndLocationContainingIgnoreCaseOrActiveTrueAndSkillsContainingIgnoreCase(String t,String l,String s,Pageable pageable);
}
