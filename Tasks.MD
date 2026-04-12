# Tasks - Research-Oriented University System

## Existing (already done by phoenix)
- [x] User (abstract), Employee (abstract), Student, Teacher, Manager, Admin
- [x] Course (prerequisites, electives, credits, multiple teachers)
- [x] Mark (1st att, 2nd att, exam, GPA, letter grades)
- [x] Lesson, LessonType enum (LECTURE, PRACTICE, LAB)
- [x] News, Message (basic versions)
- [x] Enums: DegreeStudent, DegreeTeacher, FacultyType, ManagerType, AttestationType
- [x] Database (Singleton pattern + serialization)
- [x] Demo classes (console menu)
- [x] Comparators: SortByGPA, SortByNames
- [x] Course registration with 21 credit limit
- [x] UserFactory (Factory pattern)

---

## PHOENIX - Research System + Patterns

### Priority 1: Research Core
- [ ] Create `Researcher` interface with methods:
  - `printPapers(Comparator c)` - print papers sorted by comparator
  - `calculateHIndex()` - calculate h-index
  - `getResearchPapers()`, `getResearchProjects()`
- [ ] Create `ResearchPaper` class:
  - Fields: title, authors (List), journal, pages, date, doi, citations, volume, number
  - `getCitation(Format f)` - return Plain Text or BibTeX format
  - Implement Comparable (by date default)
- [ ] Create `ResearchProject` class:
  - Fields: topic, publishedPapers (List), participants (List)
  - Throw `NotResearcherException` if non-Researcher tries to join
- [ ] Create comparators: `PaperByDate`, `PaperByCitations`, `PaperByPages`
- [ ] Print all research papers of all researchers in university (sorted)
- [ ] Print top cited researcher of school / of year

### Priority 2: GraduateStudent
- [ ] Create `GraduateStudent extends Student`:
  - Fields: researchSupervisor (Researcher), diplomaProject
  - Implement `Researcher` interface
  - Master and PhD students ARE always researchers
- [ ] Create `DiplomaProject` class:
  - Fields: title, publishedPapers (List<ResearchPaper>)
- [ ] Throw `InvalidSupervisorException` if supervisor h-index < 3

### Priority 3: Design Patterns (4+ required)
- [ ] Singleton - already in Database (done)
- [ ] Factory - already in UserFactory (done)
- [ ] Observer - for journal subscriptions (see derdiedasx tasks)
- [ ] Decorator - make Researcher work as decorator for Teacher/Student/Employee
  OR just use interface approach (decide with team)

### Priority 4: Custom Exceptions
- [ ] `InvalidSupervisorException` (h-index < 3)
- [ ] `NotResearcherException` (non-researcher joins research project)
- [ ] `CreditLimitExceededException` (student > 21 credits)
- [ ] `MaxFailException` (student failed > 3 times)

### Priority 5: Fix Bugs in Existing Code
- [ ] Fix `==` string comparison to `.equals()` in User.login()
- [ ] Fix `==` string comparison in Mark.convertToGPA()
- [ ] Fill in empty Lesson class (constructors, getters, setters)
- [ ] Fill in empty Transcript class

---

## DERDIEDASX - News, Journals, TechSupport, Messages

### Priority 1: News System (upgrade existing)
- [ ] Upgrade `News` class:
  - Add: topic (enum: RESEARCH, ACADEMIC, GENERAL, etc.), comments (List), author, pinned
  - Research news must be pinned/prioritized
- [ ] Auto-generate news when researcher publishes paper
- [ ] Auto-generate news about top cited researcher
- [ ] Add comments functionality to News

### Priority 2: University Journals + Observer Pattern
- [ ] Create `Journal` class:
  - Fields: name, publishedPapers (List), subscribers (List)
- [ ] Implement Observer pattern:
  - `Observable` / `Subject` interface for Journal
  - `Observer` interface for subscribers
  - `subscribe()`, `unsubscribe()`, `notifySubscribers()`
- [ ] All users (not only researchers) can subscribe
- [ ] Notify when new paper published in subscribed journal

### Priority 3: TechSupportSpecialist
- [ ] Create `TechSupportSpecialist extends Employee`:
  - View new orders
  - Accept/reject orders
- [ ] Create `Order` class:
  - Fields: description, status (NEW, ACCEPTED, DONE, REJECTED), createdBy
- [ ] View done orders, view accepted (not yet done) orders
- [ ] Create `OrderStatus` enum

### Priority 4: Message System (upgrade)
- [ ] Upgrade `Message` class:
  - Add: sender, receiver, date, subject
- [ ] Complaint system:
  - Create `Complaint extends Message`
  - Add `UrgencyLevel` enum (LOW, MEDIUM, HIGH)
  - Teacher.sendComplaint(Student, UrgencyLevel) to dean
- [ ] Work messages (sluzhebnie zapiski)

### Priority 5: Language Support
- [ ] Create `Language` enum (KZ, EN, RU)
- [ ] Add language field to User
- [ ] Interface text in 3 languages (at least menu/basic output)

---

## ITACHI8539 - Student/Teacher/Manager/Admin Enhancements + Demo

### Priority 1: Student Enhancements
- [ ] Student organizations:
  - Create `StudentOrganization` class (name, members, head)
  - Student can be member or head
- [ ] Implement Transcript class:
  - List of courses with marks, GPA, total credits
  - `getTranscript()` method
- [ ] Enforce max 3 fails rule (throw exception)
- [ ] Course types: MAJOR, MINOR, FREE_ELECTIVE (enum `CourseType`)
  - Note: SITE major can be Oil&Gas free elective

### Priority 2: Teacher Enhancements
- [ ] Add teacher title enum: TUTOR, LECTOR, SENIOR_LECTOR, PROFESSOR
  (update existing DegreeTeacher enum)
- [ ] Professors ARE always researchers (implement Researcher interface)
- [ ] Non-professor teachers CAN be researchers optionally
- [ ] Report generation about marks (simple statistics per course)

### Priority 3: Manager Enhancements
- [ ] Create statistical reports on academic performance
- [ ] View requests from employees (signed by dean/rector)
- [ ] Approve/reject student registration (improve existing)
- [ ] Specify course for which major/year

### Priority 4: Admin Enhancements
- [ ] Log files about user actions:
  - Create `LogAction` class or simple logging
  - Admin can view logs
- [ ] Improve user management (add, remove, update)

### Priority 5: Console Demo
- [ ] Update all Demo classes for new features
- [ ] Create `ResearcherDemo`
- [ ] Create `TechSupportDemo`
- [ ] Create `GraduateStudentDemo`
- [ ] Main demo that ties everything together
- [ ] Authentication flow (login before accessing any menu)

---

## Summary Table

| Member     | Main Responsibility              | Classes to Create                                    |
|------------|----------------------------------|------------------------------------------------------|
| phoenix    | Research system + patterns       | Researcher, ResearchPaper, ResearchProject,           |
|            |                                  | GraduateStudent, DiplomaProject, Exceptions           |
| derdiedasx | News, journals, tech support     | Journal, Observer, TechSupportSpecialist, Order,      |
|            |                                  | Complaint, upgrade News & Message                     |
| itachi8539 | Student/Teacher/Manager + demo   | StudentOrganization, Transcript, CourseType,           |
|            |                                  | LogAction, all Demo classes                           |

## Deadline Notes
- Part A (UML diagrams): 14th week
- Part B (classes draft): 15th week
- Part C (demo + final): final exam
- Focus first on: course registration, marks, research (MOST IMPORTANT)
