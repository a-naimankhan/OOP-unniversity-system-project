# Tasks — Research-Oriented University System

> Дедлайны: **Part B** — 15-я неделя (практика) | **Part C + финальный** — финальный экзамен

---

## Статус по паттернам (нужно ≥ 4)

| Паттерн          | Класс(ы)                                               | Статус |
|------------------|--------------------------------------------------------|--------|
| Singleton        | `Database` (getInstance)                               | ✅ Done |
| Observer         | `Subject`, `Observer`, `Journal`, `User.update()`      | ✅ Done |
| Factory Method   | `UserCreator` + `StudentCreator/TeacherCreator/...`    | ✅ Done |
| Decorator        | `IMessage`, `MessageDecorator`, `UrgentMessage`, `EncryptedMessage` | ✅ Done |

**4/4 — требование выполнено.**

---

## PHOENIX (Саша) — Research System + Patterns + Exceptions

### ✅ Всё сделано

- [x] `Researcher` interface (default: `printPapers(Comparator)`, `calculateHIndex()`, `addResearchPaper`)
- [x] `ResearchPaper` (title, authors, journal, pages, date, doi, citations, volume, number)
  - [x] `getCitation(Format f)` — Plain Text и BibTeX
  - [x] `Comparable<ResearchPaper>` по дате
- [x] `ResearchProject` (topic, publishedPapers, participants)
  - [x] `addParticipant()` → бросает `NotResearcherException` для не-исследователей
- [x] `Format` enum (PLAIN_TEXT, BIBTEX)
- [x] Компараторы: `PaperByDate`, `PaperByCitations`, `PaperByPages`
- [x] `GraduateStudent extends Student implements Researcher`
  - [x] researchSupervisor (h-index ≥ 3, иначе `InvalidSupervisorException`)
  - [x] diplomaProject, researchPapers, researchProjects
- [x] `DiplomaProject` (title, publishedPapers)
- [x] Исключения: `InvalidSupervisorException`, `NotResearcherException`, `CreditLimitExceededException`, `MaxFailException`, `LowHIndexException`
- [x] `Database.getAllResearchers()`, `getTopCitedResearcher()`, `getTopCitedResearcherOfSchool(FacultyType)`
- [x] Decorator pattern: `IMessage` интерфейс, `MessageDecorator`, `UrgentMessageDecorator`, `EncryptedMessageDecorator`
- [x] Factory Method: `UserCreator` (abstract) + `StudentCreator`, `TeacherCreator`, `AdminCreator`, `ManagerCreator`; `UserFactory` делегирует в создатели

### 🔧 Осталось у Phoenix

- [ ] `LowHIndexException` — создан, но нигде не бросается. Добавить в `Researcher.addResearchPaper()` или в `ResearchProject` если автор с h-index < 1
- [ ] `Database.serializeJournals()` / `deserializeJournals()` — `journals` вектор есть, но сериализации нет

---

## DERDIEDASX — News, Journals, TechSupport, Messages

### ✅ Всё сделано

- [x] `News` (topic, comments, author, pinned — RESEARCH всегда pinned, Comparable по дате/pin)
- [x] `NewsTopic` enum (RESEARCH, ACADEMIC, GENERAL)
- [x] `Comment` (text, author, date)
- [x] Авто-новость при публикации статьи исследователем
- [x] Авто-новость о топ-цитируемом исследователе
- [x] `Journal implements Subject` (subscribe/unsubscribe/notifyObservers, publishPaper)
- [x] `Observer` interface, `Subject` interface; `User implements Observer`
- [x] `User.subscribeToJournal()`, `unsubscribeFromJournal()`
- [x] `TechSupportSpecialist extends Employee` (viewNew/Accepted/Done, accept/reject/markDone/viewRequest)
- [x] `Request` (description, status, createdBy, date, urgency)
- [x] `RequestStatus` enum (NEW, VIEWED, ACCEPTED, REJECTED, DONE)
- [x] `UrgencyLevel` enum (LOW, MEDIUM, HIGH)
- [x] `Message` (sender, receiver, date, subject) — теперь реализует `IMessage`
- [x] `Complaint extends Message` (student, urgency)
- [x] `OfficialMessage extends Message` (purpose)
- [x] `Teacher.sendComplaint(Student, urgency, dean)`
- [x] `Language` enum (KZ, EN, RU), `LanguageManager`, поле language в User

