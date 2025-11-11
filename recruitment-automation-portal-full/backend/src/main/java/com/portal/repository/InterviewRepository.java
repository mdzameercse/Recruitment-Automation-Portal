package com.portal.repository;
import com.portal.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository public interface InterviewRepository extends JpaRepository<Interview, Long> {}