# Recruitment Automation Portal - Full Prototype

## Overview
Spring Boot (Java 21) backend with Apache Tika resume parsing and a React frontend.
Uploads are stored at: C:/Users/mdzam/Downloads/uploads

## Quick start (local MySQL)
1. Create a MySQL database named `recruitment_portal`.
2. Update `backend/src/main/resources/application.yml` with your DB username/password if needed.
3. Run backend: `cd backend && mvn spring-boot:run`
4. Run frontend: `cd frontend && npm install && npm start`
5. Use Postman or the UI to upload resumes and schedule interviews.

## Notes
- This prototype uses Apache Tika to parse PDF/DOCX. For local testing you can upload .txt files as well.
