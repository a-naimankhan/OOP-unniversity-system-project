# University Management System — Project Report

**Course:** Object-Oriented Programming  
**Language:** Java 17  
**Team members:** Alexander Demchenko, Aibar, Almas

---

## 1. Introduction

This report describes the design and implementation of a University Management System developed as a final project for the Object-Oriented Programming course. The system is a console-based Java 17 application that simulates the core operations of a university, including user management, course registration, academic grading, research activity, news publishing, technical support, and a multilingual user interface supporting English, Russian, and Kazakh.

The project was developed collaboratively by a team of three students, with each member responsible for a specific set of features. The application was built entirely in plain Java 17 without any external frameworks or build tools, relying solely on the Java standard library. All data is persisted between sessions using Java object serialization.

---

## 2. System Architecture and Package Structure

The project is organized into seven packages, each with a clearly defined responsibility.

The **users** package contains the entire user class hierarchy along with the Database class, which acts as the central data store for the application. The **course** package holds all classes related to academic courses, including scheduling, rooms, lessons, and attendance tracking. The **mark** package handles the grading system, defining how attestation scores are recorded and how final grades are calculated. The **research** package contains everything related to academic research activity, including papers, projects, journals, and the Researcher interface. The **other** package groups supporting classes such as the messaging system, news, technical support requests, startups, recommendation letters, and the multilingual manager. The **exceptions** package defines five custom exception classes that enforce business rules at runtime. Finally, the **demo** package contains the application entry point and all role-based interactive console menus.

---

## 3. Class Hierarchy and Descriptions

### 3.1 User Hierarchy

The class hierarchy is built around an abstract base class called User, which defines the common properties and behaviors shared by everyone in the system. Every user has a full name, username, password, and a preferred language for the interface. The User class also implements the Observer interface, which allows users to receive notifications from journals they subscribe to.

The Employee class extends User and represents all staff members. It adds a salary field and the ability to send and receive messages. Employee is further extended by Teacher, Manager, Admin, and TechSupportSpecialist.

A Teacher holds a list of assigned courses, a faculty type, an academic degree, and a rating given by students. Teachers can assign marks to students for each attestation period, generate statistical reports for their courses, and write recommendation letters for students.

A Manager is responsible for administrative operations such as adding and removing courses, publishing and updating news, and assigning courses to teachers. Managers can also view student rankings by name or GPA.

An Admin has the highest level of access and can add or remove any user from the system, view the full activity log, and perform regular expression searches across users, courses, and research papers.

A TechSupportSpecialist handles technical support requests submitted by users. They can view pending requests, accept or reject them, and mark them as completed.

The Student class extends User directly and represents undergraduate and master-level students. Each student maintains a map of courses to marks, tracks their total registered credits, and counts how many times they have failed each course. Students can register for courses subject to a maximum credit limit of 21 and a maximum of three failed attempts per course. They can also drop courses, view their marks and transcript, rate teachers, and read news.

GraduateStudent extends Student and additionally implements the Researcher interface. This class adds research papers, research projects, a diploma project, and a research supervisor. The supervisor can only be assigned if their h-index is three or higher.

ResearchTeacher extends Teacher and also implements Researcher, representing professors who are active in academic research.

### 3.2 Database

The Database class serves as the central Singleton data store. It holds all system collections as static vectors: users, students, teachers, managers, admins, courses, marks, news, journals, requests, logs, attendance records, rooms, startups, and recommendation letters. It provides methods for saving all data to binary files, loading it back on startup, logging system events, searching by regular expression, and finding the top-cited researcher. On each save, it also exports a human-readable credentials file listing all users with their usernames and passwords.

### 3.3 Course and Scheduling

The Course class stores a course name, code, number of credits, semester period, faculty type, student limit, and an optional prerequisite course. Courses can be of type MAJOR, MINOR, or FREE_ELECTIVE. The Schedule class holds a list of Lesson objects for a course, where each lesson has a type (lecture, practice, or lab), a room reference, a day, and a time. The Room class defines a physical location with a capacity. The Attendance class records whether a specific student was present at a specific lesson.

### 3.4 Mark

The Mark class stores three attestation scores: the first attestation (maximum 30 points), the second attestation (maximum 30 points), and the exam (maximum 40 points). The total is the sum of all three, giving a maximum of 100. The final letter grade is derived from the total: 90 or above is A, 80 to 89 is B+, 70 to 79 is B, 60 to 69 is C+, 50 to 59 is C, and below 50 is F.

### 3.5 Research System

The ResearchPaper class represents an academic publication. It stores the title, list of authors, journal name, number of pages, publication date, DOI, citation count, volume, and issue number. It implements both Serializable and Comparable, allowing papers to be sorted and persisted. It also provides a getCitation method that generates a formatted reference in either plain text or BibTeX format.

