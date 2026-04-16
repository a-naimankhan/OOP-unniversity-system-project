# Tasks - Research-Oriented University System

## Existing (already done by phoenix/Саша)
- [x] User (abstract), Employee (abstract), Student, Teacher, Manager, Admin
- [x] Course (prerequisites, electives, credits, multiple teachers per course)
- [x] Mark (1st att, 2nd att, exam, GPA, letter grades)
- [x] Lesson, LessonType enum (LECTURE, PRACTICE, LAB)
- [x] News, Message (basic versions)
- [x] Enums: DegreeStudent, DegreeTeacher, FacultyType, ManagerType, AttestationType
- [x] Database (Singleton pattern + serialization)
- [x] Demo classes (console menu for Student, Teacher, Manager, Admin)
- [x] Comparators: SortByGPA, SortByNames
- [x] Course registration with 21 credit limit
- [x] UserFactory (Factory pattern)
- [x] Day, Period, Time helper classes

---

## PHOENIX (Саша) - Research System + Patterns + Exceptions

### Priority 1: Research Core - DONE
- [x] `Researcher` interface (default methods: `printPapers(Comparator)`, `calculateHIndex()`)
- [x] `ResearchPaper` class (title, authors, journal, pages, date, doi, citations, volume, number)
  - [x] `getCitation(Format f)` — Plain Text and BibTeX formats
  - [x] Implements `Comparable<ResearchPaper>` (by date)
- [x] `ResearchProject` class (topic, publishedPapers, participants)
  - [x] Throws `NotResearcherException` if non-Researcher joins
- [x] `Format` enum (PLAIN_TEXT, BIBTEX)
- [x] Comparators: `PaperByDate`, `PaperByCitations`, `PaperByPages`

### Priority 2: GraduateStudent - DONE
- [x] `GraduateStudent extends Student implements Researcher`
  - [x] researchSupervisor, diplomaProject, researchPapers, researchProjects
  - [x] Master and PhD students ARE always researchers
- [x] `DiplomaProject` class (title, publishedPapers list)
- [x] `InvalidSupervisorException` — if supervisor h-index < 3

### Priority 3: Custom Exceptions - DONE
- [x] `InvalidSupervisorException`
- [x] `NotResearcherException`
- [x] `CreditLimitExceededException`
- [x] `MaxFailException`

### Priority 4: Bug Fixes - DONE
- [x] Fixed `==` → `.equals()` in User.login()
- [x] Fixed `==` → `.equals()` in Mark.convertToGPA()
- [x] Fixed login not resetting isLogged on wrong password
- [x] Fixed changePassword() — now takes old + new password
- [x] Filled empty Lesson class

### Priority 5 TODO
- [x] Add method to print ALL research papers of ALL researchers in university (sorted)
  - `Database.printAllResearchPapers(Comparator)` + `Database.getAllResearchers()`
- [x] Add method to get top cited researcher of a school
  - `Database.getTopCitedResearcherOfSchool(FacultyType)`
- [x] Add method to get top cited researcher (among all)
  - `Database.getTopCitedResearcher()`
- [ ] Think about Decorator pattern for Researcher


---

## DERDIEDASX - News, Journals, TechSupport, Messages

### Priority 1: News System
- [ ] Upgrade `News` class:
  - Add: topic (enum: RESEARCH, ACADEMIC, GENERAL), comments (List), author, pinned (boolean)
  - Research news must be pinned/prioritized
- [ ] Auto-generate news when researcher publishes paper
- [ ] Auto-generate news about top cited researcher
- [ ] Comments on news

### Priority 2: University Journals + Observer Pattern
- [ ] `Journal` class (name, publishedPapers, subscribers)
- [ ] Observer pattern:
  - `Subject` interface for Journal
  - `Observer` interface for User (subscribers)
  - `subscribe()`, `unsubscribe()`, `notifySubscribers()`
- [ ] All users can subscribe (not only researchers)
- [ ] Notify when new paper published

### Priority 3: TechSupportSpecialist
- [ ] `TechSupportSpecialist extends Employee`
- [ ] `Request` class (description, status, createdBy)
- [ ] `RequestStatus` enum: **VIEWED, ACCEPTED, REJECTED, DONE**
  (after specialist sees it → VIEWED, then ACCEPTED/REJECTED, then DONE)
- [ ] View new/accepted/done requests

### Priority 4: Message System
- [ ] Upgrade `Message` (add: sender, receiver, date, subject)
- [ ] `Complaint extends Message` + `UrgencyLevel` enum (LOW, MEDIUM, HIGH)
- [ ] Teacher.sendComplaint(Student, UrgencyLevel) to dean
- [ ] Working official messages (служебные записки) — booking rooms for exams, etc.

### Priority 5: Language Support
- [ ] `Language` enum (KZ, EN, RU)
- [ ] Language field in User
- [ ] Menu text in 3 languages

---

## ITACHI8539 - Student/Teacher/Manager/Admin + Demo

### Priority 1: Student
- [ ] `StudentOrganization` class (name, members, head)
- [ ] `Transcript` class (courses list, marks, GPA, total credits)
- [ ] Enforce max 3 fails (throw `MaxFailException`)
- [ ] `CourseType` enum: MAJOR, MINOR, FREE_ELECTIVE

### Priority 2: Teacher
- [ ] Update DegreeTeacher enum: TUTOR, LECTOR, SENIOR_LECTOR, PROFESSOR
- [ ] Professors → always implement Researcher
- [ ] Non-professors → optionally can be Researcher
- [ ] Report generation (mark statistics per course)

### Priority 3: Manager
- [ ] Statistical reports on academic performance
- [ ] View employee requests (signed by dean/rector)
- [ ] Improve approveRegistration
- [ ] Specify course for major/year

### Priority 4: Admin
- [ ] Log files (user actions logging)
- [ ] Admin can view logs
- [ ] Improve add/remove/update user

### Priority 5: Console Demo (Part C)
- [ ] Update all Demo classes
- [ ] `ResearcherDemo`, `TechSupportDemo`, `GraduateStudentDemo`
- [ ] Main demo with authentication flow
- [ ] Everything runs in console

---

## Summary

| Member          | Responsibility              | Key Classes                                          |
|-----------------|-----------------------------|------------------------------------------------------|
| phoenix (Саша)  | Research + patterns + bugs  | Researcher, ResearchPaper, ResearchProject,          |
|                 |                             | GraduateStudent, DiplomaProject, 4 Exceptions        |
| derdiedasx      | News, journals, tech support| Journal, Observer, TechSupportSpecialist, Request,   |
|                 |                             | Complaint, upgrade News & Message                    |
| itachi8539      | Student/Teacher/Mgr + demo  | StudentOrganization, Transcript, CourseType,          |
|                 |                             | LogAction, all Demo classes                          |

## Deadlines
- Part A (UML diagrams): 14th week
- Part B (classes draft): 15th week (practice)
- Part C (demo + final): final exam
- **Приоритет:** course registration → marks → research
