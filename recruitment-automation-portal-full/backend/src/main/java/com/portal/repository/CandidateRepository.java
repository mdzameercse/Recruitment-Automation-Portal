package com.portal.repository;
import com.portal.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository public interface CandidateRepository extends JpaRepository<Candidate, Long> {}