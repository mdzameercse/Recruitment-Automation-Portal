package com.portal.service;
import com.portal.model.Candidate; import com.portal.model.Interview;
import com.portal.repository.CandidateRepository; import com.portal.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired; import org.springframework.stereotype.Service;
import java.time.LocalDateTime; import java.util.Optional;
@Service public class InterviewService {
    @Autowired private InterviewRepository interviewRepository;
    @Autowired private CandidateRepository candidateRepository;
    public Interview schedule(Long candidateId, LocalDateTime dt){
        Optional<Candidate> oc = candidateRepository.findById(candidateId);
        if(oc.isEmpty()) throw new RuntimeException("Candidate not found");

        Interview i = new Interview();
        i.setCandidate(oc.get());
        i.setInterviewDate(dt);
        i.setStatus("SCHEDULED");
        return interviewRepository.save(i);
    }
}