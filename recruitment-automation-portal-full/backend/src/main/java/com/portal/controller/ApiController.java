package com.portal.controller;
import com.portal.model.Candidate; import com.portal.model.Interview;
import com.portal.service.CandidateService; import com.portal.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired; import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime; import java.util.List; import java.util.Map;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApiController {
    @Autowired private CandidateService candidateService;
    @Autowired private InterviewService interviewService;

    @PostMapping("/resumes/upload")
    public ResponseEntity<?> uploadResume(@RequestParam("file") MultipartFile file){
        try{
            Candidate c = candidateService.saveFromFile(file);
            return ResponseEntity.ok(c);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }
    @GetMapping("/candidates")
    public List<Candidate> getCandidates(){
        return candidateService.listAll();
    }
    @PostMapping("/interviews")
    public ResponseEntity<?> scheduleInterview(@RequestBody Map<String,String> body){
        try{
            Long candidateId = Long.parseLong(body.get("candidateId"));
            LocalDateTime dt = LocalDateTime.parse(body.get("interviewDate"));
            Interview i = interviewService.schedule(candidateId, dt);
            return ResponseEntity.ok(i);
        }catch(Exception e)
            { e.printStackTrace();
                return ResponseEntity.status(400).body(Map.of("error", e.getMessage()));
            }
    }
}