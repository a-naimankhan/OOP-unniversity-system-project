# University System — задачи команды

Дедлайны: Part B — 15 неделя (практика) | Part C — финальный экзамен

---

## Паттерны (нужно ≥ 4)

| Паттерн        | Где реализован                                              | Статус  |
|----------------|-------------------------------------------------------------|---------|
| Singleton      | `Database` — один экземпляр на всю программу               | готово  |
| Observer       | `Journal`, `Subject`, `Observer`, `User.update()`          | готово  |
| Factory Method | `UserCreator` + `StudentCreator`, `TeacherCreator` и др.   | готово  |
| Decorator      | `IMessage`, `MessageDecorator`, `UrgentMessage`, `EncryptedMessage` | готово |

---

## Саша (Phoenix) — Research, паттерны, исключения

- [x] интерфейс `Researcher` (printPapers, calculateHIndex, addResearchPaper)
- [x] `ResearchPaper` — title, authors, journal, pages, date, doi, citations, volume, number
- [x] `ResearchPaper.getCitation(Format)` — Plain Text и BibTeX форматы
- [x] `ResearchProject` — topic, participants; исключение если не Researcher
- [x] `Format` enum (PLAIN_TEXT, BIBTEX)
- [x] компараторы: `PaperByDate`, `PaperByCitations`, `PaperByPages`
- [x] `GraduateStudent extends Student implements Researcher`
- [x] supervisor с проверкой h-index ≥ 3 (`InvalidSupervisorException`)
- [x] `DiplomaProject` — список статей как дипломный проект
- [x] исключения: `InvalidSupervisorException`, `NotResearcherException`, `CreditLimitExceededException`, `MaxFailException`, `LowHIndexException`
- [x] `Database.getAllResearchers()`, `getTopCitedResearcher()`, `getTopCitedResearcherOfSchool()`
- [x] Decorator: `IMessage`, `MessageDecorator`, `UrgentMessageDecorator`, `EncryptedMessageDecorator`
- [x] Factory Method: `UserCreator` + `StudentCreator`, `TeacherCreator`, `AdminCreator`, `ManagerCreator`

---

## Derdiedasx — новости, журналы, техподдержка, сообщения

- [x] `News` — topic, comments, author; RESEARCH всегда pinned, сортировка по дате
- [x] `NewsTopic` enum (RESEARCH, ACADEMIC, GENERAL)
- [x] `Comment` (text, author, date)
- [x] авто-новость при публикации статьи исследователем
- [x] авто-новость о топ-цитируемом исследователе университета
- [x] `Journal implements Subject` — subscribe/unsubscribe/notifyObservers, publishPaper
- [x] `Observer`, `Subject` интерфейсы; `User implements Observer`
- [x] `User.subscribeToJournal()`, `unsubscribeFromJournal()`
- [x] `TechSupportSpecialist extends Employee` — просмотр/принятие/отклонение заявок
- [x] `Request` — description, status, createdBy, date, urgency, signedBy
- [x] `RequestStatus` enum (NEW, VIEWED, ACCEPTED, REJECTED, DONE)
- [x] `UrgencyLevel` enum (LOW, MEDIUM, HIGH)
- [x] `Message`, `Complaint extends Message`, `OfficialMessage extends Message`
- [x] `Teacher.sendComplaint(Student, urgency, dean)`
- [x] `Language` enum (KZ, EN, RU), `LanguageManager`, поле language у User

---

## Itachi8539 — Student, Teacher, Manager, Admin, Demo

- [x] `Transcript` — полный вывод оценок, GPA, кредитов
- [x] `StudentOrganization` — name, members, head; методы join/leave/becomeHead
- [x] ограничение: студент не может записаться на курс больше 3 раз (`MaxFailException`)
- [x] ограничение: не более 21 кредита (`CreditLimitExceededException`)
- [x] `CourseType` enum (MAJOR, MINOR, FREE_ELECTIVE) вместо boolean isElective
- [x] `ResearchTeacher extends Teacher implements Researcher` — профессора всегда Researcher
- [x] `Teacher.generateReport(Course)` — avg/min/max/passing статистика
- [x] `Manager.generateReport()` — средний GPA по факультету, топ студенты
- [x] `Course.targetYear`, `Course.targetDegree` — для какого года и степени курс
- [x] `Database.logs` + `Admin.viewLogs()` + логирование addUser/removeUser
- [x] `StudentDemo`, `TeacherDemo`, `ManagerDemo`, `AdminDemo` — консольные меню
- [x] `ResearcherDemo`, `TechSupportDemo`, `GraduateStudentDemo` — консольные меню
- [x] `Main.java` — аутентификация, выбор языка, роутинг по роли

---

## Чек-лист Part B / Part C

- [x] все классы из ТЗ (User, Employee, Teacher, Manager, Student, GraduateStudent, Admin, Course, Mark, Lesson, TechSupportSpecialist, Researcher, ResearchPaper, ResearchProject, News, Message)
- [x] все enum (DegreeStudent, DegreeTeacher, FacultyType, LessonType, Period, AttestationType, ManagerType, Language, UrgencyLevel, RequestStatus, Format, CourseType)
- [x] собственные исключения (5 штук)
- [x] ≥ 4 паттерна (Observer, Singleton, Factory Method, Decorator)
- [x] сериализация (Database — все коллекции сохраняются и загружаются)
- [x] Comparable + Comparators
- [x] аутентификация (login → меню по роли)
- [x] переключение языков KZ/EN/RU

---

## Бонусы

- [x] расписание — `Room`, `Schedule`, `Lesson` с типами лекция/практика
- [x] посещаемость — `Attendance`
- [x] поиск по RegEx — `Database.searchUsers/Courses/ResearchPapers(regex)`, меню в AdminDemo
- [x] стартапы — `Startup.java`, создание в GraduateStudentDemo
- [x] рекомендательные письма — `RecommendationLetter.java`, написание в TeacherDemo

---

## Дедлайны

| Часть  | Срок                                       |
|--------|--------------------------------------------|
| Part A | 14 неделя — диаграммы                      |
| Part B | 15 неделя (практика) + финальный экзамен   |
| Part C | финальный экзамен                          |
