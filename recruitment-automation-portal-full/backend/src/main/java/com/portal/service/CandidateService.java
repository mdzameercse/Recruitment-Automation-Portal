package com.portal.service;
import com.portal.model.Candidate;
import com.portal.repository.CandidateRepository;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File; import java.io.FileOutputStream; import java.nio.file.Paths;
import java.util.*; import java.util.regex.Matcher; import java.util.regex.Pattern;

@Service public class CandidateService {
    @Autowired private CandidateRepository candidateRepository;

    private Tika tika = new Tika();
    private final List<String> SKILLS = Arrays.asList("java","spring","react","python","sql","mysql","docker","aws","git","hibernate","node","javascript");
    private final String uploadDir = "C:/Users/mdzam/Downloads/uploads";

    public Candidate saveFromFile(MultipartFile file) throws Exception {
        File uploads = new File(uploadDir);
        if (!uploads.exists())
            uploads.mkdirs();
        String filename = System.currentTimeMillis() + "_" + Objects.requireNonNull(file.getOriginalFilename()).replaceAll("\s+","_");
        File conv = Paths.get(uploadDir, filename).toFile();
        try (FileOutputStream fos = new FileOutputStream(conv)) { fos.write(file.getBytes()); }
        String text = tika.parseToString(conv).toLowerCase();// conversting to plain text in lower case
        String email = extractEmail(text);
        String name = extractName(text);
        String skills = extractSkills(text);
        double score = computeScore(text, skills);
        Candidate c = new Candidate();
        c.setName(name); c.setEmail(email);
        c.setSkills(skills);
        c.setScore(score);
        c.setResumePath(conv.getAbsolutePath());
        return candidateRepository.save(c);
    }
    public List<Candidate> listAll(){
        return candidateRepository.findAll();
    }
    private String extractEmail(String text){ Pattern p = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"); Matcher m = p.matcher(text); if (m.find()) return m.group(); return ""; }
    private String extractName(String text){ Pattern p = Pattern.compile("name[:\\-\\s]+([A-Za-z\\s\\.]{3,60})"); Matcher m = p.matcher(text); if (m.find()) return m.group(1).trim(); String[] lines = text.split("\\r?\\n"); for (String line: lines){ line=line.trim(); if (line.length()>2 && line.matches("[A-Za-z\\s\\.]+")){ return line.split("\\s{2,}")[0].trim(); } } return "Unknown"; }
    private String extractSkills(String text){ Set<String> found = new LinkedHashSet<>(); for (String s: SKILLS){ if (text.contains(s)) found.add(s); } return String.join(", ", found); }

    private double computeScore(String text, String skills)
    {
        double score=0;
        Pattern p = Pattern.compile("(\\d+)\\+?\\s+years");
        Matcher m = p.matcher(text);
        if (m.find())
           {
            try
            {
                score += Math.min(10, Integer.parseInt(m.group(1))*2);
            }
            catch(Exception ignored){}
           }
            if (!skills.isEmpty())
                score += skills.split(",").length * 5;
            if (text.contains("spring")) score += 5;
            if (text.contains("java")) score += 5;
            return Math.min(score, 100);
    }
}