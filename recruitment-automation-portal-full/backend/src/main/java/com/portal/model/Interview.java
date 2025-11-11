package com.portal.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="interviews")
public class Interview {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @JoinColumn(name="candidate_id")
    private Candidate candidate;
    private LocalDateTime interviewDate;
    private String status;


    public Interview(){} public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Candidate getCandidate(){return candidate;} public void setCandidate(Candidate candidate){this.candidate=candidate;}
    public LocalDateTime getInterviewDate(){return interviewDate;} public void setInterviewDate(LocalDateTime interviewDate){this.interviewDate=interviewDate;}
    public String getStatus(){return status;} public void setStatus(String status){this.status=status;}
}