The ResearchProject class represents a collaborative academic project identified by a topic and a list of participants. Only users who implement the Researcher interface may join a project; otherwise a NotResearcherException is thrown.

The DiplomaProject class associates a GraduateStudent with a collection of research papers that form the basis of their thesis.

The Journal class implements the Subject interface and maintains a list of subscribers. When a paper is published through a journal, all subscribed users are notified automatically.

Three comparator classes are provided for sorting research papers: PaperByDate sorts by publication date, PaperByCitations sorts by citation count in descending order, and PaperByPages sorts by page count in descending order.

### 3.6 Supporting Classes

The News class stores a title, body text, publication date, topic (RESEARCH, ACADEMIC, or GENERAL), and a reference to the author. When a researcher adds a paper, a news item is automatically created with the RESEARCH topic and added to the system.

The Request class represents a technical support ticket with a description, urgency level (LOW, MEDIUM, or HIGH), status (NEW, VIEWED, ACCEPTED, REJECTED, or DONE), and a reference to the user who created it.

The Startup class allows GraduateStudents to register a startup with a name, description, and list of members. The RecommendationLetter class stores a letter written by a Teacher for a specific Student, including the content and issue date.

The LanguageManager class holds a static map of over 80 translation keys for all three supported languages. Any part of the system can call it with a key and a Language enum value to retrieve the appropriate translated string.

---

## 4. Interfaces

The system defines three interfaces that enforce key behavioral contracts.

The **Researcher** interface is the most significant. It is implemented by GraduateStudent and ResearchTeacher. It defines two abstract methods for retrieving lists of research papers and projects, and three default methods that provide shared implementations. The addResearchPaper default method validates that citations are non-negative, adds the paper to the list, and automatically generates a news entry. The printPapers default method accepts a comparator and prints the sorted list. The calculateHIndex default method sorts citation counts in descending order and counts how many papers have at least as many citations as their rank, which is the standard definition of the h-index.

The **IMessage** interface defines the contract for the messaging system and serves as the base of the Decorator pattern chain. It declares methods for retrieving the subject, text, sender, receiver, date, and formatted content of a message.

The **Observer** and **Subject** interfaces together implement the Observer design pattern. Subject defines methods for subscribing, unsubscribing, and notifying observers. Observer defines a single update method. User implements Observer, and Journal implements Subject.

---

## 5. Design Patterns

### 5.1 Singleton — Database

The Database class follows the Singleton pattern by creating its single instance at class loading time through a static field initializer. This ensures that the entire application shares one unified data store. All collections are static, so any class in any package can access the data directly without passing references around.

### 5.2 Observer — Journal and User

The Observer pattern connects journals to their subscribers. When a researcher publishes a paper through a Journal object, the journal calls notifyObservers, which iterates over all subscribed User objects and calls their update method. This delivers a real-time notification to every interested party without the journal needing to know anything about the specific users.

### 5.3 Factory Method — UserCreator

The Factory Method pattern abstracts user creation. The abstract UserCreator class declares a createUser method that subclasses must implement. StudentCreator, TeacherCreator, AdminCreator, and ManagerCreator each provide their own implementation that constructs the correct type of user with the appropriate fields. This makes it easy to add new user types in the future without changing the code that requests user creation.

### 5.4 Decorator — IMessage and MessageDecorator

The Decorator pattern allows message behavior to be extended dynamically without subclassing. The base Message class implements IMessage and provides the standard formatted content. The abstract MessageDecorator class also implements IMessage and wraps another IMessage instance. UrgentMessageDecorator prepends an urgency label to the formatted content, and EncryptedMessageDecorator wraps the content with an encryption marker. Decorators can be chained, so a message can be both urgent and encrypted at the same time.

---

## 6. Custom Exceptions

The system defines five custom exception classes to enforce business rules at runtime rather than relying on generic error handling.

CreditLimitExceededException is thrown when a student attempts to register for a course that would bring their total credit count above 21. MaxFailException is thrown when a student tries to register for a course they have already failed three times. NotResearcherException is thrown when a user who does not implement the Researcher interface tries to join a ResearchProject. InvalidSupervisorException is thrown when a GraduateStudent attempts to assign a supervisor whose h-index is below 3. LowHIndexException is thrown when an h-index is insufficient for a required operation.

All five exceptions extend RuntimeException and include a descriptive message that explains exactly which rule was violated.

---

## 7. Key Features

### 7.1 Multilingual Interface

Language selection happens at the start of each session before login. The user chooses between English, Russian, and Kazakh by entering a number. The selected language is stored on the User object after login, and every demo class retrieves translations through LanguageManager using the user's language preference. This means the same code path produces fully translated output regardless of which language was chosen.

