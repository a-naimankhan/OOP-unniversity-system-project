package other;

import java.util.HashMap;
import java.util.Map;

public class LanguageManager {
    private static final Map<Language, Map<String, String>> translations = new HashMap<>();

    static {
        // ── English ──────────────────────────────────────────────────────────
        Map<String, String> en = new HashMap<>();
        // login / общее
        en.put("select_language",   "Select language:\n 1) English\n 2) Russian\n 3) Kazakh");
        en.put("welcome",           "Welcome to the University System!");
        en.put("enter_username",    "Username: ");
        en.put("enter_password",    "Password: ");
        en.put("login_success",     "Login successful!");
        en.put("login_fail",        "Login failed. Invalid username or password.");
        en.put("menu_logout",       "Logging out...");
        en.put("menu_back",         "Back");
        en.put("menu_exit",         "Exit");
        en.put("prompt_continue",   " 1) Again  2) Back  3) Exit");
        en.put("again_back",        " 1) Again  2) Back");
        en.put("back_exit",         " 1) Back  2) Exit");
        en.put("course_not_found",  "Course not found.");
        en.put("teacher_not_found", "Teacher not found.");
        en.put("student_not_found", "Student not found.");
        en.put("no_courses",        "No courses available.");
        en.put("no_students",       "No students available.");
        en.put("no_teachers",       "No teachers available.");
        en.put("pick_course",       "Courses:");
        en.put("pick_student",      "Students:");
        en.put("pick_teacher",      "Teachers:");
        en.put("pick_0_cancel",     "Choice (0 to cancel): ");
        en.put("no_news",           "No news available.");
        en.put("menu_news",         "=== News ===");

        // Student
        en.put("student_menu",      "What do you want to do?\n 1) Register for course\n 2) Drop course\n 3) Rate teacher\n 4) View courses\n 5) View teacher info\n 6) View marks\n 7) View transcript\n 8) View news\n 9) Logout");
        en.put("reg_success",       "Registered successfully.");
        en.put("reg_fail",          "Registration failed: ");
        en.put("drop_success",      "Dropped successfully.");
        en.put("drop_fail",         "Cannot drop — course already has marks.");
        en.put("menu_rate_point",   "Enter rating (0-5): ");
        en.put("rated",             "Rated successfully.");
        en.put("no_registered",     "No registered courses.");
        en.put("your_courses",      "Your registered courses:");
        en.put("all_marks",         "All marks:");
        en.put("no_marks",          "No marks yet.");
        en.put("marks_submenu",     " 1) Course marks  2) All marks  3) Back");

        // Teacher
        en.put("teacher_menu",      "What do you want to do?\n 1) Put mark\n 2) View courses\n 3) View students\n 4) Generate report\n 5) Write recommendation letter\n 6) Logout");
        en.put("attestation_type",  "Attestation type: 1) First  2) Second  3) Exam");
        en.put("enter_point",       "Enter point: ");
        en.put("mark_added",        "Mark added: ");
        en.put("mark_fail",         "Could not add mark (student not registered for this course?).");
        en.put("report_label",      "=== Report: ");
        en.put("rec_content",       "Enter letter content: ");
        en.put("rec_saved",         "Recommendation letter saved.");

        // Manager
        en.put("manager_menu",      "What do you want to do?\n 1) Manage courses\n 2) Manage news\n 3) Assign courses to teachers\n 4) View students\n 5) View teachers\n 6) Send message\n 7) View messages\n 8) Logout");
        en.put("courses_submenu",   "Courses:\n 1) Add course\n 2) Remove course");
        en.put("news_submenu",      "News:\n 1) Add\n 2) Remove\n 3) Update");
        en.put("students_submenu",  "View students:\n 1) By name\n 2) By GPA");
        en.put("enter_cname",       "Enter course name: ");
        en.put("enter_semester",    "Semester: 1) Fall  2) Spring  3) Summer");
        en.put("enter_code",        "Enter course code: ");
        en.put("enter_credits",     "Enter credits: ");
        en.put("is_elective",       "Elective? 1) No  2) Yes");
        en.put("enter_limit",       "Enter student limit: ");
        en.put("enter_prereq",      "Prerequisite course name (or Enter to skip): ");
        en.put("enter_dept",        "Department (FIT/BS/CCE/FEOG/FGE/FGGE/ISE/KMA/SMS): ");
        en.put("course_added",      "Course added!");
        en.put("course_exists",     "Course already exists.");
        en.put("course_removed",    "Course removed!");
        en.put("enter_ntitle",      "Enter news title: ");
        en.put("enter_ntext",       "Enter news text: ");
        en.put("enter_ndate",       "Enter news date: ");
        en.put("news_added",        "News added!");
        en.put("news_removed",      "News removed!");
        en.put("news_updated",      "News updated!");
        en.put("news_not_found",    "News not found.");
        en.put("assign_ok",         "Assigned successfully!");
        en.put("no_assign_data",    "No courses or teachers available.");
        en.put("enter_receiver",    "Receiver name: ");
        en.put("enter_msg_text",    "Message text: ");
        en.put("msg_sent",          "Message sent.");
        en.put("no_messages",       "No messages.");

        // Admin
        en.put("admin_menu",        "What do you want to do?\n 1) Add user\n 2) Remove user\n 3) View all users\n 4) View logs\n 5) Search (RegEx)\n 6) Logout");
        en.put("enter_user_type",   "User type (student/teacher/manager/admin): ");
        en.put("enter_full_name",   "Full name: ");
        en.put("enter_salary",      "Salary: ");
        en.put("enter_student_id",  "Student ID: ");
        en.put("enter_year",        "Study year: ");
        en.put("user_added",        "User added.");
        en.put("unknown_type",      "Unknown user type.");
        en.put("remove_username",   "Username to remove: ");
        en.put("user_removed",      "User removed.");
        en.put("user_not_found",    "User not found.");
        en.put("all_users_header",  "=== All Users ===");
        en.put("logs_header",       "=== Logs ===");
        en.put("no_logs",           "No logs.");
        en.put("search_in",         "Search in: 1) Users  2) Courses  3) Research papers");
        en.put("enter_regex",       "Regex pattern: ");
        en.put("found_prefix",      "Found ");
        en.put("found_users_s",     " user(s):");
        en.put("found_courses_s",   " course(s):");
        en.put("found_papers_s",    " paper(s):");
        en.put("invalid_regex",     "Invalid regex: ");

        // GraduateStudent
        en.put("grad_menu",         "GraduateStudent Menu:\n 1) Add research paper\n 2) Print papers\n 3) Join research project\n 4) View h-index\n 5) Create startup\n 6) View recommendation letters\n 7) Logout");
        en.put("title_prompt",      "Title: ");
        en.put("authors_prompt",    "Authors (comma-separated): ");
        en.put("journal_prompt",    "Journal: ");
        en.put("pages_prompt",      "Pages: ");
        en.put("year_prompt",       "Year (e.g. 2024): ");
        en.put("doi_prompt",        "DOI (or Enter to skip): ");
        en.put("citations_prompt",  "Citations: ");
        en.put("volume_prompt",     "Volume: ");
        en.put("number_prompt",     "Number: ");
        en.put("paper_added",       "Paper added.");
        en.put("paper_fail",        "Failed: ");
        en.put("sort_by",           "Sort by: 1) Date  2) Citations  3) Pages");
        en.put("project_topic",     "Project topic: ");
        en.put("joined_project",    "Joined project: ");
        en.put("cannot_join",       "Cannot join: ");
        en.put("startup_name",      "Startup name: ");
        en.put("startup_desc",      "Description: ");
        en.put("startup_created",   "Startup created!");
        en.put("no_rec_letters",    "No recommendation letters found.");

        // TechSupport
        en.put("ts_menu",           "TechSupport Menu:\n 1) View all requests\n 2) View NEW requests\n 3) Accept request\n 4) Reject request\n 5) Mark DONE\n 6) Logout");
        en.put("no_requests",       "No requests.");
        en.put("idx_accept",        "Request index to ACCEPT: ");
        en.put("idx_reject",        "Request index to REJECT: ");
        en.put("idx_done",          "Request index to mark DONE: ");
        en.put("invalid_index",     "Invalid index.");

        translations.put(Language.EN, en);

        // ── Russian ──────────────────────────────────────────────────────────
        Map<String, String> ru = new HashMap<>();
        // login / общее
        ru.put("select_language",   "Select language:\n 1) English\n 2) Russian\n 3) Kazakh");
        ru.put("welcome",           "Добро пожаловать в университетскую систему!");
        ru.put("enter_username",    "Имя пользователя: ");
        ru.put("enter_password",    "Пароль: ");
        ru.put("login_success",     "Вход выполнен успешно!");
        ru.put("login_fail",        "Ошибка входа. Неверный логин или пароль.");
        ru.put("menu_logout",       "Выход из системы...");
        ru.put("menu_back",         "Назад");
        ru.put("menu_exit",         "Выход");
        ru.put("prompt_continue",   " 1) Ещё раз  2) Назад  3) Выход");
        ru.put("again_back",        " 1) Ещё раз  2) Назад");
        ru.put("back_exit",         " 1) Назад  2) Выход");
        ru.put("course_not_found",  "Курс не найден.");
        ru.put("teacher_not_found", "Преподаватель не найден.");
        ru.put("student_not_found", "Студент не найден.");
        ru.put("no_courses",        "Нет доступных курсов.");
        ru.put("no_students",       "Нет студентов.");
        ru.put("no_teachers",       "Нет преподавателей.");
        ru.put("pick_course",       "Курсы:");
        ru.put("pick_student",      "Студенты:");
        ru.put("pick_teacher",      "Преподаватели:");
        ru.put("pick_0_cancel",     "Выбор (0 — отмена): ");
        ru.put("no_news",           "Новостей нет.");
        ru.put("menu_news",         "=== Новости ===");

        // Student
        ru.put("student_menu",      "Что вы хотите сделать?\n 1) Записаться на курс\n 2) Отписаться от курса\n 3) Оценить преподавателя\n 4) Мои курсы\n 5) Информация о преподавателе\n 6) Оценки\n 7) Транскрипт\n 8) Новости\n 9) Выход");
        ru.put("reg_success",       "Успешно записаны.");
        ru.put("reg_fail",          "Ошибка записи: ");
        ru.put("drop_success",      "Успешно отписаны.");
        ru.put("drop_fail",         "Нельзя отписаться — есть оценки.");
        ru.put("menu_rate_point",   "Оценка (0-5): ");
        ru.put("rated",             "Оценка выставлена.");
        ru.put("no_registered",     "Нет записанных курсов.");
        ru.put("your_courses",      "Ваши курсы:");
        ru.put("all_marks",         "Все оценки:");
        ru.put("no_marks",          "Оценок пока нет.");
        ru.put("marks_submenu",     " 1) Оценки по курсу  2) Все оценки  3) Назад");

        // Teacher
        ru.put("teacher_menu",      "Что вы хотите сделать?\n 1) Выставить оценку\n 2) Мои курсы\n 3) Список студентов\n 4) Отчёт по курсу\n 5) Написать рекомендацию\n 6) Выход");
        ru.put("attestation_type",  "Тип аттестации: 1) Первая  2) Вторая  3) Экзамен");
        ru.put("enter_point",       "Введите балл: ");
        ru.put("mark_added",        "Оценка добавлена: ");
        ru.put("mark_fail",         "Не удалось добавить оценку (студент не записан на курс?).");
        ru.put("report_label",      "=== Отчёт: ");
        ru.put("rec_content",       "Содержание письма: ");
        ru.put("rec_saved",         "Рекомендательное письмо сохранено.");

        // Manager
        ru.put("manager_menu",      "Что вы хотите сделать?\n 1) Управление курсами\n 2) Управление новостями\n 3) Назначить курс преподавателю\n 4) Студенты\n 5) Преподаватели\n 6) Отправить сообщение\n 7) Входящие сообщения\n 8) Выход");
        ru.put("courses_submenu",   "Курсы:\n 1) Добавить курс\n 2) Удалить курс");
        ru.put("news_submenu",      "Новости:\n 1) Добавить\n 2) Удалить\n 3) Обновить");
        ru.put("students_submenu",  "Студенты:\n 1) По имени\n 2) По GPA");
        ru.put("enter_cname",       "Название курса: ");
        ru.put("enter_semester",    "Семестр: 1) Осень  2) Весна  3) Лето");
        ru.put("enter_code",        "Код курса: ");
        ru.put("enter_credits",     "Количество кредитов: ");
        ru.put("is_elective",       "Элективный? 1) Нет  2) Да");
        ru.put("enter_limit",       "Лимит студентов: ");
        ru.put("enter_prereq",      "Пресерквизит (или Enter для пропуска): ");
        ru.put("enter_dept",        "Факультет (FIT/BS/CCE/FEOG/FGE/FGGE/ISE/KMA/SMS): ");
        ru.put("course_added",      "Курс добавлен!");
        ru.put("course_exists",     "Курс уже существует.");
        ru.put("course_removed",    "Курс удалён!");
        ru.put("enter_ntitle",      "Заголовок новости: ");
        ru.put("enter_ntext",       "Текст новости: ");
        ru.put("enter_ndate",       "Дата новости: ");
        ru.put("news_added",        "Новость добавлена!");
        ru.put("news_removed",      "Новость удалена!");
        ru.put("news_updated",      "Новость обновлена!");
        ru.put("news_not_found",    "Новость не найдена.");
        ru.put("assign_ok",         "Назначено успешно!");
        ru.put("no_assign_data",    "Нет курсов или преподавателей.");
        ru.put("enter_receiver",    "Имя получателя: ");
        ru.put("enter_msg_text",    "Текст сообщения: ");
        ru.put("msg_sent",          "Сообщение отправлено.");
        ru.put("no_messages",       "Сообщений нет.");

        // Admin
        ru.put("admin_menu",        "Что вы хотите сделать?\n 1) Добавить пользователя\n 2) Удалить пользователя\n 3) Все пользователи\n 4) Журнал событий\n 5) Поиск (RegEx)\n 6) Выход");
        ru.put("enter_user_type",   "Тип (student/teacher/manager/admin): ");
        ru.put("enter_full_name",   "Полное имя: ");
        ru.put("enter_salary",      "Зарплата: ");
        ru.put("enter_student_id",  "ID студента: ");
        ru.put("enter_year",        "Год обучения: ");
        ru.put("user_added",        "Пользователь добавлен.");
        ru.put("unknown_type",      "Неизвестный тип пользователя.");
        ru.put("remove_username",   "Логин для удаления: ");
        ru.put("user_removed",      "Пользователь удалён.");
        ru.put("user_not_found",    "Пользователь не найден.");
        ru.put("all_users_header",  "=== Все пользователи ===");
        ru.put("logs_header",       "=== Журнал событий ===");
        ru.put("no_logs",           "Нет записей.");
        ru.put("search_in",         "Поиск: 1) Пользователи  2) Курсы  3) Статьи");
        ru.put("enter_regex",       "Регулярное выражение: ");
        ru.put("found_prefix",      "Найдено ");
        ru.put("found_users_s",     " пользователей:");
        ru.put("found_courses_s",   " курсов:");
        ru.put("found_papers_s",    " статей:");
        ru.put("invalid_regex",     "Неверное регулярное выражение: ");

        // GraduateStudent
        ru.put("grad_menu",         "Меню аспиранта:\n 1) Добавить статью\n 2) Список статей\n 3) Вступить в проект\n 4) Мой h-index\n 5) Создать стартап\n 6) Рекомендательные письма\n 7) Выход");
        ru.put("title_prompt",      "Название: ");
        ru.put("authors_prompt",    "Авторы (через запятую): ");
        ru.put("journal_prompt",    "Журнал: ");
        ru.put("pages_prompt",      "Страниц: ");
        ru.put("year_prompt",       "Год (напр. 2024): ");
        ru.put("doi_prompt",        "DOI (или Enter для пропуска): ");
        ru.put("citations_prompt",  "Цитирований: ");
        ru.put("volume_prompt",     "Том: ");
        ru.put("number_prompt",     "Номер: ");
        ru.put("paper_added",       "Статья добавлена.");
        ru.put("paper_fail",        "Ошибка: ");
        ru.put("sort_by",           "Сортировка: 1) По дате  2) По цитированиям  3) По объёму");
        ru.put("project_topic",     "Тема проекта: ");
        ru.put("joined_project",    "Вступили в проект: ");
        ru.put("cannot_join",       "Не удалось вступить: ");
        ru.put("startup_name",      "Название стартапа: ");
        ru.put("startup_desc",      "Описание: ");
        ru.put("startup_created",   "Стартап создан!");
        ru.put("no_rec_letters",    "Рекомендательных писем нет.");

        // TechSupport
        ru.put("ts_menu",           "Меню техподдержки:\n 1) Все заявки\n 2) Новые заявки\n 3) Принять заявку\n 4) Отклонить заявку\n 5) Отметить выполненной\n 6) Выход");
        ru.put("no_requests",       "Заявок нет.");
        ru.put("idx_accept",        "Номер заявки для принятия: ");
        ru.put("idx_reject",        "Номер заявки для отклонения: ");
        ru.put("idx_done",          "Номер заявки для отметки выполненной: ");
        ru.put("invalid_index",     "Неверный номер.");

        translations.put(Language.RU, ru);

        // ── Kazakh ───────────────────────────────────────────────────────────
        Map<String, String> kz = new HashMap<>();
        // login / общее
        kz.put("select_language",   "Select language:\n 1) English\n 2) Russian\n 3) Kazakh");
        kz.put("welcome",           "Университет жүйесіне қош келдіңіз!");
        kz.put("enter_username",    "Пайдаланушы аты: ");
        kz.put("enter_password",    "Құпия сөз: ");
        kz.put("login_success",     "Жүйеге кіру сәтті аяқталды!");
        kz.put("login_fail",        "Жүйеге кіру қатесі. Логин немесе құпия сөз қате.");
        kz.put("menu_logout",       "Жүйеден шығу...");
        kz.put("menu_back",         "Артқа");
        kz.put("menu_exit",         "Шығу");
        kz.put("prompt_continue",   " 1) Қайтадан  2) Артқа  3) Шығу");
        kz.put("again_back",        " 1) Қайтадан  2) Артқа");
        kz.put("back_exit",         " 1) Артқа  2) Шығу");
        kz.put("course_not_found",  "Курс табылмады.");
        kz.put("teacher_not_found", "Оқытушы табылмады.");
        kz.put("student_not_found", "Студент табылмады.");
        kz.put("no_courses",        "Қолжетімді курстар жоқ.");
        kz.put("no_students",       "Студенттер жоқ.");
        kz.put("no_teachers",       "Оқытушылар жоқ.");
        kz.put("pick_course",       "Курстар:");
        kz.put("pick_student",      "Студенттер:");
        kz.put("pick_teacher",      "Оқытушылар:");
        kz.put("pick_0_cancel",     "Таңдау (0 — бас тарту): ");
        kz.put("no_news",           "Жаңалықтар жоқ.");
        kz.put("menu_news",         "=== Жаңалықтар ===");

        // Student
        kz.put("student_menu",      "Не істегіңіз келеді?\n 1) Курсқа тіркелу\n 2) Курстан шығу\n 3) Оқытушыны бағалау\n 4) Курстарым\n 5) Оқытушы туралы ақпарат\n 6) Бағалар\n 7) Транскрипт\n 8) Жаңалықтар\n 9) Шығу");
        kz.put("reg_success",       "Сәтті тіркелдіңіз.");
        kz.put("reg_fail",          "Тіркелу қатесі: ");
        kz.put("drop_success",      "Сәтті шықтыңыз.");
        kz.put("drop_fail",         "Шығу мүмкін емес — бағалар бар.");
        kz.put("menu_rate_point",   "Баға (0-5): ");
        kz.put("rated",             "Баға қойылды.");
        kz.put("no_registered",     "Тіркелген курстар жоқ.");
        kz.put("your_courses",      "Сіздің курстарыңыз:");
        kz.put("all_marks",         "Барлық бағалар:");
        kz.put("no_marks",          "Бағалар әлі жоқ.");
        kz.put("marks_submenu",     " 1) Курс бағасы  2) Барлық бағалар  3) Артқа");

        // Teacher
        kz.put("teacher_menu",      "Не істегіңіз келеді?\n 1) Баға қою\n 2) Курстарым\n 3) Студенттер тізімі\n 4) Курс есебі\n 5) Ұсыныс хат жазу\n 6) Шығу");
        kz.put("attestation_type",  "Аттестация түрі: 1) Бірінші  2) Екінші  3) Емтихан");
        kz.put("enter_point",       "Балл енгізіңіз: ");
        kz.put("mark_added",        "Баға қойылды: ");
        kz.put("mark_fail",         "Баға қою мүмкін болмады (студент курсқа тіркелмеген?).");
        kz.put("report_label",      "=== Есеп: ");
        kz.put("rec_content",       "Хат мазмұны: ");
        kz.put("rec_saved",         "Ұсыныс хаты сақталды.");

        // Manager
        kz.put("manager_menu",      "Не істегіңіз келеді?\n 1) Курстарды басқару\n 2) Жаңалықтарды басқару\n 3) Курсты оқытушыға тағайындау\n 4) Студенттер\n 5) Оқытушылар\n 6) Хабарлама жіберу\n 7) Кіріс хабарламалар\n 8) Шығу");
        kz.put("courses_submenu",   "Курстар:\n 1) Курс қосу\n 2) Курсты жою");
        kz.put("news_submenu",      "Жаңалықтар:\n 1) Қосу\n 2) Жою\n 3) Жаңарту");
        kz.put("students_submenu",  "Студенттер:\n 1) Аты бойынша\n 2) GPA бойынша");
        kz.put("enter_cname",       "Курс атауы: ");
        kz.put("enter_semester",    "Семестр: 1) Күз  2) Көктем  3) Жаз");
        kz.put("enter_code",        "Курс коды: ");
        kz.put("enter_credits",     "Кредиттер саны: ");
        kz.put("is_elective",       "Элективті? 1) Жоқ  2) Иә");
        kz.put("enter_limit",       "Студент лимиті: ");
        kz.put("enter_prereq",      "Пресерквизит (немесе өткізіп жіберу үшін Enter): ");
        kz.put("enter_dept",        "Факультет (FIT/BS/CCE/FEOG/FGE/FGGE/ISE/KMA/SMS): ");
        kz.put("course_added",      "Курс қосылды!");
        kz.put("course_exists",     "Курс бұрыннан бар.");
        kz.put("course_removed",    "Курс жойылды!");
        kz.put("enter_ntitle",      "Жаңалық тақырыбы: ");
        kz.put("enter_ntext",       "Жаңалық мәтіні: ");
        kz.put("enter_ndate",       "Жаңалық күні: ");
        kz.put("news_added",        "Жаңалық қосылды!");
        kz.put("news_removed",      "Жаңалық жойылды!");
        kz.put("news_updated",      "Жаңалық жаңартылды!");
        kz.put("news_not_found",    "Жаңалық табылмады.");
        kz.put("assign_ok",         "Сәтті тағайындалды!");
        kz.put("no_assign_data",    "Курстар немесе оқытушылар жоқ.");
        kz.put("enter_receiver",    "Алушының аты: ");
        kz.put("enter_msg_text",    "Хабарлама мәтіні: ");
        kz.put("msg_sent",          "Хабарлама жіберілді.");
        kz.put("no_messages",       "Хабарламалар жоқ.");

        // Admin
        kz.put("admin_menu",        "Не істегіңіз келеді?\n 1) Пайдаланушы қосу\n 2) Пайдаланушыны жою\n 3) Барлық пайдаланушылар\n 4) Оқиғалар журналы\n 5) Іздеу (RegEx)\n 6) Шығу");
        kz.put("enter_user_type",   "Түрі (student/teacher/manager/admin): ");
        kz.put("enter_full_name",   "Толық аты: ");
        kz.put("enter_salary",      "Жалақы: ");
        kz.put("enter_student_id",  "Студент ID: ");
        kz.put("enter_year",        "Оқу жылы: ");
        kz.put("user_added",        "Пайдаланушы қосылды.");
        kz.put("unknown_type",      "Белгісіз пайдаланушы түрі.");
        kz.put("remove_username",   "Жою үшін логин: ");
        kz.put("user_removed",      "Пайдаланушы жойылды.");
        kz.put("user_not_found",    "Пайдаланушы табылмады.");
        kz.put("all_users_header",  "=== Барлық пайдаланушылар ===");
        kz.put("logs_header",       "=== Оқиғалар журналы ===");
        kz.put("no_logs",           "Жазбалар жоқ.");
        kz.put("search_in",         "Іздеу: 1) Пайдаланушылар  2) Курстар  3) Мақалалар");
        kz.put("enter_regex",       "Үлгі (regex): ");
        kz.put("found_prefix",      "Табылды ");
        kz.put("found_users_s",     " пайдаланушы:");
        kz.put("found_courses_s",   " курс:");
        kz.put("found_papers_s",    " мақала:");
        kz.put("invalid_regex",     "Жарамсыз regex: ");

        // GraduateStudent
        kz.put("grad_menu",         "Магистрант мәзірі:\n 1) Мақала қосу\n 2) Мақалалар тізімі\n 3) Жобаға кіру\n 4) Менің h-index\n 5) Стартап құру\n 6) Ұсыныс хаттар\n 7) Шығу");
        kz.put("title_prompt",      "Атауы: ");
        kz.put("authors_prompt",    "Авторлар (үтір арқылы): ");
        kz.put("journal_prompt",    "Журнал: ");
        kz.put("pages_prompt",      "Беттер саны: ");
        kz.put("year_prompt",       "Жыл (мыс. 2024): ");
        kz.put("doi_prompt",        "DOI (немесе өткізіп жіберу үшін Enter): ");
        kz.put("citations_prompt",  "Сілтемелер: ");
        kz.put("volume_prompt",     "Том: ");
        kz.put("number_prompt",     "Нөмір: ");
        kz.put("paper_added",       "Мақала қосылды.");
        kz.put("paper_fail",        "Қате: ");
        kz.put("sort_by",           "Сұрыптау: 1) Күні бойынша  2) Сілтемелер  3) Көлемі");
        kz.put("project_topic",     "Жоба тақырыбы: ");
        kz.put("joined_project",    "Жобаға кірдіңіз: ");
        kz.put("cannot_join",       "Кіру мүмкін болмады: ");
        kz.put("startup_name",      "Стартап атауы: ");
        kz.put("startup_desc",      "Сипаттама: ");
        kz.put("startup_created",   "Стартап құрылды!");
        kz.put("no_rec_letters",    "Ұсыныс хаттар жоқ.");

        // TechSupport
        kz.put("ts_menu",           "Техқолдау мәзірі:\n 1) Барлық өтінімдер\n 2) Жаңа өтінімдер\n 3) Өтінімді қабылдау\n 4) Өтінімді қабылдамау\n 5) Орындалды деп белгілеу\n 6) Шығу");
        kz.put("no_requests",       "Өтінімдер жоқ.");
        kz.put("idx_accept",        "Қабылдау үшін өтінім нөмірі: ");
        kz.put("idx_reject",        "Қабылдамау үшін өтінім нөмірі: ");
        kz.put("idx_done",          "Орындалды деп белгілеу үшін нөмір: ");
        kz.put("invalid_index",     "Жарамсыз нөмір.");

        translations.put(Language.KZ, kz);
    }

    public static String getTranslation(String key, Language lang) {
        if (lang == null || !translations.containsKey(lang)) return key;
        return translations.get(lang).getOrDefault(key, key);
    }
}
