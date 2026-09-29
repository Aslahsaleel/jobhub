# JobHub — Secure Job Recruitment & Application Platform

A portfolio-grade Java/Spring Boot job platform inspired by the information architecture and usability patterns of modern job portals. It is an original implementation and does not use Naukri branding, assets, or proprietary UI.

## UX flow
1. Landing page → Login / Register
2. Registration → clear field validation → success message on login page
3. Login → role-based dashboard
4. Applicant → search jobs → view job → apply → track status
5. Employer → post jobs → review candidates → update application status
6. Profile → update details and upload PDF resume

## Demo account
Employer: hr@jobhub.demo / Password@123

## Run
- JDK 21+
- Maven 3.9+
- MySQL 8+
- `docker compose up -d`
- `mvn spring-boot:run`
- Open http://localhost:8080