### 🔧 Осталось у derdiedasx

- [ ] `Request` не имеет поля `signedBy` — по ТЗ запросы должны быть подписаны деканом/ректором (нужно добавить `User signedBy` в Request)
- [ ] `Database.requests` существует, но `TechSupportSpecialist` не добавляет новые запросы сам — нужен метод `createRequest()` либо у User, либо у Student/Employee

---

## ITACHI8539 — Student / Teacher / Manager / Admin + Demo

### ✅ Сделано

- [x] `Transcript` (student, getTranscript() — полный вывод с оценками, GPA, кредитами)

### ❌ Приоритет 1: Student — НЕ СДЕЛАНО

- [ ] `StudentOrganization` класс (name, String role, List<Student> members, Student head)
  - Student должен иметь `List<StudentOrganization> organizations`
  - Методы: `joinOrganization()`, `leaveOrganization()`, `becomeHead()`
- [ ] Ограничение: студент не может провалить курс более 3 раз
  - В `Student`: добавить `Map<Course, Integer> failCount`
  - В `registerCourse()`: если студент уже падал курс 3 раза → бросить `MaxFailException`
- [ ] `CourseType` enum: MAJOR, MINOR, FREE_ELECTIVE
  - Добавить поле `CourseType courseType` в класс `Course`
  - Убрать/заменить boolean `isElective`

### ❌ Приоритет 2: Teacher — НЕ СДЕЛАНО

- [ ] Teacher как Researcher: добавить `ResearchTeacher extends Teacher implements Researcher`
  OR сделать Teacher implement Researcher напрямую (с полями researchPapers, researchProjects)
  - По ТЗ: **Professors (DegreeTeacher.PROFESSOR) — ВСЕГДА Researcher**; остальные опционально
  - Если Teacher.degree == PROFESSOR → обязателен как Researcher
- [ ] Обновить `DegreeTeacher` enum — добавить SENIOR_LECTOR если нет (уже есть SENIOR — ОК)
- [ ] `Teacher.generateReport(Course)` — простая статистика по оценкам курса (средняя, мин, макс)

### ❌ Приоритет 3: Manager — НЕ СДЕЛАНО

- [ ] Статистический отчёт об успеваемости (`AcademicReport` класс или метод `generateReport()`)
  - Средний GPA по факультету, топ студенты, проваленные курсы и т.д.
- [ ] `Course` нужно поле для целевого года обучения и специальности: `int targetYear`, `DegreeStudent targetDegree`
- [ ] Просмотр запросов сотрудников (подписанных деканом/ректором)

### ❌ Приоритет 4: Admin — НЕ СДЕЛАНО

- [ ] Логирование действий пользователей (`LogEntry` класс или просто список строк в Database)
  - `Database.logs = new Vector<String>()`
  - Метод `Database.log(String action)`
  - `Admin.viewLogs()` — возвращает список логов
- [ ] `Admin.addUser()` / `removeUser()` должны логировать действие

### ❌ Приоритет 5: Console Demo (Part C) — НЕ СДЕЛАНО

- [ ] Обновить `StudentDemo` — добавить организации, Transcript
- [ ] Обновить `TeacherDemo` — добавить research, отчёты
- [ ] Обновить `ManagerDemo` — статистика, отчёты
- [ ] Обновить `AdminDemo` — логи
- [ ] Добавить `ResearcherDemo` (публикация, h-index, comparators)
- [ ] Добавить `TechSupportDemo`
- [ ] Добавить `GraduateStudentDemo`
- [ ] `Main.java` — полный flow с аутентификацией (login → меню по роли)

---

## 🐛 Баги в существующем коде (нужно исправить)

