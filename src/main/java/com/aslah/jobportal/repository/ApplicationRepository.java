package com.aslah.jobportal.repository;
import com.aslah.jobportal.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ApplicationRepository extends JpaRepository<JobApplication,Long>{
    boolean existsByJobIdAndApplicantId(Long jobId, Long applicantId);
    List<JobApplication> findByApplicantIdOrderByAppliedAtDesc(Long applicantId);
    List<JobApplication> findByJobEmployerIdOrderByAppliedAtDesc(Long employerId);
    long countByJobId(Long jobId);
}
