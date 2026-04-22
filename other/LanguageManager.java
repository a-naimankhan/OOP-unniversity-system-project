package other;

import java.util.HashMap;
import java.util.Map;

public class LanguageManager {
    private static final Map<Language, Map<String, String>> translations = new HashMap<>();

    static {
        // English
        Map<String, String> en = new HashMap<>();
        en.put("login_success", "Login successful!");
        en.put("login_fail", "Login failed. Invalid username or password.");
        en.put("welcome", "Welcome to the University System!");
        en.put("menu_exit", "Exit");
        en.put("menu_news", "View News");
        en.put("menu_register", "Register for Course");
        translations.put(Language.EN, en);

        // Kazakh
        Map<String, String> kz = new HashMap<>();
        kz.put("login_success", "Жүйеге кіру сәтті аяқталды!");
        kz.put("login_fail", "Жүйеге кіру қатесі. Пайдаланушы аты немесе құпия сөз қате.");
        kz.put("welcome", "Университет жүйесіне қош келдіңіз!");
        kz.put("menu_exit", "Шығу");
        kz.put("menu_news", "Жаңалықтарды қарау");
        kz.put("menu_register", "Курсқа тіркелу");
        translations.put(Language.KZ, kz);

        // Russian
        Map<String, String> ru = new HashMap<>();
        ru.put("login_success", "Вход выполнен успешно!");
        ru.put("login_fail", "Ошибка входа. Неверное имя пользователя или пароль.");
        ru.put("welcome", "Добро пожаловать в университетскую систему!");
        ru.put("menu_exit", "Выход");
        ru.put("menu_news", "Просмотр новостей");
        ru.put("menu_register", "Регистрация на курс");
        translations.put(Language.RU, ru);
    }

    public static String getTranslation(String key, Language lang) {
        if (!translations.containsKey(lang)) return key;
        return translations.get(lang).getOrDefault(key, key);
    }
}