| Файл | Баг | Приоритет |
|------|-----|-----------|
| `mark/Mark.java` | `convertToLetterMark()` и `convertToGPA()` используют поле `finalAttestation` (=0), а не метод `getFinalAttestation()` — все оценки всегда "F" | **КРИТИЧНО** |
| `users/Student.java` | `dropCourse()` проверяет `getFinalAttestation() == 0`, что ВСЕГДА true для новых отметок — студент может дропнуть любой курс в любой момент | HIGH |
| `users/Teacher.java` | `getRating()` в catch-блоке `ArithmeticException` снова делит `rating / ratedCnt` → бросит снова. Нужен `return 0;` | HIGH |
| `users/Student.java` | `registerCourse()` не бросает `CreditLimitExceededException` — просто возвращает false | MED |
| `users/Student.java` | Нет проверки на 3 фейла (`MaxFailException`) в `registerCourse()` | MED |
| `users/Database.java` | `journals` в Database есть, но нет `serializeJournals()` / `deserializeJournals()` | MED |

### Исправление для Mark (КРИТИЧНО):

В `Mark.convertToLetterMark()` изменить:
```java
// было:
if (finalAttestation >= 95 ...
// стало:
double total = getFinalAttestation();
if (total >= 95 ...
```

---

## Сводная таблица команды

| Участник        | Зона ответственности        | Ключевые классы                                          | Статус    |
|-----------------|-----------------------------|----------------------------------------------------------|-----------|
| phoenix (Саша)  | Research + patterns + bugs  | Researcher, ResearchPaper, ResearchProject, GraduateStudent, Decorator, Factory | ✅ В основном готово |
| derdiedasx      | News, journals, tech support| Journal, Observer, TechSupportSpecialist, Complaint, Message | ✅ В основном готово |
| itachi8539      | Student/Teacher/Mgr + demo  | StudentOrganization, CourseType, LogAction, все Demo-классы | ❌ Бо́льшая часть не сделана |

---

## Чек-лист Part B (показываем на 15-й неделе)

Минимум, который должен быть:

- [x] Все классы из ТЗ существуют (User, Employee, Teacher, Manager, Student, GraduateStudent, Admin, Course, Mark, Lesson, TechSupportSpecialist, Researcher, ResearchPaper, ResearchProject, News, Message)
- [x] Все enum из ТЗ (DegreeStudent, DegreeTeacher, FacultyType, LessonType, Period, AttestationType, ManagerType, Language, UrgencyLevel, RequestStatus, Format)
- [x] Свои исключения (≥ 4 шт.)
- [x] ≥ 4 паттерна (Observer, Singleton, Factory Method, Decorator)
- [x] Serialization (Database)
- [x] Comparable + Comparators
- [ ] `CourseType` enum (MAJOR, MINOR, FREE_ELECTIVE)
- [ ] `StudentOrganization`
- [ ] Teacher implements Researcher (для профессоров)
- [ ] Исправить баг в `Mark.convertToLetterMark()`
- [x] `Transcript` класс

---

## Бонусы (extra points)

- [ ] Расписание (Schedule): классы `Room` (type, capacity, number), `Schedule` (lessons by day/time)
- [ ] Посещаемость (Attendance)
- [ ] Расширенный поиск (RegEx)
- [ ] Стартапы, рекомендательные письма

---

## Дедлайны

| Часть    | Срок                         |
|----------|------------------------------|
| Part A   | 14-я неделя (диаграммы)      |
| Part B   | 15-я неделя (практика) — черновик; финальный экзамен — финальная версия |
| Part C   | Финальный экзамен            |

---

## 🔍 Code Review Action Items (2026-05-04)