### 7.2 Data Persistence

All system state is saved to binary files in the data folder using Java object serialization. The Database.save method is called when any user logs out through the normal menu flow. Each collection is serialized to its own file. On the next run, load reads all files back into the static collections. A human-readable credentials.txt file is also written each time save is called, listing all users with their usernames and passwords for easy reference.

### 7.3 Role-Based Console Menus

Each user role has a dedicated demo class with a console menu tailored to their responsibilities. All menus are fully translated into the three supported languages. Wherever the user previously had to type a name (such as a course name or student name), the menus now display a numbered list and the user simply enters the corresponding number, eliminating errors from typos.

### 7.4 RegEx Search

The Admin role can search across users, courses, and research papers using a regular expression pattern. The Database class provides three search methods that filter their respective collections by matching the pattern against relevant text fields such as full name, username, or course name.

### 7.5 h-index Calculation

The h-index is calculated by sorting a researcher's citation counts in descending order and finding the largest index h such that at least h papers each have at least h citations. This is implemented as a default method in the Researcher interface and is available to both GraduateStudent and ResearchTeacher.

---

## 8. Bonus Features

The project includes several features beyond the base requirements. A Schedule system was built with Room, Lesson, Day, and LessonType classes that allow courses to have structured timetables. An Attendance tracking class records student presence at individual lessons. GraduateStudents can create Startup entries with a name, description, and founding team. Teachers can write RecommendationLetter objects for students, which are stored in the database and visible to the relevant graduate student. The Admin menu includes a full RegEx search feature. The multilingual system covers over 80 UI keys across all three languages.

---

## 9. Problems Encountered and Solutions

The first significant problem was related to input handling. The application originally created a new BufferedReader wrapping System.in in both Main.java and each individual demo class. When input was provided through a pipe or redirect, the first reader buffered more bytes than it consumed, leaving nothing for the second reader. The solution was to create a single BufferedReader at the start of Main.java and pass it as a parameter to every demo class, ensuring all input reading went through one shared reader.

The second problem was UTF-8 encoding on Windows. Cyrillic characters were displayed as garbled symbols in the terminal. An initial attempt to fix this by wrapping System.out with a UTF-8 PrintStream made the problem worse, because it caused raw UTF-8 bytes to bypass the Windows console translation layer. The correct solution was to remove that wrapper entirely and rely only on JVM startup flags that configure file and standard output encoding at the JVM level. The language selection menu was also changed to use only ASCII characters, since it appears before any language is chosen.

The third problem was the fragility of text-based sub-menus in the Manager demo. The original implementation required the user to type exact strings like "add course" or "by gpa" to navigate. Any variation would silently do nothing. This was replaced with numbered menus throughout, making navigation consistent and error-free.

The fourth problem was that the Teacher and Student demos originally asked users to type exact names of courses and students. A single typo would fail silently. These were all replaced with numbered lists populated from the live database collections.

---

## 10. Team Contributions and Project Management

The team of three communicated through a group Telegram chat and distributed responsibilities as follows.

Alexander was responsible for the Researcher interface and all its default method implementations, the ResearchPaper class including both citation formats, the ResearchProject and DiplomaProject classes, the h-index algorithm, all five custom exceptions, the Decorator pattern implementation for messages, the Factory Method pattern for user creation, the Database search methods, the Startup and RecommendationLetter classes, and the multilingual translations for all demo classes.

The second team member was responsible for the News system, the Observer pattern implementation including the Journal and Subject interfaces, the TechSupportSpecialist class and the Request system with its status and urgency levels, the base Message class hierarchy, and the Language enum and LanguageManager.

The third team member was responsible for the Transcript class, StudentOrganization, the CourseType enum, ResearchTeacher, teacher and manager report generation, the Schedule, Room, Lesson, and Attendance classes, the Database logging system, all demo menu classes, and the Main entry point including authentication and language routing.

---

## 11. What Works and What Does Not Work

The complete authentication flow with language selection works correctly for all seven user roles. All role-based menus function as intended: students can register and drop courses, view marks and transcripts, and read news; teachers can assign marks, generate course reports, and write recommendation letters; managers can manage courses and news, assign teachers, and view student rankings; the admin can manage users, view logs, and perform regex searches; graduate students can manage research papers, join projects, view their h-index, and create startups; the tech support specialist can view and process requests. The Observer pattern correctly notifies subscribed users when papers are published. The Decorator pattern correctly wraps messages with urgent and encrypted labels. Data is saved to files on logout and the credentials file is generated automatically.

The main limitation is that the application is console-only with no graphical interface. The schedule and attendance features were fully implemented as classes but are not exposed through any demo menu. Each run of Main.java re-creates test data from scratch, so any data saved from a previous session is overwritten by the fresh initialization.
