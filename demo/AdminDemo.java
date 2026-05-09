package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import course.Course;
import other.Language;
import other.LanguageManager;
import users.Admin;
import users.Database;
import users.DegreeStudent;
import users.DegreeTeacher;
import users.FacultyType;
import users.Manager;
import users.ManagerType;
import users.Student;
import users.Teacher;
import users.User;

public class AdminDemo {
	static Admin admin = null;
	static BufferedReader br = null;

	private static String t(String key) {
		return LanguageManager.getTranslation(key, admin.getLanguage());
	}

	private static void addUser() throws IOException {
		System.out.print(t("enter_user_type"));
		String type = br.readLine();
		System.out.print(t("enter_full_name"));
		String fullName = br.readLine();
		System.out.print(t("enter_username"));
		String username = br.readLine();
		System.out.print(t("enter_password"));
		String password = br.readLine();

		if (type.equalsIgnoreCase("student")) {
			System.out.print(t("enter_student_id"));
			String id = br.readLine();
			System.out.print(t("enter_year"));
			int year = Integer.parseInt(br.readLine().trim());
			admin.addUser(new Student(fullName, username, password, id, year,
					DegreeStudent.BACHELOR, FacultyType.FIT, "CS"));
			System.out.println(t("user_added"));
		} else if (type.equalsIgnoreCase("teacher")) {
			System.out.print(t("enter_salary"));
			int salary = Integer.parseInt(br.readLine().trim());
			admin.addUser(new Teacher(fullName, username, password, salary,
					DegreeTeacher.LECTURER, FacultyType.FIT));
			System.out.println(t("user_added"));
		} else if (type.equalsIgnoreCase("manager")) {
			System.out.print(t("enter_salary"));
			int salary = Integer.parseInt(br.readLine().trim());
			admin.addUser(new Manager(fullName, username, password, salary, ManagerType.OR));
			System.out.println(t("user_added"));
		} else if (type.equalsIgnoreCase("admin")) {
			System.out.print(t("enter_salary"));
			int salary = Integer.parseInt(br.readLine().trim());
			admin.addUser(new Admin(fullName, username, password, salary));
			System.out.println(t("user_added"));
		} else {
			System.out.println(t("unknown_type"));
		}
	}

	private static void removeUser() throws IOException {
		System.out.print(t("remove_username"));
		String username = br.readLine();
		for (User u : Database.users) {
			if (u.getUsername().equals(username)) {
				admin.removeUser(u);
				System.out.println(t("user_removed"));
				return;
			}
		}
		System.out.println(t("user_not_found"));
	}

	private static void viewUsers() {
		System.out.println(t("all_users_header"));
		for (User u : Database.users) System.out.println(u);
	}

	private static void viewLogs() {
		System.out.println(t("logs_header"));
		if (Database.logs.isEmpty()) { System.out.println(t("no_logs")); return; }
		for (String log : admin.viewLogs()) System.out.println(log);
	}

	private static void search() throws IOException {
		System.out.println(t("search_in"));
		int target = Integer.parseInt(br.readLine());
		System.out.print(t("enter_regex"));
		String pattern = br.readLine();
		try {
			if (target == 1) {
				List<User> results = Database.searchUsers(pattern);
				System.out.println(t("found_prefix") + results.size() + t("found_users_s"));
				for (User u : results) System.out.println("  " + u);
			} else if (target == 2) {
				List<Course> results = Database.searchCourses(pattern);
				System.out.println(t("found_prefix") + results.size() + t("found_courses_s"));
				for (Course c : results) System.out.println("  " + c);
			} else if (target == 3) {
				List<research.ResearchPaper> results = Database.searchResearchPapers(pattern);
				System.out.println(t("found_prefix") + results.size() + t("found_papers_s"));
				for (research.ResearchPaper p : results) System.out.println("  " + p.getTitle());
			}
		} catch (PatternSyntaxException e) {
			System.out.println(t("invalid_regex") + e.getMessage());
		}
	}

	private static void exit() {
		Database.save();
		System.out.println(t("menu_logout"));
	}

	public static void run(User user, BufferedReader brIn) {
		admin = (Admin) user;
		br = brIn;
		try {
			menu: while (true) {
				System.out.println(t("admin_menu"));
				System.out.print("Choice: ");
				int choice = Integer.parseInt(br.readLine());
				switch (choice) {
				case 1:
					addUser();
					System.out.println(" 1) " + t("menu_back") + "  2) " + t("menu_exit"));
					if (Integer.parseInt(br.readLine()) != 1) { exit(); break menu; }
					break;
				case 2:
					removeUser();
					System.out.println(" 1) " + t("menu_back") + "  2) " + t("menu_exit"));
					if (Integer.parseInt(br.readLine()) != 1) { exit(); break menu; }
					break;
				case 3:
					viewUsers();
					System.out.println(" 1) " + t("menu_back") + "  2) " + t("menu_exit"));
					if (Integer.parseInt(br.readLine()) != 1) { exit(); break menu; }
					break;
				case 4:
					viewLogs();
					System.out.println(" 1) " + t("menu_back") + "  2) " + t("menu_exit"));
					if (Integer.parseInt(br.readLine()) != 1) { exit(); break menu; }
					break;
				case 5:
					while (true) {
						search();
						System.out.println(t("prompt_continue"));
						int c = Integer.parseInt(br.readLine());
						if (c == 1) continue;
						if (c == 2) break;
						exit(); break menu;
					}
					break;
				default:
					exit();
					break menu;
				}
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