### 🔥 P0 — Демка упадёт / сломается без этих фиксов
- [x] **`User implements Serializable`** — `users/User.java:15`. Также сменил `Comparable<Object>` → `Comparable<User>`, добавил `serialVersionUID`.
- [x] **`Time implements Serializable`** — `other/Time.java:6`. + поля переведены в `private`.
- [x] **Убрать хардкод `C:\OOP\test\`** — `users/Database.java`. Теперь `data/` в рабочей директории, авто-создаётся через `Files.createDirectories`.
- [x] **Починить `compareTo`, возвращающий 0 при разных именах** — `Student/Teacher/Employee.compareTo` теперь возвращают `super.compareTo` при разных именах, делают tie-break по GPA / rating / salary.
- [x] **Убрать баги `if(...);` в демках** — `StudentDemo.register/drop` и `ManagerDemo.addCourse/removeCourse/addNews/removeNews/updateNews/assignCourse` переписаны с настоящими блоками. Также фикснут баг `addCourse` (потерянный `courseCode`).
- [x] **Добавить `Database.load()`** — теперь восстанавливает все 12 коллекций (включая journals/requests/logs).
- [x] **Защитить `Student.getGpa()` от `cnt == 0`** — `cnt == 0 → return 0.0` (раньше делил на ноль и Transcript показывал `NaN`).

### ⚠️ P1 — Требования ТЗ
- [x] `CourseType` enum (MAJOR/MINOR/FREE_ELECTIVE) + заменить `boolean isElective` — `course/CourseType.java` создан; `Course.java` обновлён (обратно-совместимый конструктор сохранён)
- [x] `StudentOrganization` класс + `Student.organizations` — `users/StudentOrganization.java` создан; `Student` получил `organizations`, `joinOrganization/leaveOrganization/becomeHead`
- [x] `Student.failCount` мапа + бросать `MaxFailException` в `registerCourse` после 3 фейлов — `Student.java` полностью переписан
- [x] `Student.registerCourse` бросает `CreditLimitExceededException` (а не `return false`) — там же; `creditLimit` стал `private static final MAX_CREDITS`
- [x] `Teacher` (хотя бы PROFESSOR) implements `Researcher` — `users/ResearchTeacher.java` создан (`extends Teacher implements Researcher`)
- [x] `Database.logs` + `Admin.viewLogs()` + логирование `addUser/removeUser` — `Database.log()` добавлен; `Admin.java` полностью переписан с логами
- [x] `Database.serializeJournals` / `serializeRequests` — добавлены `serializeJournals/Requests/Logs` + `Database.load()` восстанавливает все 12 коллекций
- [x] `Request.signedBy` + `User.createRequest()` — `Request.java` получил поле `signedBy` + `signBy(User)`; `User.createRequest()` добавлен
- [x] `Manager.generateReport()` — академическая статистика по GPA, топ-студент, курсы с фейлами; фильтр по `FacultyType`
- [x] `Course.targetYear`, `Course.targetDegree` — добавлены в `Course.java`; новый полный конструктор + обратно-совместимый
- [x] `Teacher.generateReport(Course)` — статистика avg/min/max/passing по курсу
- [x] Бросать `LowHIndexException` — в `ResearchProject.addParticipant`: исследователь с бумагами но h-index=0 не может войти в проект
- [x] Починить `Teacher.sendComplaint` — теперь создаёт `Request`, кладёт в `Database.requests`, логирует через `Database.log()`
- [x] Рефактор Singleton — `public Database()` → `private Database()`, паттерн теперь не нарушается
- [ ] **Подключить `LanguageManager.getTranslation` в реальные меню демок** — ⬅ единственное незакрытое в P1
- [ ] **Обновить `Main.java`** под новые checked exceptions (`registerCourse` бросает 2 исключения, `addParticipant` — теперь тоже `LowHIndexException`)

### 🧹 P2 — Качество кода
- [ ] Убрать `prerequisites` из `Course.equals/hashCode` (риск StackOverflow)
- [ ] Убрать derived/mutable поля из `Student/Teacher.equals/hashCode`
- [ ] Починить математику `Teacher.setRating`
- [ ] Починить `Teacher.putMark` ветку FIRST (мутация, не замена)
- [ ] Валидация переходов в `TechSupportSpecialist` (NEW→VIEWED→ACCEPTED→DONE)
- [ ] Null-гарды в `ResearchPaper.getCitation` (пустые authors, null date)
- [ ] Централизованная аутентификация + логин-флоу в `Main`
- [ ] Демки: `ResearcherDemo`, `TechSupportDemo`, `GraduateStudentDemo`
- [ ] Удалить мёртвое поле `Mark.finalAttestation`
