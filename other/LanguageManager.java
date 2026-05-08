package other;

import java.util.HashMap;
import java.util.Map;

public class LanguageManager {
    private static final Map<Language, Map<String, String>> translations = new HashMap<>();

    static {
        // ── English ──────────────────────────────────────────────────────────
        Map<String, String> en = new HashMap<>();
        en.put("login_success", "Login successful!");
        en.put("login_fail", "Login failed. Invalid username or password.");
        en.put("welcome", "Welcome to the University System!");
        en.put("select_language", "Select language:\n 1) English\n 2) Russian (Русский)\n 3) Kazakh (Қазақша)");
        en.put("enter_username", "Username: ");
        en.put("enter_password", "Password: ");
        en.put("student_menu", "What do you want to do?\n 1) Register for course\n 2) Drop course\n 3) Rate teacher\n 4) View courses\n 5) View teacher info\n 6) View marks\n 7) View transcript\n 8) View news\n 9) Logout");
        en.put("menu_register", "Enter course name: ");
        en.put("reg_success", "Registered successfully.");
        en.put("reg_fail", "Registration failed: ");
        en.put("course_not_found", "Course not found.");
        en.put("menu_drop", "Enter course name to drop: ");
        en.put("drop_success", "Dropped successfully.");
        en.put("drop_fail", "Cannot drop — course already has marks.");
        en.put("menu_rate", "Enter teacher's name: ");
        en.put("menu_rate_point", "Enter rating (0-5): ");
        en.put("rated", "Rated successfully.");
        en.put("menu_logout", "Logging out...");
        en.put("menu_exit", "Exit");
        en.put("menu_back", "Back");
        en.put("menu_news", "=== News ===");
        en.put("no_news", "No news available.");
        en.put("teacher_not_found", "Teacher not found.");
        en.put("prompt_continue", " 1) Again  2) Back  3) Exit");
        translations.put(Language.EN, en);

        // ── Russian ──────────────────────────────────────────────────────────
        Map<String, String> ru = new HashMap<>();
        ru.put("login_success", "Вход выполнен успешно!");
        ru.put("login_fail", "Ошибка входа. Неверное имя пользователя или пароль.");
        ru.put("welcome", "Добро пожаловать в университетскую систему!");
        ru.put("select_language", "Select language:\n 1) English\n 2) Russian (Русский)\n 3) Kazakh (Қазақша)");
        ru.put("enter_username", "Имя пользователя: ");
        ru.put("enter_password", "Пароль: ");
        ru.put("student_menu", "Что вы хотите сделать?\n 1) Записаться на курс\n 2) Отписаться от курса\n 3) Оценить преподавателя\n 4) Мои курсы\n 5) Информация о преподавателе\n 6) Оценки\n 7) Транскрипт\n 8) Новости\n 9) Выход");
        ru.put("menu_register", "Введите название курса: ");
        ru.put("reg_success", "Успешно записаны.");
        ru.put("reg_fail", "Ошибка записи: ");
        ru.put("course_not_found", "Курс не найден.");
        ru.put("menu_drop", "Введите название курса для отписки: ");
        ru.put("drop_success", "Успешно отписаны.");
        ru.put("drop_fail", "Нельзя отписаться — курс уже имеет оценки.");
        ru.put("menu_rate", "Введите имя преподавателя: ");
        ru.put("menu_rate_point", "Введите оценку (0-5): ");
        ru.put("rated", "Оценка выставлена.");
        ru.put("menu_logout", "Выход из системы...");
        ru.put("menu_exit", "Выход");
        ru.put("menu_back", "Назад");
        ru.put("menu_news", "=== Новости ===");
        ru.put("no_news", "Новостей нет.");
        ru.put("teacher_not_found", "Преподаватель не найден.");
        ru.put("prompt_continue", " 1) Ещё раз  2) Назад  3) Выход");
        translations.put(Language.RU, ru);

        // ── Kazakh ───────────────────────────────────────────────────────────
        Map<String, String> kz = new HashMap<>();
        kz.put("login_success", "Жүйеге кіру сәтті аяқталды!");
        kz.put("login_fail", "Жүйеге кіру қатесі. Пайдаланушы аты немесе құпия сөз қате.");
        kz.put("welcome", "Университет жүйесіне қош келдіңіз!");
        kz.put("select_language", "Select language:\n 1) English\n 2) Russian (Русский)\n 3) Kazakh (Қазақша)");
        kz.put("enter_username", "Пайдаланушы аты: ");
        kz.put("enter_password", "Құпия сөз: ");
        kz.put("student_menu", "Не істегіңіз келеді?\n 1) Курсқа тіркелу\n 2) Курстан шығу\n 3) Оқытушыны бағалау\n 4) Курстарым\n 5) Оқытушы туралы ақпарат\n 6) Бағалар\n 7) Транскрипт\n 8) Жаңалықтар\n 9) Шығу");
        kz.put("menu_register", "Курс атын енгізіңіз: ");
        kz.put("reg_success", "Сәтті тіркелдіңіз.");
        kz.put("reg_fail", "Тіркелу қатесі: ");
        kz.put("course_not_found", "Курс табылмады.");
        kz.put("menu_drop", "Шығатын курс атын енгізіңіз: ");
        kz.put("drop_success", "Сәтті шықтыңыз.");
        kz.put("drop_fail", "Шығу мүмкін емес — бағалар бар.");
        kz.put("menu_rate", "Оқытушының атын енгізіңіз: ");
        kz.put("menu_rate_point", "Бағаны енгізіңіз (0-5): ");
        kz.put("rated", "Баға қойылды.");
        kz.put("menu_logout", "Жүйеден шығу...");
        kz.put("menu_exit", "Шығу");
        kz.put("menu_back", "Артқа");
        kz.put("menu_news", "=== Жаңалықтар ===");
        kz.put("no_news", "Жаңалықтар жоқ.");
        kz.put("teacher_not_found", "Оқытушы табылмады.");
        kz.put("prompt_continue", " 1) Қайтадан  2) Артқа  3) Шығу");
        translations.put(Language.KZ, kz);
    }

    public static String getTranslation(String key, Language lang) {
        if (lang == null || !translations.containsKey(lang)) return key;
        return translations.get(lang).getOrDefault(key, key);
    }
}
