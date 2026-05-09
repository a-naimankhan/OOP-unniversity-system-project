# University Management System

Консольная система управления университетом на Java. Реализует роли пользователей, управление курсами, оценками, исследовательской деятельностью и новостями.

---

## Запуск

### Требования
- Java 17+
- VS Code с расширением Java Extension Pack (опционально)

### VS Code (рекомендуется)
Открыть папку `d:\8.Java` в VS Code → нажать **F5**.

### Терминал (PowerShell)
```powershell
cd "d:\8.Java\OOP-unniversity-system-project"

# Компиляция
Get-ChildItem -Recurse -Filter "*.java" | Select-Object -ExpandProperty FullName | Out-File sources.txt -Encoding utf8
javac -encoding UTF-8 -d out "@sources.txt"

# Запуск
chcp 65001
java "-Dfile.encoding=UTF-8" "-Dstdout.encoding=UTF-8" "-Dstderr.encoding=UTF-8" -cp out demo.Main
```

```
cd "d:\8.Java\OOP-unniversity-system-project"; chcp 65001; java "-Dfile.encoding=UTF-8" "-Dstdout.encoding=UTF-8" "-Dstderr.encoding=UTF-8" -cp out demo.Main 
```
> `chcp 65001` — включает UTF-8 в терминале для корректного отображения кириллицы.

### Логины для входа

| Роль              | Логин    | Пароль     |
|-------------------|----------|------------|
| Admin             | admin    | admin123   |
| Teacher (Prof)    | almas    | pass123    |
| Teacher           | sanzhar  | pass456    |
| Student           | aidar    | student1   |
| Student           | marat    | student2   |
| GraduateStudent   | dana     | grad1      |
| Manager           | bolat    | mgr123     |

После первого запуска все логины сохраняются в `data/credentials.txt`.

---

## Структура пакетов

```
OOP-unniversity-system-project/
├── users/          # Пользователи и база данных
├── course/         # Курсы, расписание, посещаемость
├── mark/           # Оценки и аттестации
├── research/       # Исследования, статьи, журналы
├── other/          # Сообщения, новости, заявки, стартапы
├── exceptions/     # Собственные исключения
└── demo/           # Точка входа и меню для каждой роли
```

---

## Пакеты подробно

### `users/` — пользователи
Иерархия наследования:
```
User
├── Employee
│   ├── Teacher
│   │   └── ResearchTeacher  (implements Researcher)
│   ├── Manager
│   ├── Admin
│   └── TechSupportSpecialist
└── Student
    └── GraduateStudent      (implements Researcher)
```
- **`User`** — базовый класс: fullName, username, password, login(), язык интерфейса
- **`Employee`** — добавляет salary, sendMessage(), createRequest()
- **`Teacher`** — putMark(), generateReport(), viewStudents(), рейтинг
- **`Manager`** — управление курсами и новостями, просмотр студентов по GPA
- **`Admin`** — addUser(), removeUser(), viewLogs(), поиск по RegEx
- **`TechSupportSpecialist`** — просмотр и обработка заявок
- **`Student`** — registerCourse(), dropCourse(), viewMarks(), getGpa()
- **`GraduateStudent`** — расширяет Student, реализует Researcher: статьи, диплом, supervisor
- **`Database`** — Singleton, хранит все коллекции (users, courses, news...), сериализация, логи
- **`Transcript`** — формирует официальную выписку оценок студента

### `course/` — учебный процесс
- **`Course`** — название, код, кредиты, семестр, факультет, лимит студентов
- **`Lesson`** — конкретное занятие: тип (лекция/практика), аудитория, время
- **`Schedule`** — расписание курса (список уроков)
- **`Room`** — аудитория с вместимостью
- **`Attendance`** — посещаемость студента на занятии
- **Enums**: `Period` (FALL/SPRING/SUMMER), `LessonType`, `CourseType`, `Day`

### `mark/` — оценки
- **`Mark`** — первая аттестация (макс 30) + вторая (макс 30) + экзамен (макс 40) = итог из 100
- **`AttestationType`** — FIRST / SECOND / EXAM

### `research/` — исследовательская деятельность
- **`Researcher`** — интерфейс: addResearchPaper(), printPapers(), calculateHIndex()
- **`ResearchPaper`** — статья: авторы, журнал, DOI, цитирования; getCitation(Format) — Plain Text или BibTeX
- **`ResearchProject`** — проект с участниками (только Researcher могут вступить)
- **`DiplomaProject`** — дипломный проект GraduateStudent (список статей)
- **`Journal`** — реализует Subject (Observer pattern): подписка, уведомление при публикации
- **`PaperByDate` / `PaperByCitations` / `PaperByPages`** — компараторы для сортировки статей
- **`Format`** — enum PLAIN_TEXT / BIBTEX

### `other/` — вспомогательные классы
- **`News`** — новость: тема (RESEARCH/ACADEMIC/GENERAL), комментарии, автор, дата
- **`Message`** / **`Complaint`** / **`OfficialMessage`** — система сообщений между пользователями
- **`IMessage`** / **`MessageDecorator`** / **`UrgentMessageDecorator`** / **`EncryptedMessageDecorator`** — Decorator pattern
- **`Request`** — заявка в техподдержку: статус (NEW→VIEWED→ACCEPTED→DONE), приоритет
- **`Startup`** — стартап GraduateStudent: название, описание, участники
- **`RecommendationLetter`** — рекомендательное письмо от Teacher для Student
- **`LanguageManager`** — статические переводы строк для EN / RU / KZ
- **`Language`** — enum EN / RU / KZ

### `exceptions/` — исключения
| Класс | Когда бросается |
|-------|----------------|
| `CreditLimitExceededException` | регистрация курса превысит 21 кредит |
| `MaxFailException` | студент провалил курс 3 раза |
| `NotResearcherException` | не-Researcher пытается вступить в проект |
| `InvalidSupervisorException` | supervisor с h-index < 3 |
| `LowHIndexException` | h-index недостаточен для операции |

### `demo/` — меню и запуск
- **`Main`** — точка входа: инициализация данных, выбор языка, логин, роутинг по роли
- **`StudentDemo`** — регистрация/отписка от курсов, оценки, транскрипт, новости
- **`TeacherDemo`** — выставление оценок, отчёт по курсу, рекомендательные письма
- **`ManagerDemo`** — управление курсами, новостями, просмотр студентов и преподавателей
- **`AdminDemo`** — управление пользователями, логи, поиск по RegEx
- **`GraduateStudentDemo`** — статьи, h-index, стартапы, рекомендательные письма
- **`TechSupportDemo`** — заявки в техподдержку
- **`ResearcherDemo`** — расширенная работа с исследованиями
- **`UserCreator`** / `StudentCreator` / `TeacherCreator` / ... — Factory Method pattern

---

## Паттерны проектирования

| Паттерн | Реализация |
|---------|-----------|
| **Singleton** | `Database` — один экземпляр на всю программу |
| **Observer** | `Journal` (Subject) уведомляет `User` (Observer) о публикации статей |
| **Factory Method** | `UserCreator` и подклассы создают пользователей нужного типа |
| **Decorator** | `IMessage` → `MessageDecorator` → `UrgentMessageDecorator` / `EncryptedMessageDecorator` |
