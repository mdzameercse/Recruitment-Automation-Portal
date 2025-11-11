package com.portal.model;
import jakarta.persistence.*;
@Entity
@Table(name = "candidates")
public class Candidate {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; private String email;

    @Column(columnDefinition = "TEXT")
    private String skills;

    private Double score; private String resumePath;

    public Candidate() {}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String name){this.name=name;}
    public String getEmail(){return email;} public void setEmail(String email){this.email=email;}
    public String getSkills(){return skills;} public void setSkills(String skills){this.skills=skills;}
    public Double getScore(){return score;} public void setScore(Double score){this.score=score;}
    public String getResumePath(){return resumePath;} public void setResumePath(String resumePath){this.resumePath=resumePath;}
}